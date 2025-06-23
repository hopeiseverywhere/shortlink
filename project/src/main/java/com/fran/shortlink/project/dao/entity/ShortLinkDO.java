package com.fran.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fran.shortlink.project.common.database.BaseDO;
import lombok.Data;

/**
 * Short Link Data Object
 */
@Data
@TableName("t_link")
public class ShortLinkDO extends BaseDO {

    /**
     * id
     */
    private Long id;
    private String domain;
    private String shortUri;

    private String fullShortUrl;
    private String originUrl;
    private Integer clickCount;
    /**
     * Group identifier
     */
    private String gid;
    /**
     * Enable Status: 0 = Disabled, 1 = Enabled
     */
    private Integer enableStatus;
    /**
     * Creation Type: 0 = Console, 1 = API
     */
    private Integer createType;

    /**
     * Validity Type: 0 = Permanent, 1 = Custom
     */
    private Integer validDateType;
    private String description;

}
