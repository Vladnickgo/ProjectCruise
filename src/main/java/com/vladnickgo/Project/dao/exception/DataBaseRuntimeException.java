package com.vladnickgo.Project.dao.exception;

public class DataBaseRuntimeException extends RuntimeException{
    public DataBaseRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBaseRuntimeException(Throwable cause) {
        super(cause);
    }
}
