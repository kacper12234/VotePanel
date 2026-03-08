package com.onwelo.votepanel.exception;

public class ChoiceNotInElectionException extends RuntimeException {
    public ChoiceNotInElectionException(long id, long electionId) {
        super("Choice with id: " + id + " not in election with id: " + electionId);
    }
}
