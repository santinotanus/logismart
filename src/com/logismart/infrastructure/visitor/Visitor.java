package com.logismart.infrastructure.visitor;
import com.logismart.domain.model.Envio;

public interface Visitor {
    void visit(Envio envio);
}