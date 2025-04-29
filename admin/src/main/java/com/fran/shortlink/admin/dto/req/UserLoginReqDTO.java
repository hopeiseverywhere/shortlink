package com.fran.shortlink.admin.dto.req;

import lombok.Data;

/**
 * User Login Request DTO
 */
@Data
public class UserLoginReqDTO {

    private String username;
    private String password;
}
