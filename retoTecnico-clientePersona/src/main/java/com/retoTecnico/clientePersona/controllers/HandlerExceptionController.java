package com.retoTecnico.clientePersona.controllers;

import com.retoTecnico.clientePersona.exceptions.NotFoundClienteException;
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

    @ExceptionHandler({ NotFoundClienteException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> insufficientBalanceException(Exception ex) {

        Map<String, Object> error = new HashMap<>();

        error.put(FECHA, LocalDateTime.now());
        error.put(MENSAJE, ex.getMessage());

        return error;

    }
}
