package br.com.dicas.api_test.services.exceptions;

public class DataIntegrateViolationException extends RuntimeException{

    public DataIntegrateViolationException(String message) {
        super(message);
    }

}
