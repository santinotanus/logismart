package com.logismart.application.command;

import com.logismart.domain.model.Envio;
import com.logismart.domain.state.EstadoEnTransito;
import com.logismart.domain.state.EstadoConfirmado;

public class IniciarViajeCommand implements Command {
    private Envio envio;

    public IniciarViajeCommand(Envio envio) {
        this.envio = envio;
    }

    @Override
    public void execute() {
        System.out.println("[Command] Iniciando viaje para envío: " + envio.getId());
        envio.setEstado(new EstadoEnTransito());
    }

    @Override
    public void undo() {
        System.out.println("[Command] Deshaciendo inicio de viaje: " + envio.getId());
        envio.setEstado(new EstadoConfirmado());
    }
}