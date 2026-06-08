package com.logismart.infrastructure.observer;

public class SistemaAuditoria implements Observador {
    @Override
    public void actualizar(String mensaje) {
        System.out.println("[Auditoria] Log: " + mensaje);
    }
    public void registrarEvento(String evento, String detalle) {
        System.out.println("[Auditoria] Guardando en DB: " + evento + " -> " + detalle);
    }
}