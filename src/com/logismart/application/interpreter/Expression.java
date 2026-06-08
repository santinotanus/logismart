package com.logismart.application.interpreter;
import com.logismart.domain.model.Envio;

public interface Expression {
    boolean interpret(Envio envio);
}