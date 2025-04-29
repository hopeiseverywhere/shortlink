package com.fran.shortlink.admin.dto.req;

import lombok.Data;

/**
 * User Update Request DTO
 */
@Data
public class UserUpdateReqDTO {

    private String username;
    private String password;
    private String realName;
    private String phone;
    private String email;
}
