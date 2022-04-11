package com.grizzly.subscription.matcher.exceptions;

public class RatingNotFoundException extends RuntimeException {
    public RatingNotFoundException(Long id) {
        super("Could not find rating " + id);
    }
}
