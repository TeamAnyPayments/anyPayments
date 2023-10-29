package com.artist.wea.common.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    // Common
    BAD_REQUEST(400, "COMMON-001", "Invalid Request"),
    UNAUTHORIZED(401, "COMMON-002", "Unauthorized"),
    FORBIDDEN_ACCESS(403, "COMMON-003", "Forbidden"),
    NOT_FOUND(404, "COMMON-004", "Not Found"),
    METHOD_NOT_ALLOWED(405, "COMMON-005", "Not Allowed Method"),
    INTERNAL_SERVER_ERROR(500, "COMMON-005", "Server Error"),

    // User
    USER_NOT_CHECK_ID(400, "USER-001", "아이디 중복 확인이 필요합니다."),
    USER_NOT_CHECK_EMAIL(400, "USER-002", "이메일 인증이 필요합니다."),
    USER_DUPLICATE_ID(400, "USER-003", "사용할 수 없는 아이디입니다."),
    USER_DUPLICATE_EMAIL(400, "USER-004", "사용할 수 없는 이메일입니다."),
    USER_INVALID_PASSWORD(400, "USER-005", "비밀번호가 일치하지 않습니다."),
    USER_NOT_FOUND(404, "USER-006", "존재하지 않는 사용자입니다."),

    // Artist
    ARTIST_DUPLICATE_MEMBER(400, "ARTIST-001", "이미 초대한 멤버입니다."),
    ARTIST_NOT_FOUND(404, "ARTIST-002", "존재하지 않는 아티스트입니다.");

    private final int status;
    private final String code;
    private final String message;
}