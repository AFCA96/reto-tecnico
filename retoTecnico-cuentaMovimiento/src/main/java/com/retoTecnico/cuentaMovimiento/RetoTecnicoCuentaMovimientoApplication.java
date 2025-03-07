package com.retoTecnico.cuentaMovimiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.retoTecnico.cuentaMovimiento.client")
@SpringBootApplication
public class RetoTecnicoCuentaMovimientoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetoTecnicoCuentaMovimientoApplication.class, args);
	}

}
