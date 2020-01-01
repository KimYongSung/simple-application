package com.kys.book.exception;

import com.kys.book.constants.ErrorCode;
import lombok.Getter;

public abstract class ApiException extends RuntimeException {

    @Getter
    private final ErrorCode errorCode;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.toString());
        this.errorCode = errorCode;
    }
}
