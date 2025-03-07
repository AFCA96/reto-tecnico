package com.retoTecnico.clientePersona.entities;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

class ClienteValidationTest {

    private Validator validator;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        validator = new ClienteValidator();
        cliente = new Cliente();
    }

    @Test
    void testValidCliente() {
        cliente.setIdentificacion(231561L);
        cliente.setNombre("Aldo Carrillo");
        cliente.setEdad(25);
        cliente.setGenero("Masculino");
        cliente.setDireccion("Calle de prueba");
        cliente.setTelefono("23123186489");
        cliente.setContrasenia("12345");

        Errors errors = new BeanPropertyBindingResult(cliente, "cliente");
        validator.validate(cliente, errors);

        assertFalse(errors.hasErrors(), "No debe haber errores de validación");
    }

    @Test
    void testInvalidClienteSinNombre() {
        cliente.setIdentificacion(231561L);
        cliente.setNombre("");
        cliente.setEdad(25);
        cliente.setGenero("Masculino");
        cliente.setDireccion("Calle de prueba");
        cliente.setTelefono("23123186489");
        cliente.setContrasenia("12345");

        Errors errors = new BeanPropertyBindingResult(cliente, "cliente");
        validator.validate(cliente, errors);

        assertTrue(errors.hasFieldErrors("nombre"), "Nombre no debe ser vacio ni nulo");
    }

    @Test
    void testInvalidClienteEdadInvalida() {
        cliente.setIdentificacion(231561L);
        cliente.setNombre("Aldo Carrillo");
        cliente.setEdad(15);
        cliente.setGenero("Masculino");
        cliente.setDireccion("Calle de prueba");
        cliente.setTelefono("23123186489");
        cliente.setContrasenia("12345");

        Errors errors = new BeanPropertyBindingResult(cliente, "cliente");
        validator.validate(cliente, errors);

        assertTrue(errors.hasFieldErrors("edad"), "Edad debe ser igual o mayor a 18");
    }

    @Test
    void testInvalidClienteContrasenia() {
        cliente.setIdentificacion(123456789L);
        cliente.setNombre("Aldo Carrillo");
        cliente.setEdad(25);
        cliente.setGenero("Masculino");
        cliente.setDireccion("Calle de prueba");
        cliente.setTelefono("23123186489");
        cliente.setContrasenia("123");

        Errors errors = new BeanPropertyBindingResult(cliente, "cliente");
        validator.validate(cliente, errors);

        assertTrue(errors.hasFieldErrors("contrasenia"), "La contrasenia debe tener al menos 5 caracteres");
    }

    @Test
    void testPrePersistEstadoPorDefecto() {
        Cliente cliente = new Cliente();
        cliente.setEstado(false);
        cliente.prePersist();

        assertTrue(cliente.getEstado(), "El estado debe ser verdadero al crear un Cliente");
    }


}
