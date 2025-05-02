package com.fran.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
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
public class GroupDO {

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

    private Date createTime;
    private Date updateTime;
    /**
     * Deletion flag 0: not deleted 1: deleted
     */
    private Integer delFlag;
}
