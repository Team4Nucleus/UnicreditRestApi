package com.unicredit.cap.exception;

public class FailedToLoginException extends Exception {

    public FailedToLoginException(String username) {
        super(username);
    }
}
