package com.logismart.domain.strategy;
import com.logismart.domain.model.Envio;

public class CostoPorUrgencia implements EstrategiaCalculoCosto {
    @Override public String getNombre() { return "Costo por Urgencia"; }
    @Override public double calcular(Envio e) { return e.isEsUrgente() ? 1500.0 : 350.0; }
}