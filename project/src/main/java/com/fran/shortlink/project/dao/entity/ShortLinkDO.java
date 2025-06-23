package com.fran.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fran.shortlink.project.common.database.BaseDO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Short Link Data Object
 */
@Data
@Builder
@TableName("t_link")
@NoArgsConstructor
@AllArgsConstructor
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
    /**
     * Expiration Date
     */
    private Date validDate;
    private String description;

}
