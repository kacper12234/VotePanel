package com.onwelo.votepanel.exception;

public class VoterBlockedException extends RuntimeException {

    public VoterBlockedException(long id) {
        super("Voter blocked with id: " + id);
    }
}
