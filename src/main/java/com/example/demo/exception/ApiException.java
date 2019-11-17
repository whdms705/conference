package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST , reason = "bad request")
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = -5540112835469012749L;

    private HttpStatus httpStatus;
    String exceptionCode;
    Object exceptioninfo;
    boolean logable;

    public ApiException(String message,HttpStatus httpStatus,String exceptionCode,Object exceptioninfo){
        super(message);
        this.logable =true;
        this.setExceptionCode(exceptionCode);
        this.setExceptioninfo(exceptioninfo);
        this.setHttpStatus(httpStatus);

    }

    public ApiException(String message,HttpStatus httpStatus,String exceptionCode){
        this(message,httpStatus,exceptionCode,null);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public Object getExceptioninfo() {
        return exceptioninfo;
    }

    public void setExceptioninfo(Object exceptioninfo) {
        this.exceptioninfo = exceptioninfo;
    }
}
