package com.example.Task;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {
        super("Do not provide task ID.");
    }
}
