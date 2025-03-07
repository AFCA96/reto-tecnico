package com.retoTecnico.cuentaMovimiento.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @Column(name = "numero_cuenta")
    private Long numeroCuenta;

    @Column(name = "tipo_cuenta")
    @NotBlank(message = "{NotNull.cuenta.tipoCuenta}")
    private String tipoCuenta;

    @Column(name = "saldo_inicial")
    @Min(value = 0, message = "{Min.cuenta.saldo}")
    private Double saldoInicial;

    private boolean estado;

    @Size(min = 5, max = 100, message = "{Size.cuenta.cliente}")
    private String cliente;

    public Cuenta(){}

    public Cuenta(Long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }



    @PrePersist
    private void prePersist(){
        this.estado = true;
    }

    public Long getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public Double getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(Double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

}
