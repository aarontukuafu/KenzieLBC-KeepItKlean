package com.kenzie.appserver.service;

public class InvalidCustomerInputException extends RuntimeException{
    public InvalidCustomerInputException (String message){
        super(message);
    }
}
