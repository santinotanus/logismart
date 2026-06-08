package com.logismart.domain.model;

public abstract class Usuario {
    private String id;
    private String nombre;

    public Usuario(String id, String nombre) { 
        this.id = id; 
        this.nombre = nombre; 
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }

    public abstract String getRol();
}