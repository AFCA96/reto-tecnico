package com.retoTecnico.cuentaMovimiento.dto;

public class ClienteDto {

    private Long indentificacion;
    private String nombre;
    private String genero;
    private Integer edad;
    private String direccion;
    private String telefono;
    private Long clienteId;
    private boolean estado;

    public Long getIndentificacion() {
        return indentificacion;
    }

    public void setIndentificacion(Long indentificacion) {
        this.indentificacion = indentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
