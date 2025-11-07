package com.sth.stheca.model;

public class Dispositivo {
    protected String nombre;
    protected int id;
    protected TipoDispositivo tipo;

    public Dispositivo(int id, String nombre, TipoDispositivo tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoDispositivo getTipo() {
        return tipo;
    }

    public void setId(int i) {
        this.id = i;
    }

}
