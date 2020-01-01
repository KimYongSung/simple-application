package com.kys.book.exception;

import com.kys.book.constants.ErrorCode;

public class UserNotFoundException extends ApiException {

    public UserNotFoundException() {
        super(ErrorCode.CODE_0001);
    }
}
