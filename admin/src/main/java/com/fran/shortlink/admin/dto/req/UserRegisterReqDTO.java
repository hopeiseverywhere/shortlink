package com.fran.shortlink.admin.dto.req;

import lombok.Data;

/**
 * User Registration Request DTO
 */
@Data
public class UserRegisterReqDTO {

    private Long id;
    private String username;
    private String password;
    private String realName;
    private String phone;
    private String email;
}
