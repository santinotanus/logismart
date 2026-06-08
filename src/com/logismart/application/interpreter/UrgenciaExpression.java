package com.logismart.application.interpreter;
import com.logismart.domain.model.Envio;

public class UrgenciaExpression implements Expression {
    @Override
    public boolean interpret(Envio envio) {
        return envio.isEsUrgente();
    }
}