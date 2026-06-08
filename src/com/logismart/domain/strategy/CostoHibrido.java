package com.logismart.domain.strategy;
import com.logismart.domain.model.Envio;

public class CostoHibrido implements EstrategiaCalculoCosto {
    @Override public String getNombre() { return "Costo Híbrido"; }
    @Override public double calcular(Envio e) {
        return (e.getDistanciaKm() * 5.0) + (e.getPeso() * 15.0) + (e.isEsUrgente() ? 500.0 : 0.0);
    }
}