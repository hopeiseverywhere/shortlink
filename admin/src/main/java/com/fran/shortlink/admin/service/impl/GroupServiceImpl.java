package com.fran.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fran.shortlink.admin.common.biz.user.UserContext;
import com.fran.shortlink.admin.common.convention.result.Result;
import com.fran.shortlink.admin.dao.entity.GroupDO;
import com.fran.shortlink.admin.dao.mapper.GroupMapper;
import com.fran.shortlink.admin.dto.req.ShortLinkGroupSortReqDTO;
import com.fran.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.fran.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.fran.shortlink.admin.remote.dto.ShortLinkRemoteService;
import com.fran.shortlink.admin.remote.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.fran.shortlink.admin.service.GroupService;
import com.fran.shortlink.admin.toolkit.RandomGenerator;
import groovy.util.logging.Slf4j;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.stereotype.Service;


/**
 * Short Link Group Interface Implementation
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    @Override
    public void saveGroup(String groupName) {

        String gid;
        do {
            gid = RandomGenerator.generateRandom();
        } while (!hasGid(gid));
        GroupDO groupDO = GroupDO.builder().gid(gid).sortOrder(0)
            .username(UserContext.getUsername()).name(groupName).build();
        baseMapper.insert(groupDO);
    }

    @Override
    public List<ShortLinkGroupRespDTO> listGroup() {
        //LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
        //    .eq(GroupDO::getDelFlag, 0).eq(GroupDO::getUsername, UserContext.getUsername())
        //    .orderByDesc(List.of(GroupDO::getSortOrder, GroupDO::getUpdateTime));
        //List<GroupDO> groupDOList = baseMapper.selectList(queryWrapper);
        //Result<List<ShortLinkGroupCountQueryRespDTO>> listResult = shortLinkRemoteService.listGroupShortLinkCount(
        //    groupDOList.stream().map(GroupDO::getGid).toList());
        //List<ShortLinkGroupRespDTO> shortLinkGroupRespDTOList = BeanUtil.copyToList(groupDOList,
        //    ShortLinkGroupRespDTO.class);
        //shortLinkGroupRespDTOList.forEach(each -> {
        //    Optional<ShortLinkGroupCountQueryRespDTO> first = listResult.getData().stream()
        //        .filter(item -> Objects.equals(item.getGid(), each.getGid()))
        //        .findFirst();
        //    first.ifPresent(item -> each.setShortLinkCount(first.get().getShortLinkCount()));
        //});
        //return shortLinkGroupRespDTOList;
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
            .eq(GroupDO::getDelFlag, 0)
            .eq(GroupDO::getUsername, UserContext.getUsername())
            .orderByDesc(GroupDO::getSortOrder, GroupDO::getUpdateTime);
        List<GroupDO> groupDOList = baseMapper.selectList(queryWrapper);
        Result<List<ShortLinkGroupCountQueryRespDTO>> listResult = shortLinkRemoteService
            .listGroupShortLinkCount(groupDOList.stream().map(GroupDO::getGid).toList());
        List<ShortLinkGroupRespDTO> shortLinkGroupRespDTOList = BeanUtil.copyToList(groupDOList,
            ShortLinkGroupRespDTO.class);
        shortLinkGroupRespDTOList.forEach(each -> {
            Optional<ShortLinkGroupCountQueryRespDTO> first = listResult.getData().stream()
                .filter(item -> Objects.equals(item.getGid(), each.getGid()))
                .findFirst();
            first.ifPresent(item -> each.setShortLinkCount(first.get().getShortLinkCount()));
        });
        return shortLinkGroupRespDTOList;
    }

    @Override
    public void updateGroup(ShortLinkGroupUpdateReqDTO requestParam) {
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
            .eq(GroupDO::getUsername, UserContext.getUsername())
            .eq(GroupDO::getGid, requestParam.getGid()).eq(GroupDO::getDelFlag, 0);
        GroupDO groupDO = new GroupDO();
        groupDO.setName(requestParam.getName());
        baseMapper.update(groupDO, updateWrapper);
    }

    @Override
    public void deleteGroup(String gid) {
        LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
            .eq(GroupDO::getUsername, UserContext.getUsername()).eq(GroupDO::getGid, gid)
            .eq(GroupDO::getDelFlag, 0);
        GroupDO groupDO = new GroupDO();
        groupDO.setDelFlag(1);
        baseMapper.update(groupDO, updateWrapper);
    }

    @Override
    public void sortGroup(List<ShortLinkGroupSortReqDTO> requestParam) {
        requestParam.forEach(each -> {
            GroupDO groupDO = GroupDO.builder()
                //.gid(each.getGid())
                .sortOrder(each.getSortOrder()).build();
            LambdaUpdateWrapper<GroupDO> updateWrapper = Wrappers.lambdaUpdate(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getGid, each.getGid()).eq(GroupDO::getDelFlag, 0);
            baseMapper.update(groupDO, updateWrapper);
        });
    }

    private boolean hasGid(String gid) {
        // Make sure gid is unique
        LambdaQueryWrapper<GroupDO> queryMapper = Wrappers.lambdaQuery(GroupDO.class)
            .eq(GroupDO::getGid, gid).eq(GroupDO::getUsername, UserContext.getUsername());
        GroupDO hasGroupFlag = baseMapper.selectOne(queryMapper);
        return hasGroupFlag == null;

    }
}
