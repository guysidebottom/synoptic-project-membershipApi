package com.springjpa.springpostgresjpa.exception;

public class EntityCreationException extends RuntimeException {

    public EntityCreationException(String msg, Object... args) {
        super(String.format(msg, args));
    }
}
