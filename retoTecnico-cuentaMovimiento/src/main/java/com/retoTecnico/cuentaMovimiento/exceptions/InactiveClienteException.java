package com.retoTecnico.cuentaMovimiento.exceptions;

public class InactiveClienteException extends RuntimeException{

    public InactiveClienteException (String message) {
        super(message);
    }
}
