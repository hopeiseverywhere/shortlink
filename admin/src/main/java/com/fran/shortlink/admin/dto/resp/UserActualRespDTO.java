package com.fran.shortlink.admin.dto.resp;

import lombok.Data;

/**
 * User Response Entity w/ Actual Info
 */
@Data
public class UserActualRespDTO {

    private Long id;
    private String username;
    private String realName;
    private String phone;
    private String email;
}
