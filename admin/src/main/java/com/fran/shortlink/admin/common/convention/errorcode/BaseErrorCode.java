package com.fran.shortlink.admin.common.convention.errorcode;

/**
 * Basic Error Code Definitions
 */
public enum BaseErrorCode implements IErrorCode {

    // ========== Level 1 Macro Error Code - Client Error ==========
    CLIENT_ERROR("A000001", "Client-side error"),

    // ========== Level 2 Macro Error Code - User Registration Error ==========
    USER_REGISTER_ERROR("A000100", "User registration error"),
    USER_NAME_VERIFY_ERROR("A000110", "Username validation failed"),
    USER_NAME_EXIST_ERROR("A000111", "Username already exists"),
    USER_NAME_SENSITIVE_ERROR("A000112", "Username contains sensitive words"),
    USER_NAME_SPECIAL_CHARACTER_ERROR("A000113", "Username contains special characters"),
    PASSWORD_VERIFY_ERROR("A000120", "Password validation failed"),
    PASSWORD_SHORT_ERROR("A000121", "Password is too short"),
    PHONE_VERIFY_ERROR("A000151", "Phone number format validation failed"),

    // ========== Level 2 Macro Error Code - Missing Idempotent Token in Request ==========
    IDEMPOTENT_TOKEN_NULL_ERROR("A000200", "Idempotent token is missing"),
    IDEMPOTENT_TOKEN_DELETE_ERROR("A000201", "Idempotent token has been used or expired"),

    // ========== Level 1 Macro Error Code - System Execution Error ==========
    SERVICE_ERROR("B000001", "System execution error"),
    // ========== Level 2 Macro Error Code - System Execution Timeout ==========
    SERVICE_TIMEOUT_ERROR("B000100", "System execution timeout"),

    // ========== Level 1 Macro Error Code - Third-party Service Error ==========
    REMOTE_ERROR("C000001", "Error calling third-party service");

    private final String code;
    private final String message;

    BaseErrorCode(String code, String message) {
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
