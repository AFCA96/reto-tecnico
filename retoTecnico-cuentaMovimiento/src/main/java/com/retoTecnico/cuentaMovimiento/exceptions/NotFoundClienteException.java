package com.retoTecnico.cuentaMovimiento.exceptions;

public class NotFoundClienteException extends RuntimeException {

    public NotFoundClienteException (String message) {
        super(message);
    }

}
