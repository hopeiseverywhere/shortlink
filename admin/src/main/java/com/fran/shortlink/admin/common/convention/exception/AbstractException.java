package com.fran.shortlink.admin.common.convention.exception;

import com.fran.shortlink.admin.common.convention.errorcode.IErrorCode;
import java.util.Optional;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * Abstracts three types of exception systems in the project: client-side exceptions, server-side
 * exceptions, and remote service call exceptions.
 *
 * @see ClientException
 * @see ServerException
 * @see RemoteException
 */
@Getter
public abstract class AbstractException extends RuntimeException {

    public final String errorCode;

    public final String errorMessage;

    public AbstractException(String message, Throwable throwable, IErrorCode errorCode) {
        super(message, throwable);
        this.errorCode = errorCode.code();
        this.errorMessage = Optional.ofNullable(StringUtils.hasLength(message) ? message : null)
            .orElse(errorCode.message());
    }
}
