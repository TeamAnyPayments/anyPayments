package com.artist.wea.common.exception;

import com.artist.wea.common.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommonApiException extends RuntimeException {
    private final ErrorCode errorCode;

}