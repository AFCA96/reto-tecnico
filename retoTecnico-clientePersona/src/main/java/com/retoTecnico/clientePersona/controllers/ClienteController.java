package com.retoTecnico.clientePersona.controllers;

import com.retoTecnico.clientePersona.entities.Cliente;
import com.retoTecnico.clientePersona.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> showAll(){
        return this.clienteService.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<?> findById (@PathVariable Long clienteId){
        Optional<Cliente> optionalCliente = this.clienteService.findByClienteId(clienteId);
        if (optionalCliente.isPresent()){
            return ResponseEntity.ok().body(optionalCliente.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save (@Valid @RequestBody Cliente cliente, BindingResult result){

        if (result.hasFieldErrors()){
            return this.validation(result);
        }

        Cliente newCliente = this.clienteService.save(cliente);
        return ResponseEntity.ok(newCliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<?> update (@PathVariable Long clienteId, @RequestBody Cliente cliente){
        Optional<Cliente> optionalClient = this.clienteService.update(clienteId, cliente);
        if (optionalClient.isPresent()){
            return ResponseEntity.ok().body(optionalClient.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<?> delete(@PathVariable Long clienteId){
        Optional<Cliente> optionalCliente = this.clienteService.delete(clienteId);
        if (optionalCliente.isPresent()){
            return ResponseEntity.ok().body(optionalCliente.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<?> findByNombre(@PathVariable String nombre){
        return ResponseEntity.ok().body(this.clienteService.findByNombre(nombre));
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
