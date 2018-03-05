package com.myview.exception;

/**
 * @author liangjun on 2018/3/5.
 */

public class MyException extends RuntimeException {


    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}
