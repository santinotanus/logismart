package com.logismart.domain.model;
public class Camion extends Vehiculo {
    public Camion(String patente) { super(patente, 5000.0); }
    @Override public String getTipo() { return "CAMION"; }
}