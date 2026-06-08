package com.logismart.infrastructure.memento;
import com.logismart.domain.state.EstadoEnvio;

public class EnvioMemento {
    private final EstadoEnvio estado;
    public EnvioMemento(EstadoEnvio estado) { this.estado = estado; }
    public EstadoEnvio getEstado() { return estado; }
}