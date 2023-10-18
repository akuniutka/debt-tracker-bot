package io.github.akuniutka.debttrackerbot.exception;

public class WrongAmountException extends RuntimeException {
    public WrongAmountException(String errorMessage) {
        super(errorMessage);
    }
}
