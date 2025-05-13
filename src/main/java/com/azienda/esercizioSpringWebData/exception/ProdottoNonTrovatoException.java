package com.azienda.esercizioSpringWebData.exception;

public class ProdottoNonTrovatoException extends RuntimeException {
    public ProdottoNonTrovatoException(String message) {
        super(message);
    }
}