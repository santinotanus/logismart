package com.logismart.infrastructure.main;

import com.logismart.domain.model.Envio;
import com.logismart.application.service.LogisticaFacade;
import com.logismart.application.service.ProcesoNacional;
import com.logismart.application.command.IniciarViajeCommand;
import com.logismart.application.command.CancelarEnvioCommand;
import com.logismart.infrastructure.observer.SistemaAuditoria;
import com.logismart.infrastructure.memento.Caretaker;

public class MainIntegracion {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=================================================");
        System.out.println("   🚀 INICIANDO SISTEMA LOGISMART (LIVE DEMO) 🚀   ");
        System.out.println("=================================================\n");
        
        // --- SETUP DE INFRAESTRUCTURA ---
        System.out.println(">>> 1. INICIALIZANDO INFRAESTRUCTURA (Observers & Facades)...");
        SistemaAuditoria auditoria = new SistemaAuditoria();
        LogisticaFacade facade = new LogisticaFacade();
        Caretaker caretaker = new Caretaker();
        Thread.sleep(1000); // Pequeña pausa para efecto dramático en la presentación
        
        // --- CREACIÓN DEL DOMINIO ---
        System.out.println("\n>>> 2. CREANDO ENTIDAD DE DOMINIO...");
        Envio miEnvio = new Envio("ENV-DEMO-2026", 150.0, "Mar del Plata");
        System.out.println("[Dominio] Nuevo envío creado. ID: " + miEnvio.getId() + " | Estado Inicial: " + miEnvio.getEstadoActual().getNombre());
        auditoria.registrarEvento("CREACION", "Envío registrado en sistema matriz.");
        Thread.sleep(1500);

        // --- DEMOSTRACIÓN: TEMPLATE METHOD Y STRATEGY ---
        System.out.println("\n>>> 3. EJECUTANDO FLUJO DE NEGOCIO (Template Method)...");
        System.out.println("[Facade] Delegando al Proceso Nacional:");
        facade.procesarEnvio(new ProcesoNacional(), miEnvio);
        Thread.sleep(1500);

        // --- DEMOSTRACIÓN: MEMENTO (Guardado de Estado) ---
        System.out.println("\n>>> 4. PREPARANDO TRANSACCIÓN CRÍTICA (Memento)...");
        System.out.println("[Memento] Tomando snapshot del estado actual antes del viaje...");
        caretaker.save(miEnvio.guardarEstado());
        Thread.sleep(1500);

        // --- DEMOSTRACIÓN: COMMAND PATTERN ---
        System.out.println("\n>>> 5. INICIANDO VIAJE (Command Pattern)...");
        facade.ejecutarOperacion(new IniciarViajeCommand(miEnvio));
        System.out.println("[State] Estado actual verificado: " + miEnvio.getEstadoActual().getNombre());
        auditoria.registrarEvento("TRANSICION", "El camión ha salido de la central.");
        Thread.sleep(1500);

        // --- DEMOSTRACIÓN: MANEJO DE ERRORES Y ROLLBACK ---
        System.out.println("\n>>> 6. SIMULANDO EMERGENCIA LOGÍSTICA (Rollback/Undo)...");
        System.out.println("[Alerta] Se detectó un problema en la ruta. Abortando misión...");
        
        // Deshacemos usando Memento
        miEnvio.restaurarEstado(caretaker.undo());
        System.out.println("[State] Estado revertido exitosamente a: " + miEnvio.getEstadoActual().getNombre());
        
        // Ejecutamos comando de cancelación
        facade.ejecutarOperacion(new CancelarEnvioCommand(miEnvio));
        auditoria.registrarEvento("CANCELACION", "Operación abortada por seguridad.");
        Thread.sleep(1000);

        System.out.println("\n=================================================");
        System.out.println("        ✅ DEMOSTRACIÓN FINALIZADA CON ÉXITO ✅        ");
        System.out.println("=================================================");
    }
}