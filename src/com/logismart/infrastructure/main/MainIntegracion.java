package com.logismart.infrastructure.main;

import com.logismart.domain.model.Envio;
import com.logismart.application.service.LogisticaFacade;
import com.logismart.application.service.ProcesoNacional;
import com.logismart.application.command.IniciarViajeCommand;
import com.logismart.infrastructure.observer.SistemaAuditoria;

public class MainIntegracion {
    public static void main(String[] args) {
        System.out.println("=== Iniciando LogiSmart Final ===");
        
        // 1. Setup
        LogisticaFacade facade = new LogisticaFacade();
        Envio miEnvio = new Envio("ENV-001", 50.0, "Buenos Aires");
        
        // 2. Ejecutar Comando (Command Pattern)
        facade.ejecutarOperacion(new IniciarViajeCommand(miEnvio));
        
        // 3. Proceso de Negocio (Template Method)
        facade.procesarEnvio(new ProcesoNacional(), miEnvio);
        
        System.out.println("=== Ejecución Finalizada Exitosamente ===");
    }
}