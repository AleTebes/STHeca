package com.sth.stheca.model;

public class Ticket {
    private int id;
    private String titulo;
    private String descripcion;
    private String estado;
    private Dispositivo dispositivo;
    private Usuario usuario;

    public Ticket(String titulo, String descripcion, String estado, Dispositivo dispositivo, Usuario usuario) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.dispositivo = dispositivo;
        this.usuario = usuario;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

}
