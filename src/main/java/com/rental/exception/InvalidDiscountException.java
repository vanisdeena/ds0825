package com.rental.exception;

public class InvalidDiscountException extends RuntimeException {
    public InvalidDiscountException(int discount) {
        super("Discount percent must be between 0 and 100. Given: " + discount + "%");
    }
}
