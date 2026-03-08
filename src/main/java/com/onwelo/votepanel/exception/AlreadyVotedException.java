package com.onwelo.votepanel.exception;

public class AlreadyVotedException extends RuntimeException {
    public AlreadyVotedException(long id) {

        super("User with id: " + id + " already voted");
    }
}
