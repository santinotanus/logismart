package com.logismart.application.chain;
import com.logismart.domain.model.Envio;

public class PesoValidator extends Validator {
    @Override
    public boolean validate(Envio envio) {
        if (envio.getPeso() > 10000) {
            System.out.println("[Chain] Error: Envío demasiado pesado.");
            return false;
        }
        return checkNext(envio);
    }
}