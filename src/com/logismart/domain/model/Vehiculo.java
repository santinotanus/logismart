package com.logismart.domain.model;

public abstract class Vehiculo {
    private String patente;
    private double capacidad;

    public Vehiculo(String p, double c) { 
        this.patente = p; 
        this.capacidad = c; 
    }

    public String getPatente() { return patente; }
    public double getCapacidad() { return capacidad; }

    public abstract String getTipo();
}