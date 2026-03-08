package com.onwelo.votepanel.exception;

public class VoterNotFoundException extends RuntimeException {

    public VoterNotFoundException(long id) {
        super("Voter not found with id: " + id);
    }

    public VoterNotFoundException(String personalNumber) {
        super("Voter not found with personal number: " + personalNumber);
    }
}
