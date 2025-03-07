package com.retoTecnico.cuentaMovimiento.services;

import com.retoTecnico.cuentaMovimiento.dto.RegistroDto;
import com.retoTecnico.cuentaMovimiento.entities.Movimiento;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovimientoService {

    List<Movimiento> findAll();

    Optional<Movimiento> findById(Long id);

    Optional<Movimiento> save(Movimiento movimiento);

    Optional<Movimiento> update (Long id, Movimiento movimiento);

    Optional<Movimiento> delete(Long id);

    List<RegistroDto> showMovimientosByFechaAndCliente(LocalDate fechaInicio, LocalDate fechaFin, String cliente);

}
