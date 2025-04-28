package com.fran.shortlink.admin.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * User data object
 */
@Data
@TableName("t_user")
public class UserDO {

    private Long id;
    private String username;
    private String password;
    private String realName;
    private String phone;
    private String email;
    private Long deletionTime;
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
