package com.pduda.tourney.domain.repository;


public class RepoException extends Exception {

    public RepoException(Throwable cause) {
        super(cause);
    }

    public RepoException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepoException(String message) {
        super(message);
    }

    public RepoException() {
    }


}