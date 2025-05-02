package com.fran.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fran.shortlink.admin.dao.entity.GroupDO;
import com.fran.shortlink.admin.dao.mapper.GroupMapper;
import com.fran.shortlink.admin.service.GroupService;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Service;


/**
 * Short Link Group Interface Implementation
 */
@Slf4j
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {

}
