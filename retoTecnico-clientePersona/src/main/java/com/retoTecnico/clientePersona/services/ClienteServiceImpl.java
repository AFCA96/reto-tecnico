package com.retoTecnico.clientePersona.services;

import com.retoTecnico.clientePersona.entities.Cliente;
import com.retoTecnico.clientePersona.exceptions.NotFoundClienteException;
import com.retoTecnico.clientePersona.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    private static final String MESSAGE = "No se pudo encontrar el cliente";

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) this.clienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> findByClienteId(Long clienteId) {
        Optional<Cliente> optionalCliente = this.clienteRepository.findById(clienteId);
        if (optionalCliente.isPresent()){
            return optionalCliente;
        }
        throw new NotFoundClienteException(MESSAGE);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public Optional<Cliente> update(Long clienteId, Cliente cliente) {
        Optional<Cliente> optionalCliente = this.clienteRepository.findByClienteId(clienteId);
        if (optionalCliente.isPresent()){
            Cliente updateCliente = optionalCliente.orElseThrow();
            updateCliente.setContrasenia(cliente.getContrasenia());
            updateCliente.setEstado(cliente.getEstado());
            updateCliente.setEdad(cliente.getEdad());
            updateCliente.setGenero(cliente.getGenero());
            updateCliente.setIdentificacion(cliente.getIdentificacion());
            updateCliente.setDireccion(cliente.getDireccion());
            updateCliente.setNombre(cliente.getNombre());
            updateCliente.setTelefono(cliente.getTelefono());
            return Optional.of(this.clienteRepository.save(updateCliente));
        }
        throw new NotFoundClienteException(MESSAGE);
    }

    @Override
    @Transactional
    public Optional<Cliente> delete(Long clienteId) {
        Optional<Cliente> optionalCliente = this.clienteRepository.findByClienteId(clienteId);
        if (optionalCliente.isPresent()){
            this.clienteRepository.delete(optionalCliente.orElseThrow());
        }
        throw new NotFoundClienteException(MESSAGE);
    }

    @Override
    public Cliente findByNombre(String nombre) {
        return this.clienteRepository.findByNombre(nombre);
    }
}
