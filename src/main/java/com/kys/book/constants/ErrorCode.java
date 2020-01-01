package com.kys.book.constants;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCode {

    CODE_0001("0001","사용자가 없습니다. ")
    ;

    private String code;

    private String message;
}
