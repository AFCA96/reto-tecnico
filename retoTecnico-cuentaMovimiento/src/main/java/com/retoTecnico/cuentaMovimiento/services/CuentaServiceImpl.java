package com.retoTecnico.cuentaMovimiento.services;

import com.retoTecnico.cuentaMovimiento.client.ClientePersonaClient;
import com.retoTecnico.cuentaMovimiento.dto.ClienteDto;
import com.retoTecnico.cuentaMovimiento.entities.Cuenta;
import com.retoTecnico.cuentaMovimiento.exceptions.InactiveClienteException;
import com.retoTecnico.cuentaMovimiento.exceptions.NotFoundClienteException;
import com.retoTecnico.cuentaMovimiento.exceptions.NotFoundCuentaException;
import com.retoTecnico.cuentaMovimiento.http.response.ClienteByNombreResponse;
import com.retoTecnico.cuentaMovimiento.repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClientePersonaClient clientePersonaClient;

    @Override
    @Transactional(readOnly = true)
    public List<Cuenta> findAll() {
        return (List<Cuenta>) this.cuentaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cuenta> findByNumeroCuenta(Long numeroCuenta) {
        Optional<Cuenta> optionalCuenta = this.cuentaRepository.findByNumeroCuenta(numeroCuenta);
        if (optionalCuenta.isPresent()){
            return optionalCuenta;
        }
        throw new NotFoundCuentaException("No se encontro la cuenta especificada");
    }

    @Override
    @Transactional
    public Cuenta save(Cuenta cuenta) {
        ClienteDto clienteDto = this.findClienteByNombre(cuenta.getCliente()).getClienteDto();
        if (clienteDto != null) {
            if (!clienteDto.isEstado()) {
                throw new InactiveClienteException("No se puede crear una cuenta para un cliente inactivo");
            }
            return this.cuentaRepository.save(cuenta);
        }

        throw new NotFoundClienteException("el cliente no existe");
    }

    @Override
    @Transactional
    public Optional<Cuenta> update(Long numeroCuenta, Cuenta cuenta) {
        Optional<Cuenta> optionalCuenta = this.cuentaRepository.findByNumeroCuenta(numeroCuenta);
        if (optionalCuenta.isPresent()) {
            Cuenta updateCuenta = optionalCuenta.orElseThrow();
            updateCuenta.setTipoCuenta(cuenta.getTipoCuenta());
            updateCuenta.setSaldoInicial(cuenta.getSaldoInicial());
            updateCuenta.setEstado(cuenta.getEstado());
            updateCuenta.setCliente(cuenta.getCliente());
            return Optional.of(this.cuentaRepository.save(updateCuenta));
        }
        return optionalCuenta;
    }

    @Override
    @Transactional
    public Optional<Cuenta> delete(Long numeroCuenta) {
        Optional<Cuenta> optionalCuenta = this.cuentaRepository.findByNumeroCuenta(numeroCuenta);
        if (optionalCuenta.isPresent()){
            this.cuentaRepository.delete(optionalCuenta.orElseThrow());
            return optionalCuenta;
        }
        throw new NotFoundCuentaException("No se encontro la cuenta especificada");
    }

    @Override
    public ClienteByNombreResponse findClienteByNombre(String nombre) {
        ClienteDto clienteDto = this.clientePersonaClient.findByNombre(nombre);
        return ClienteByNombreResponse.builder()
                .clienteDto(clienteDto)
                .build();
    }

}
