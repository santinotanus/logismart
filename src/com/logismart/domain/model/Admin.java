package com.logismart.domain.model;
public class Admin extends Usuario {
    public Admin(String id, String nombre) { super(id, nombre); }
    @Override public String getRol() { return "ADMIN"; }
}