package com.fran.shortlink.admin.common.enums;

import com.fran.shortlink.admin.common.convention.errorcode.IErrorCode;

/**
 * User error code
 */
public enum UserErrorCodeEnum implements IErrorCode {

    USER_TOKEN_FAIL("A000200", "User token validation failed"),

    USER_NULL("B000200", "User record does not exist"),

    USER_NAME_EXIST("B000201", "Username already exists"),

    USER_EXIST("B000202", "User record already exists"),

    USER_SAVE_ERROR("B000203", "Failed to add user record");

    private final String code;

    private final String message;

    UserErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }


    @Override
    public String message() {
        return message;
    }
}
