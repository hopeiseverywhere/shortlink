package com.fran.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fran.shortlink.admin.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Short Link Group Data Object
 */
@Data
@TableName("t_group")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDO extends BaseDO {

    /**
     * id
     */
    private Long id;

    /**
     * Group id
     */
    private String gid;

    /**
     * Group name
     */
    private String name;

    /**
     * Username of Group Creator
     */
    private String username;

    /**
     * Group Sort Order
     */
    private Integer sortOrder;


}
