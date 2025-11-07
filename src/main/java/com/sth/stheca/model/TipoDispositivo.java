package com.sth.stheca.model;

import java.io.Serializable;
import java.util.Objects;

public class TipoDispositivo implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private String descripcion;

    // Constructor sin-argumentos
    public TipoDispositivo() {
        this.descripcion = "";
    }

    // Constructor por nombre
    public TipoDispositivo(String nombre) {
        this.nombre = nombre;
        this.descripcion = "";
    }

    // Constructor completo
    public TipoDispositivo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = "";
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoDispositivo)) return false;
        TipoDispositivo that = (TipoDispositivo) o;
        return id == that.id && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    @Override
    public String toString() {
        return "TipoDispositivo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
