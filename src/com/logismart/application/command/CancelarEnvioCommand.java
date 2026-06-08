package com.logismart.application.command;

import com.logismart.domain.model.Envio;
import com.logismart.domain.state.EstadoCancelado;
import com.logismart.domain.state.EstadoConfirmado;

public class CancelarEnvioCommand implements Command {
    private Envio envio;

    public CancelarEnvioCommand(Envio envio) {
        this.envio = envio;
    }

    @Override
    public void execute() {
        System.out.println("[Command] Cancelando envío: " + envio.getId());
        envio.setEstado(new EstadoCancelado());
    }

    @Override
    public void undo() {
        System.out.println("[Command] Revertiendo cancelación de envío: " + envio.getId());
        envio.setEstado(new EstadoConfirmado());
    }
}