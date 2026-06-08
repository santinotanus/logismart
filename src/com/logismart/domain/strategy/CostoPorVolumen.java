package com.logismart.domain.strategy;
import com.logismart.domain.model.Envio;

public class CostoPorVolumen implements EstrategiaCalculoCosto {
    @Override public String getNombre() { return "Costo por Volumen"; }
    @Override public double calcular(Envio e) { return e.getVolumen() * 250.0; }
}