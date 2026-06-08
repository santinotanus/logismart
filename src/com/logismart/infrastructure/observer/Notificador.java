package com.logismart.infrastructure.observer;

public class Notificador implements Observador {
    @Override
    public void actualizar(String mensaje) {
        System.out.println("[Notificador] Enviando alerta: " + mensaje);
    }
    public void enviarNotificacion(String msg) {
        System.out.println("[Notificador] Push notification sent: " + msg);
    }
}