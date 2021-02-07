package com.netty.demo.exceptions;

public class FilterException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private int type;
    private String message;

    public FilterException(int type, String message){
        this.type = type;
        this.message = message;
    }
}
