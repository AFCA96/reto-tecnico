package com.retoTecnico.clientePersona.repositories;

import com.retoTecnico.clientePersona.entities.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    Optional<Cliente> findByClienteId(Long clienteId);

    Cliente findByNombre(String nombre);

}
