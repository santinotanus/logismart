package com.logismart.domain.model;
public class Auto extends Vehiculo {
    public Auto(String patente) { super(patente, 200.0); }
    @Override public String getTipo() { return "AUTO"; }
}