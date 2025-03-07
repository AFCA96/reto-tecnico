package com.retoTecnico.clientePersona.services;

import com.retoTecnico.clientePersona.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<Cliente> findAll();

    Optional<Cliente> findByClienteId(Long clienteId);

    Cliente save(Cliente cliente);

    Optional<Cliente> update(Long clienteId, Cliente cliente);

    Optional<Cliente> delete(Long clienteId);

    Cliente findByNombre(String nombre);

}
