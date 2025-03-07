package com.retoTecnico.cuentaMovimiento.controllers;

import com.retoTecnico.cuentaMovimiento.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerExceptionController {

    public static final String FECHA = "fecha";
    public static final String MENSAJE = "mensaje";

    @ExceptionHandler({ InsufficientBalanceException.class,
                        EmptyNumeroCuentaException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> insufficientBalanceException(Exception ex) {

        Map<String, Object> error = new HashMap<>();

        error.put(FECHA, LocalDateTime.now());
        error.put(MENSAJE, ex.getMessage());

        return error;

    }

    @ExceptionHandler({NotFoundMovimientoException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> notFoundMovimientoException(Exception ex) {

        Map<String, Object> error = new HashMap<>();

        error.put(FECHA, LocalDateTime.now());
        error.put(MENSAJE, ex.getMessage());

        return error;

    }

    @ExceptionHandler({NotFoundCuentaException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> notFoundCuentaException(Exception ex) {

        Map<String, Object> error = new HashMap<>();

        error.put(FECHA, LocalDateTime.now());
        error.put(MENSAJE, ex.getMessage());

        return error;

    }

    @ExceptionHandler({NotFoundClienteException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> notFoundClienteException(Exception ex) {

        Map<String, Object> error = new HashMap<>();

        error.put(FECHA, LocalDateTime.now());
        error.put(MENSAJE, ex.getMessage());

        return error;

    }

    @ExceptionHandler({InactiveClienteException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> inactiveClienteException(Exception ex) {

        Map<String, Object> error = new HashMap<>();

        error.put(FECHA, LocalDateTime.now());
        error.put(MENSAJE, ex.getMessage());

        return error;

    }

}
