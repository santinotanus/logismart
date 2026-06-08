package com.logismart.application.interpreter;
import com.logismart.domain.model.Envio;

public class AndExpression implements Expression {
    private Expression expr1;
    private Expression expr2;

    public AndExpression(Expression e1, Expression e2) {
        this.expr1 = e1;
        this.expr2 = e2;
    }

    @Override
    public boolean interpret(Envio envio) {
        return expr1.interpret(envio) && expr2.interpret(envio);
    }
}