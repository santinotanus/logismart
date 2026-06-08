package com.logismart.application.service;
import com.logismart.domain.model.Envio;

public abstract class ProcesoEnvio {
    public final void procesarEnvioCompleto(Envio envio) {
        verificarDocumentacion(envio);
        calcularArancelesYCostos(envio);
        asignarTransporte(envio);
        emitirAlertaSalida(envio);
    }

    protected abstract void verificarDocumentacion(Envio envio);
    protected abstract void calcularArancelesYCostos(Envio envio);
    protected abstract void asignarTransporte(Envio envio);

    protected void emitirAlertaSalida(Envio envio) {
        System.out.println("[LogiSmart] Envío " + envio.getId() + " listo para despacho.");
    }
}