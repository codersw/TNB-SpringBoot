package com.simple.common.exception;

/**
 * 未授权异常
 * @author swen
 */
public class UnAuthorizedException extends RuntimeException {

    public UnAuthorizedException(String message) {
        super(message);
    }
}
