package com.azienda.esercizioSpringWebData.exception;

public class NomeProdottoNonValidoException extends RuntimeException {
    public NomeProdottoNonValidoException(String message) {
        super(message);
    }
}