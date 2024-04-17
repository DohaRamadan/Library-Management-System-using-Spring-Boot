package org.example.lms.exceptions;

public enum HTTPStatusCodesEnum {
    OK("200"),
    BAD_REQUEST("400"),
    UNAUTHORIZED("401"),
    FORBIDDEN("403"),
    INTERNAL_SERVER_ERROR("500"),
    NOT_FOUND("404"),
    ACCEPTED("202"),
    CONFLICT("409");

    private String httpStatusCode;

    private HTTPStatusCodesEnum(String httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getHttpStatusCode() {
        return this.httpStatusCode;
    }
}
