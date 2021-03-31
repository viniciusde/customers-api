package com.denarde.api.customers.exception;

public class CustomerAlreadyExistsException extends Exception {

    private static final String MESSAGE = "Customer already exists";

    public CustomerAlreadyExistsException(){
        super(MESSAGE);
    }

}
