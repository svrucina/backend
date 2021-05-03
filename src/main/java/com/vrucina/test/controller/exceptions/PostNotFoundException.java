package com.vrucina.test.controller.exceptions;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(Long id){
        super("Could not find post with ID " + id);
    }
}
