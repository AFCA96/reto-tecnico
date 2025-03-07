package com.retoTecnico.cuentaMovimiento.repositories;

import com.retoTecnico.cuentaMovimiento.dto.RegistroDto;
import com.retoTecnico.cuentaMovimiento.entities.Movimiento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface MovimientoRepository extends CrudRepository<Movimiento, Long> {


    @Query("select new com.retoTecnico.cuentaMovimiento.dto.RegistroDto(m.fecha, m.cuenta.cliente, m.cuenta.numeroCuenta, m.cuenta.tipoCuenta, m.cuenta.saldoInicial, m.cuenta.estado, m.valor, m.saldo) from Movimiento m where FUNCTION('DATE', m.fecha) between ?1 and ?2 and m.cuenta.cliente = ?3 order by m.id desc")
    List<RegistroDto> showMovimientosByFechaAndCliente(LocalDate fechaInicio, LocalDate fechaFin, String cliente);

}
