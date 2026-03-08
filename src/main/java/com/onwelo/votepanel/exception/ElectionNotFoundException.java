package com.onwelo.votepanel.exception;

public class ElectionNotFoundException extends RuntimeException {
    public ElectionNotFoundException(long id) {
        super("Election not found with id: " + id);
    }
}
