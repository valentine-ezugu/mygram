package com.valentine.service.exception;

public class MyValidationException extends RuntimeException {

    public MyValidationException(String image_or_video) {
        super(image_or_video);
    }
}
