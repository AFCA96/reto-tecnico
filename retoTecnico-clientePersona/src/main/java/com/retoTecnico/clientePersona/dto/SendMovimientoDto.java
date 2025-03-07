package com.retoTecnico.clientePersona.dto;

import java.io.Serializable;

public class SendMovimientoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long numeroCuenta;
    private String tipoMovimiento;
    private Double valor;

    public SendMovimientoDto() {}

    public SendMovimientoDto(long numeroCuenta, String tipoMovimiento, double valor) {
        this.numeroCuenta = numeroCuenta;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
    }

    public long getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "SendMovimientoDto{" +
                "numeroCuenta=" + numeroCuenta +
                ", tipoMovimiento='" + tipoMovimiento + '\'' +
                ", valor=" + valor +
                '}';
    }
}
