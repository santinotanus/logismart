// EstrategiaCalculoCosto.java
package com.logismart.domain.strategy;
import com.logismart.domain.model.Envio;

public interface EstrategiaCalculoCosto {
    double calcular(Envio envio);
    String getNombre();
}