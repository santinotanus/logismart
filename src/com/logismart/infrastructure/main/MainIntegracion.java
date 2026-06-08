package com.logismart.infrastructure.main;

import com.logismart.domain.model.Envio;
import com.logismart.application.service.LogisticaFacade;
import com.logismart.application.service.ProcesoNacional;
import com.logismart.application.command.IniciarViajeCommand;
import com.logismart.infrastructure.observer.SistemaAuditoria;

public class MainIntegracion {
    public static void main(String[] args) {
        System.out.println("=== Iniciando LogiSmart Final ===");
        
        // 1. Setup e instanciación de Auditoría
        SistemaAuditoria auditoria = new SistemaAuditoria();
        LogisticaFacade facade = new LogisticaFacade();
        Envio miEnvio = new Envio("ENV-001", 50.0, "Buenos Aires");
        
        // Usamos la auditoría
        auditoria.registrarEvento("INICIO", "Procesando envío: " + miEnvio.getId());
        
        // 2. Ejecutar Comando (Command Pattern)
        facade.ejecutarOperacion(new IniciarViajeCommand(miEnvio));
        
        // 3. Proceso de Negocio (Template Method)
        facade.procesarEnvio(new ProcesoNacional(), miEnvio);
        
        // Cierre con auditoría
        auditoria.registrarEvento("FIN", "Flujo completado exitosamente.");
        System.out.println("=== Ejecución Finalizada ===");
    }
}