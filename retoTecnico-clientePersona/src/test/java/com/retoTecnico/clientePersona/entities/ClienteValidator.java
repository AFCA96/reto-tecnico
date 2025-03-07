package com.retoTecnico.clientePersona.entities;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ClienteValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Cliente.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Cliente cliente = (Cliente) target;

        if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
            errors.rejectValue("nombre", "NotBlank.cliente.nombre", "El nombre no puede estar vacío");
        }

        if (cliente.getEdad() != null && cliente.getEdad() < 18) {
            errors.rejectValue("edad", "Min.cliente.edad", "La edad debe ser al menos 18");
        }

        if (cliente.getContrasenia() == null || cliente.getContrasenia().length() < 5) {
            errors.rejectValue("contrasenia", "Length.cliente.contrasenia", "La contraseña debe tener al menos 5 caracteres");
        }
    }
}
