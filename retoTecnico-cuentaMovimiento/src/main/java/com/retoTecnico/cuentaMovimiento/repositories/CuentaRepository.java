package com.retoTecnico.cuentaMovimiento.repositories;

import com.retoTecnico.cuentaMovimiento.entities.Cuenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Long>{

    Optional<Cuenta> findByNumeroCuenta(Long numeroCuenta);

}
