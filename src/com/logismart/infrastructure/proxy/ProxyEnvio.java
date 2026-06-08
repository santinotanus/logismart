package com.logismart.infrastructure.proxy;
import com.logismart.domain.model.Envio;

public class ProxyEnvio {
    private Envio realEnvio;
    private String id;

    public ProxyEnvio(String id) { this.id = id; }

    public Envio getEnvio() {
        if (realEnvio == null) {
            System.out.println("[Proxy] Cargando datos desde BD (Lazy Load)...");
            realEnvio = new Envio(id, 10.0, "Destino cargado");
        }
        return realEnvio;
    }
}