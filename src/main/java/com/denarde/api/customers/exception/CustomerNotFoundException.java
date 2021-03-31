package com.denarde.api.customers.exception;

import com.denarde.api.customers.exception.NotFoundException;

public class CustomerNotFoundException extends NotFoundException {

    private static final String MESSAGE = "Customer not found";

    public CustomerNotFoundException() {
        super(MESSAGE);
    }
}
