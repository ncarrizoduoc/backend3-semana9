package com.duoc.bff_banco_web.exception;

public class InvalidTokenException extends RuntimeException{
    
    public InvalidTokenException(String message){
        super(message);
    }

}
