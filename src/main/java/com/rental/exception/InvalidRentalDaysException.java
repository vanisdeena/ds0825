package com.rental.exception;

public class InvalidRentalDaysException extends RuntimeException {
    public InvalidRentalDaysException(int days) {
        super("Rental day count must be 1 or greater. Given: " + days);
    }
}
