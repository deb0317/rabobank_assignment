package com.raboBank.users.account.exception;

/**
 * Custom exception class for user account related exceptions.
 */
public class UserAccountException extends RuntimeException {

    /**
     * Constructs a new UserAccountException with the specified error message.
     *
     * @param message the error message
     */
    public UserAccountException(String message) {
        super(message);
    }
}
