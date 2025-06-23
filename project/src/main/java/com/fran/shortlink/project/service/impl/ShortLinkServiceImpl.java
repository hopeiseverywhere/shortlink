package com.fran.shortlink.project.service.impl;

import cn.hutool.core.text.StrBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fran.shortlink.project.common.convention.exception.ServiceException;
import com.fran.shortlink.project.dao.entity.ShortLinkDO;
import com.fran.shortlink.project.dao.mapper.ShortLinkMapper;
import com.fran.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.fran.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.fran.shortlink.project.service.ShortLinkService;
import com.fran.shortlink.project.toolkit.HashUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * Short Link API Impl
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements
    ShortLinkService {

    private final RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter;

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
        try {
            baseMapper.insert(shortLinkDO);
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
        shortUriCreateCachePenetrationBloomFilter.add(shortLinkSuffix);
        return ShortLinkCreateRespDTO.builder()
            .fullShortUrl(shortLinkDO.getFullShortUrl())
            .originUrl(requestParam.getOriginUrl())
            .gid(requestParam.getGid())
            .build();
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
