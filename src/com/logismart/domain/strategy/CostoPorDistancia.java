package com.logismart.domain.strategy;
import com.logismart.domain.model.Envio;

public class CostoPorDistancia implements EstrategiaCalculoCosto {
    @Override public String getNombre() { return "Costo por Distancia"; }
    @Override public double calcular(Envio e) { return e.getDistanciaKm() * 12.5; }
}