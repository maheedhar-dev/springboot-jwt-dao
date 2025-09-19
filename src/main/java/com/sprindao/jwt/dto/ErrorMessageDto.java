package com.sprindao.jwt.dto;

public class ErrorMessageDto {
    private String message;
    private String code;

    public ErrorMessageDto() {
    }

    public ErrorMessageDto(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ErrorMessageDto{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
