package com.retoTecnico.cuentaMovimiento.controllers;

import com.retoTecnico.cuentaMovimiento.dto.ClienteDto;
import com.retoTecnico.cuentaMovimiento.entities.Cuenta;
import com.retoTecnico.cuentaMovimiento.services.CuentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<Cuenta> showAll() {
        return this.cuentaService.findAll();
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<?> findByNumeroCuenta (@PathVariable Long numeroCuenta){
        Optional<Cuenta> optionalCuenta = this.cuentaService.findByNumeroCuenta(numeroCuenta);
        if (optionalCuenta.isPresent()){
            return ResponseEntity.ok().body(optionalCuenta.orElseThrow());
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Cuenta cuenta, BindingResult result){

        if (result.hasFieldErrors()){
            return this.validation(result);
        }

        Cuenta newCuenta = this.cuentaService.save(cuenta);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCuenta);
    }

    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<?> update (@Valid @RequestBody Cuenta cuenta, BindingResult result , @PathVariable Long numeroCuenta){

        if (result.hasFieldErrors()){
            return this.validation(result);
        }

        Optional<Cuenta> optionalCuenta = this.cuentaService.update(numeroCuenta, cuenta);
        if (optionalCuenta.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(optionalCuenta.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<?> delete (@PathVariable Long numeroCuenta){
        Optional<Cuenta> optionalCuenta = this.cuentaService.delete(numeroCuenta);
        if (optionalCuenta.isPresent()){
            return ResponseEntity.ok().body(optionalCuenta.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cliente/{nombre}")
    public ResponseEntity<?> findClienteByName(@PathVariable String nombre){
        ClienteDto clienteDto = this.cuentaService.findClienteByNombre(nombre).getClienteDto();
        if (clienteDto != null){
            return ResponseEntity.ok().body(clienteDto);
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
