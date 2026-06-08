package com.logismart.application.chain;
import com.logismart.domain.model.Envio;

public abstract class Validator {
    protected Validator next;

    public void setNext(Validator next) { this.next = next; }

    public abstract boolean validate(Envio envio);

    protected boolean checkNext(Envio envio) {
        if (next == null) return true;
        return next.validate(envio);
    }
}