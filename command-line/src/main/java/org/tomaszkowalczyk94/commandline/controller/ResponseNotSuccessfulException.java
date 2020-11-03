package org.tomaszkowalczyk94.commandline.controller;

class ResponseNotSuccessfulException extends RuntimeException {
    public ResponseNotSuccessfulException() {
    }

    public ResponseNotSuccessfulException(String message) {
        super(message);
    }

    public ResponseNotSuccessfulException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseNotSuccessfulException(Throwable cause) {
        super(cause);
    }
}
