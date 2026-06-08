package com.logismart.domain.model;

import com.logismart.domain.state.EstadoEnvio;
import com.logismart.domain.state.EstadoConfirmado;
import java.util.ArrayList;
import java.util.List;

public class Envio {
    private String id;
    private double peso;
    private double distanciaKm;
    private double volumen;
    private boolean esUrgente;
    private String destino;
    private EstadoEnvio estadoActual;

    public Envio(String id, double peso, String destino) {
        this.id = id;
        this.peso = peso;
        this.destino = destino;
        this.estadoActual = new EstadoConfirmado();
    }

    public String getId() { return id; }
    public double getPeso() { return peso; }
    public double getDistanciaKm() { return distanciaKm; }
    public double getVolumen() { return volumen; }
    public boolean isEsUrgente() { return esUrgente; }
    public String getDestino() { return destino; }
    public EstadoEnvio getEstadoActual() { return estadoActual; }

    public void setEstado(EstadoEnvio nuevoEstado) { this.estadoActual = nuevoEstado; }
    public void setDistanciaKm(double d) { this.distanciaKm = d; }
    public void setVolumen(double v) { this.volumen = v; }
    public void setEsUrgente(boolean u) { this.esUrgente = u; }

    public void validar() { estadoActual.validar(this); }
    public void cancelar() { estadoActual.cancelar(this); }
    public void ponerEnReparto() { estadoActual.ponerEnReparto(this); }
    public void entregar() { estadoActual.entregar(this); }
}