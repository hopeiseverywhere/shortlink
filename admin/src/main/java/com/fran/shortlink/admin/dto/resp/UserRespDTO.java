package com.fran.shortlink.admin.dto.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fran.shortlink.admin.common.serialize.PhoneDesensitizationSerializer;
import lombok.Data;

/**
 * User Response Entity w/ Desensitized Info
 */
@Data
public class UserRespDTO {

    private Long id;
    private String username;
    private String realName;
    @JsonSerialize(using = PhoneDesensitizationSerializer.class)
    private String phone;
    private String email;
}
