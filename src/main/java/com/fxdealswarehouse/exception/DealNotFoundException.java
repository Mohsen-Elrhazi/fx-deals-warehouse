package com.fxdealswarehouse.exception;

public class DealNotFoundException extends RuntimeException{
    public DealNotFoundException(String message) {
        super(message);
    }
}
