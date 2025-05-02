package com.fran.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fran.shortlink.admin.dao.entity.GroupDO;
import com.fran.shortlink.admin.dao.mapper.GroupMapper;
import com.fran.shortlink.admin.service.GroupService;
import com.fran.shortlink.admin.toolkit.RandomGenerator;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Short Link Group Interface Implementation
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

    @Override
    public void saveGroup(String groupName) {

        String gid;
        do {
            gid = RandomGenerator.generateRandom();
        } while (!hasGid(gid));
        GroupDO groupDO = GroupDO.builder()
            .gid(gid)
            .name(groupName)
            .build();
        baseMapper.insert(groupDO);
    }

    private boolean hasGid(String gid) {
        // Make sure gid is unique
        LambdaQueryWrapper<GroupDO> queryMapper = Wrappers.lambdaQuery(GroupDO.class)
            .eq(GroupDO::getDelFlag, 0)
            .eq(GroupDO::getGid, gid)
            // TODO SET username
            .eq(GroupDO::getUsername, null);
        GroupDO hasGroupFlag = baseMapper.selectOne(queryMapper);
        return hasGroupFlag == null;

    }
}
