package com.example.RedisDemo.exception;

public class RedisException extends Exception {

    private static final long serialVersionUID = 1L;

    public RedisException(String msg) {
        super(msg);
    }

    public RedisException(String msg, Throwable tx) {
        super(msg, tx);
    }

}