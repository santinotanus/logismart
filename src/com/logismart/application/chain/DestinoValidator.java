package com.logismart.application.chain;
import com.logismart.domain.model.Envio;

public class DestinoValidator extends Validator {
    @Override
    public boolean validate(Envio envio) {
        if (envio.getDestino() == null || envio.getDestino().isEmpty()) {
            System.out.println("[Chain] Error: Destino no válido.");
            return false;
        }
        return checkNext(envio);
    }
}