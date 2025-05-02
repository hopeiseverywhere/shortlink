package com.fran.shortlink.admin.common.database;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.util.Date;
import lombok.Data;

/**
 * Database Persistence Layer Base Data Object
 */
@Data
public class BaseDO {

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * Deletion flag 0: not deleted 1: deleted
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;
}
