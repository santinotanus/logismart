package com.logismart.application.mediator;

import com.logismart.domain.model.Envio;
import com.logismart.infrastructure.observer.SistemaAuditoria;
import com.logismart.infrastructure.observer.Notificador;

public class MediadorEnviosConcreto implements Mediator {
    private SistemaAuditoria auditoria;
    private Notificador notificador;

    public MediadorEnviosConcreto(SistemaAuditoria auditoria, Notificador notificador) {
        this.auditoria = auditoria;
        this.notificador = notificador;
    }

    @Override
    public void notify(Object sender, String event, Object data) {
        if (event.equals("VALIDACION_OK")) {
            System.out.println("[Mediator] Validación aprobada. Procediendo con el flujo.");
            notificador.enviarNotificacion("Envío validado exitosamente.");
        } 
        else if (event.equals("VALIDACION_FALLIDA")) {
            Envio envio = (Envio) data;
            System.out.println("[Mediator] ALERTA: Validación fallida para el envío " + envio.getId());
            auditoria.registrarEvento("FALLO_VALIDACION", "Envío " + envio.getId() + " bloqueado.");
            notificador.enviarNotificacion("Error: Envío " + envio.getId() + " rechazado.");
        }
        else if (event.equals("ENVIO_ENTREGADO")) {
            auditoria.registrarEvento("FINALIZACION", "Envío entregado correctamente.");
        }
    }
}