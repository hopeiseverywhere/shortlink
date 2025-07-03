package com.fran.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.StrBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fran.shortlink.project.common.convention.exception.ClientException;
import com.fran.shortlink.project.common.convention.exception.ServiceException;
import com.fran.shortlink.project.common.enums.ValidDateTypeEnum;
import com.fran.shortlink.project.dao.entity.ShortLinkDO;
import com.fran.shortlink.project.dao.entity.ShortLinkGotoDO;
import com.fran.shortlink.project.dao.mapper.ShortLinkGotoMapper;
import com.fran.shortlink.project.dao.mapper.ShortLinkMapper;
import com.fran.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.fran.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.fran.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.fran.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.fran.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.fran.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.fran.shortlink.project.service.ShortLinkService;
import com.fran.shortlink.project.toolkit.HashUtil;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Short Link API Impl
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements
    ShortLinkService {

    private final RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter;
    private final ShortLinkMapper shortLinkMapper;
    private final ShortLinkGotoMapper shortLinkGotoMapper;

    @Override
    @SneakyThrows
    public ShortLinkCreateRespDTO createShortUrl(ShortLinkCreateReqDTO requestParam) {

        String shortLinkSuffix = generatorSuffix(requestParam);
        String fullShortUrl = StrBuilder.create(requestParam.getDomain())
            .append("/")
            .append(shortLinkSuffix)
            .toString();
        ShortLinkDO shortLinkDO = ShortLinkDO.builder()
            .domain(requestParam.getDomain())
            .originUrl(requestParam.getOriginUrl())
            .gid(requestParam.getGid())
            .createType(requestParam.getCreateType())
            .validDateType(requestParam.getValidDateType())
            .validDate(requestParam.getValidDate())
            .description(requestParam.getDescription())
            .shortUri(shortLinkSuffix)
            .enableStatus(0)
            .fullShortUrl(fullShortUrl)
            .build();
        ShortLinkGotoDO shortLinkGotoDO = ShortLinkGotoDO.builder()
            .fullShortUrl(fullShortUrl)
            .gid(requestParam.getGid())
            .build();
        try {
            baseMapper.insert(shortLinkDO);
            shortLinkGotoMapper.insert(shortLinkGotoDO);
        } catch (DuplicateKeyException ex) {
            LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getFullShortUrl, fullShortUrl);
            ShortLinkDO hasShortLinkDO = baseMapper.selectOne(queryWrapper);
            if (hasShortLinkDO != null) {
                log.warn("short uri: {} already exist", fullShortUrl);
                throw new ServiceException("ShortUrl already exist when generate");
            }
        }
        // Add to bloom filter
        shortUriCreateCachePenetrationBloomFilter.add(fullShortUrl);
        return ShortLinkCreateRespDTO.builder()
            .fullShortUrl(requestParam.getDomainProtocol() + shortLinkDO.getFullShortUrl())
            .originUrl(requestParam.getOriginUrl())
            .gid(requestParam.getGid())
            .build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateShortLink(ShortLinkUpdateReqDTO requestParam) {
        // If we need to switch group (we used gid as sharding key)
        // So we need to delete the old record

        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
            .eq(ShortLinkDO::getGid, requestParam.getGid())
            .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
            .eq(ShortLinkDO::getDelFlag, 0)
            .eq(ShortLinkDO::getEnableStatus, 0);

        ShortLinkDO hasShortLinkDO = baseMapper.selectOne(queryWrapper);
        if (hasShortLinkDO == null) {
            throw new ClientException("Short Link does not exist");
        }

        ShortLinkDO shortLinkDO = ShortLinkDO.builder()
            .domain(hasShortLinkDO.getDomain())
            .shortUri(hasShortLinkDO.getShortUri())
            .clickCount(hasShortLinkDO.getClickCount())
            .favicon(hasShortLinkDO.getFavicon())
            .createType(hasShortLinkDO.getCreateType())
            .gid(hasShortLinkDO.getGid())
            .originUrl(requestParam.getOriginUrl())
            .description(requestParam.getDescription())
            .validDateType(requestParam.getValidDateType())
            .validDate(requestParam.getValidDate())
            .build();
        if (Objects.equals(hasShortLinkDO.getGid(), requestParam.getGid())) {

            LambdaUpdateWrapper<ShortLinkDO> updateWrapper = Wrappers.lambdaUpdate(
                    ShortLinkDO.class)
                .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
                .eq(ShortLinkDO::getGid, requestParam.getGid())
                .eq(ShortLinkDO::getDelFlag, 0)
                .eq(ShortLinkDO::getEnableStatus, 0)
                .set(Objects.equals(requestParam.getValidDateType(),
                        ValidDateTypeEnum.PERMANENT.getType()), ShortLinkDO::getValidDate,
                    null);

            baseMapper.update(shortLinkDO, updateWrapper);
        } else {
            LambdaUpdateWrapper<ShortLinkDO> updateWrapper = Wrappers.lambdaUpdate(
                    ShortLinkDO.class)
                .eq(ShortLinkDO::getFullShortUrl, requestParam.getFullShortUrl())
                .eq(ShortLinkDO::getGid, hasShortLinkDO.getGid())
                .eq(ShortLinkDO::getDelFlag, 0)
                .eq(ShortLinkDO::getEnableStatus, 0);
            baseMapper.delete(updateWrapper);
            shortLinkDO.setGid(requestParam.getGid());
            baseMapper.insert(shortLinkDO);
        }

    }

    @Override
    public IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam) {
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
            .eq(ShortLinkDO::getGid, requestParam.getGid())
            .eq(ShortLinkDO::getEnableStatus, 0)
            .eq(ShortLinkDO::getDelFlag, 0)
            .orderByDesc(ShortLinkDO::getCreateTime);
        IPage<ShortLinkDO> resultPage = baseMapper.selectPage(requestParam, queryWrapper);
        return resultPage.convert(each -> BeanUtil.toBean(each, ShortLinkPageRespDTO.class));
    }

    @Override
    public List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(
        List<String> requestParam) {
        QueryWrapper<ShortLinkDO> queryWrapper = Wrappers.query(new ShortLinkDO())
            .select("gid as gid, count(*) as shortLinkCount")
            .in("gid", requestParam)
            .eq("enable_status", 0)
            //.eq("del_flag", 0)
            //.eq("del_time", 0L)
            .groupBy("gid");
        List<Map<String, Object>> shortLinkDOList = baseMapper.selectMaps(queryWrapper);
        return BeanUtil.copyToList(shortLinkDOList, ShortLinkGroupCountQueryRespDTO.class);
    }

    @Override
    @SneakyThrows
    public void restoreUrl(String shortUri, ServletRequest request,
        ServletResponse response) {
        String serverName = request.getServerName();
        String fullShortUrl = serverName + "/" + shortUri;
        // Get gid from routing table
        LambdaUpdateWrapper<ShortLinkGotoDO> shortLinkGotoQueryWrapper = Wrappers.lambdaUpdate(
                ShortLinkGotoDO.class)
            .eq(ShortLinkGotoDO::getFullShortUrl, fullShortUrl);
        ShortLinkGotoDO shortLinkGotoDO = shortLinkGotoMapper.selectOne(shortLinkGotoQueryWrapper);
        if (shortLinkGotoDO == null) {

            return;
        }

        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
            .eq(ShortLinkDO::getGid, shortLinkGotoDO.getGid())
            .eq(ShortLinkDO::getFullShortUrl, fullShortUrl)
            .eq(ShortLinkDO::getDelFlag, 0)
            .eq(ShortLinkDO::getEnableStatus, 0);

        ShortLinkDO shortLinkDO = baseMapper.selectOne(queryWrapper);
        if (shortLinkDO != null) {
            ((HttpServletResponse) response).sendRedirect(shortLinkDO.getOriginUrl());
        }
        //// In bloom filter or not
        //if (shortUriCreateCachePenetrationBloomFilter.contains(fullShortUrl)) {
        //
        //}
        //
    }

    @SneakyThrows
    private String generatorSuffix(ShortLinkCreateReqDTO requestParam) {
        String shortUri;
        int customGenerateCount = 0;
        while (true) {
            if (customGenerateCount > 10) {
                throw new ServiceException("Too many short uri generated, try again later");
            }
            String originalUrl = requestParam.getOriginUrl();
            originalUrl += System.currentTimeMillis();
            shortUri = HashUtil.hashToBase62(originalUrl);

            if (!shortUriCreateCachePenetrationBloomFilter.contains(
                requestParam.getDomain() + "/" + shortUri)) {
                break;
            }

            customGenerateCount++;
        }
        return shortUri;
    }
}
