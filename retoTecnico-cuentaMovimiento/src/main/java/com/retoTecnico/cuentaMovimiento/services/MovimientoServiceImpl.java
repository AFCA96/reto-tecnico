package com.retoTecnico.cuentaMovimiento.services;

import com.retoTecnico.cuentaMovimiento.dto.RegistroDto;
import com.retoTecnico.cuentaMovimiento.dto.SendMovimientoDto;
import com.retoTecnico.cuentaMovimiento.entities.Cuenta;
import com.retoTecnico.cuentaMovimiento.entities.Movimiento;
import com.retoTecnico.cuentaMovimiento.exceptions.EmptyNumeroCuentaException;
import com.retoTecnico.cuentaMovimiento.exceptions.InsufficientBalanceException;
import com.retoTecnico.cuentaMovimiento.exceptions.NotFoundMovimientoException;
import com.retoTecnico.cuentaMovimiento.mq.publisher.Publisher;
import com.retoTecnico.cuentaMovimiento.repositories.MovimientoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoServiceImpl implements MovimientoService{

    private final Logger logger = LoggerFactory.getLogger(MovimientoServiceImpl.class);

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private Publisher publisher;

    @Override
    @Transactional(readOnly = true)
    public List<Movimiento> findAll() {
        return (List<Movimiento>) this.movimientoRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Movimiento> findById(Long id) {
        Optional<Movimiento> optionalMovimiento = this.movimientoRepository.findById(id);
        if (optionalMovimiento.isPresent()){
            return optionalMovimiento;
        }
        throw new NotFoundMovimientoException("No se encontro el movimiento");
    }

    @Override
    @Transactional
    public Optional<Movimiento> save(Movimiento movimiento) {
        movimiento.setFecha(LocalDateTime.now());
        Optional<Cuenta> optionalCuenta = this.cuentaService.findByNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
        if (optionalCuenta.isPresent()){
            Cuenta cuenta = optionalCuenta.orElseThrow();
            Double saldoTotal = cuenta.getSaldoInicial() + movimiento.getValor();
            if (saldoTotal < 0) {
                throw new InsufficientBalanceException("Saldo no disponible");
            }
            if (!cuenta.getEstado()){
                throw new EmptyNumeroCuentaException("No se puede realizar la transaccion porque la cuenta no existe o no esta activa");
            }
            movimiento.setSaldo(saldoTotal);
            cuenta.setSaldoInicial(saldoTotal);
            this.cuentaService.update(cuenta.getNumeroCuenta(), cuenta);
            Movimiento saveMovimiento = this.movimientoRepository.save(movimiento);
            SendMovimientoDto message = new SendMovimientoDto(movimiento.getCuenta().getNumeroCuenta(), movimiento.getTipoMovimiento(), movimiento.getValor());
            logger.info("Mensaje a enviar {} ", message);
            this.publisher.send(message);
            return Optional.of(saveMovimiento);
        }
        throw new EmptyNumeroCuentaException("No se puede realizar la transaccion porque la cuenta no existe o no esta activa");

    }

    @Override
    @Transactional
    public Optional<Movimiento> update(Long id, Movimiento movimiento) {
        Optional<Movimiento> optionalMovimiento = this.movimientoRepository.findById(id);
        if (optionalMovimiento.isPresent()) {
            Movimiento updateMovimiento = optionalMovimiento.orElseThrow();
            updateMovimiento.setCuenta(movimiento.getCuenta());
            updateMovimiento.setFecha(LocalDateTime.now());
            updateMovimiento.setTipoMovimiento(movimiento.getTipoMovimiento());
            updateMovimiento.setValor(movimiento.getValor());
            return Optional.of(this.movimientoRepository.save(updateMovimiento));
        }
        throw new NotFoundMovimientoException("No se encontro el movimiento");
    }

    @Override
    @Transactional
    public Optional<Movimiento> delete(Long id) {
        Optional<Movimiento> optionalMovimiento = this.movimientoRepository.findById(id);
        if (optionalMovimiento.isPresent()) {
            this.movimientoRepository.delete(optionalMovimiento.orElseThrow());
            return optionalMovimiento;
        }
        throw new NotFoundMovimientoException("No se encontro el movimiento");
    }

    @Override
    @Transactional(readOnly = true)
    public List<RegistroDto> showMovimientosByFechaAndCliente(LocalDate fechaInicio, LocalDate fechaFin, String cliente) {
        return this.movimientoRepository.showMovimientosByFechaAndCliente(fechaInicio, fechaFin, cliente);
    }
}
