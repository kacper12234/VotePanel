package com.onwelo.votepanel.exception;

public class ChoiceNotFoundException extends RuntimeException {
    public ChoiceNotFoundException(long id) {
        super("Choice not found with id: " + id);
    }
}
