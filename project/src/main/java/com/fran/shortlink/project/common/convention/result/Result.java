package com.fran.shortlink.project.common.convention.result;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Global response object
 * @param <T>
 */
@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 5679018624309023727L;

    /**
     * Success return code
     */
    public static final String SUCCESS_CODE = "0";

    /**
     * Return code
     */
    private String code;

    /**
     * Return message
     */
    private String message;

    /**
     * Response data
     */
    private T data;

    /**
     * Request ID
     */
    private String requestId;

    /**
     * Check if the response is successful
     */
    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }
}
