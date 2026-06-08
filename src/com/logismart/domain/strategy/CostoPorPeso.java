package com.logismart.domain.strategy;
import com.logismart.domain.model.Envio;

public class CostoPorPeso implements EstrategiaCalculoCosto {
    @Override public String getNombre() { return "Costo por Peso"; }
    @Override public double calcular(Envio e) { return e.getPeso() * 45.0; }
}