package com.valentine.utility.exception;

public class MyFileStorageException extends RuntimeException  {

    public MyFileStorageException(String message) {
        super(message);
    }

    public MyFileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
