package com.retoTecnico.cuentaMovimiento.client;

import com.retoTecnico.cuentaMovimiento.dto.ClienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "micro-clientePersona", url = "http://localhost:8090/clientes")
public interface ClientePersonaClient {

    @GetMapping("/nombre/{nombre}")
    ClienteDto findByNombre(@PathVariable String nombre);
}
