package com.azienda.esercizioSpringWebData.exception;


public class ProdottoGiaEsistenteException extends RuntimeException {
    public ProdottoGiaEsistenteException(String message) {
        super(message);
    }
}