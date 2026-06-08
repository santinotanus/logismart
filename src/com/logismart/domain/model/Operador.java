package com.logismart.domain.model;
public class Operador extends Usuario {
    public Operador(String id, String nombre) { super(id, nombre); }
    @Override public String getRol() { return "OPERADOR"; }
}