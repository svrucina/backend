package com.vrucina.test.controller.exceptions;

public class UserNotAuthorizedException extends RuntimeException{
    public UserNotAuthorizedException(){
        super("User is not authorized for this action.");
    }
}
