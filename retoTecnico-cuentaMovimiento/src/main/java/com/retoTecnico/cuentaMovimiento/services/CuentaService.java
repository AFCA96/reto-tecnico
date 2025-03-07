package com.retoTecnico.cuentaMovimiento.services;

import com.retoTecnico.cuentaMovimiento.entities.Cuenta;
import com.retoTecnico.cuentaMovimiento.http.response.ClienteByNombreResponse;

import java.util.List;
import java.util.Optional;

public interface CuentaService {

    List<Cuenta> findAll();

    Optional<Cuenta> findByNumeroCuenta(Long numeroCuenta);

    Cuenta save(Cuenta cuenta);

    Optional<Cuenta> update (Long numeroCuenta, Cuenta cuenta);

    Optional<Cuenta> delete(Long numeroCuenta);

    ClienteByNombreResponse findClienteByNombre(String nombre);
}
