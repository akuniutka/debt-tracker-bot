package dev.akuniutka.debttracker.exception;

public class WrongAmountException extends RuntimeException {
    public WrongAmountException(String errorMessage) {
        super(errorMessage);
    }
}
