package com.fran.shortlink.admin.dto.resp;

import java.util.Date;
import lombok.Data;

/**
 * User response entity
 */
@Data
public class UserRespDTO {
 private Long id;
    private String username;
    private String realName;
    private String phone;
    private String email;
    private Long deletionTime;
    private Date createTime;
    private Date updateTime;
    private Integer delFlag;
}
