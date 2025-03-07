package com.retoTecnico.cuentaMovimiento.http.response;


import com.retoTecnico.cuentaMovimiento.dto.ClienteDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteByNombreResponse {

    private ClienteDto clienteDto;

}
