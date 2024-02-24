package com.hcl.personservice.exception;

public class APIError {
    private String errorCode;
    private String errorDescription;
    private String httpStatusCode;
    private String httpStatusDescription;
    private String reason;

    public APIError() {
    }

    public APIError(String errorCode, String errorDescription, String httpStatusCode, String httpStatusDescription, String reason) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.httpStatusCode = httpStatusCode;
        this.httpStatusDescription = httpStatusDescription;
        this.reason = reason;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(String httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getHttpStatusDescription() {
        return httpStatusDescription;
    }

    public void setHttpStatusDescription(String httpStatusDescription) {
        this.httpStatusDescription = httpStatusDescription;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
