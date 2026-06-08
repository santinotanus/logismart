package com.logismart.domain.model;
public abstract class Vehiculo {
    private String patente;
    private double capacidad;
    public Vehiculo(String p, double c) { this.patente = p; this.capacidad = c; }
    public abstract String getTipo();
}