package com.duoc.bff_banco_movil.exception;

public class InvalidTokenException extends RuntimeException{
    
    public InvalidTokenException(String message){
        super(message);
    }

}
