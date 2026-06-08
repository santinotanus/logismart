package com.logismart.infrastructure.visitor;
import com.logismart.domain.model.Envio;

public class CalculoImpuestosVisitor implements Visitor {
    @Override
    public void visit(Envio envio) {
        double impuestos = envio.getPeso() * 0.15;
        System.out.println("[Visitor] Calculando impuestos para " + envio.getId() + ": $" + impuestos);
    }
}