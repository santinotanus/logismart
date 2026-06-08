package com.logismart.domain.model;
public class Cliente extends Usuario {
    public Cliente(String id, String nombre) { super(id, nombre); }
    @Override public String getRol() { return "CLIENTE"; }
}