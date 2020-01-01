package com.kys.book.exception;

import com.kys.book.constants.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ApiException extends RuntimeException {

    @Getter
    private final ErrorCode errorCode;

}
