package com.azienda.esercizioSpringWebData.exception;

public class UtenteNonTrovatoException extends RuntimeException {
    public UtenteNonTrovatoException(String message) {
        super(message);
    }
}