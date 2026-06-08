package com.logismart.application.service;

import com.logismart.domain.model.Envio;
import com.logismart.application.command.ColaComandos;
import com.logismart.application.command.Command;

public class LogisticaFacade {
    private ColaComandos cola;

    public LogisticaFacade() {
        this.cola = new ColaComandos();
    }

    public void procesarEnvio(ProcesoEnvio proceso, Envio envio) {
        proceso.procesarEnvioCompleto(envio);
    }

    public void ejecutarOperacion(Command cmd) {
        cola.ejecutar(cmd);
    }
}