package com.retoTecnico.cuentaMovimiento.controllers;


import com.retoTecnico.cuentaMovimiento.dto.RegistroDto;
import com.retoTecnico.cuentaMovimiento.entities.Movimiento;
import com.retoTecnico.cuentaMovimiento.services.MovimientoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public List<Movimiento> findAllMovimientos() {
        return this.movimientoService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findMovimientoById(@PathVariable Long id){
        Optional<Movimiento> optionalMovimiento = this.movimientoService.findById(id);
        if (optionalMovimiento.isPresent()){
            return ResponseEntity.ok().body(optionalMovimiento.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/reportes")
    public List<RegistroDto> showRecordByStartDateEndDateAndClient(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
            @RequestParam("cliente") String cliente){

            return this.movimientoService.showMovimientosByFechaAndCliente(fechaInicio, fechaFin, cliente);
    }

    @PostMapping
    public ResponseEntity<?> registryMovimiento(@Valid @RequestBody Movimiento movimiento, BindingResult result){
        if (result.hasFieldErrors()){
            return this.validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.movimientoService.save(movimiento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovimiemto (@Valid @RequestBody Movimiento movimiento, BindingResult result, @PathVariable Long id){
        if (result.hasFieldErrors()){
            return this.validation(result);
        }

        Optional<Movimiento> optionalMovimiento = this.movimientoService.update(id, movimiento);
        if (optionalMovimiento.isPresent()){
            return ResponseEntity.ok().body(optionalMovimiento.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovimiemto(@PathVariable Long id){
        Optional<Movimiento> optionalMovimiento = this.movimientoService.delete(id);
        if (optionalMovimiento.isPresent()){
            return ResponseEntity.ok().body(optionalMovimiento.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }


}
