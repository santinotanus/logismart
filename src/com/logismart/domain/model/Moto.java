package com.logismart.domain.model;
public class Moto extends Vehiculo {
    public Moto(String patente) { super(patente, 30.0); }
    @Override public String getTipo() { return "MOTO"; }
}