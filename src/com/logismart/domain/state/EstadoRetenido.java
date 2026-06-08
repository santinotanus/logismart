package com.logismart.domain.state;
import com.logismart.domain.model.Envio;

public class EstadoRetenido implements EstadoEnvio {
    @Override public String getNombre() { return "RETENIDO"; }
    @Override public void validar(Envio e) { }
    @Override public void cancelar(Envio e) { e.setEstado(new EstadoCancelado()); }
    @Override public void ponerEnReparto(Envio e) { }
    @Override public void entregar(Envio e) { }
    @Override public void retener(Envio e) { }
    @Override public void liberar(Envio e) { e.setEstado(new EstadoEnTransito()); }
    @Override public void reclamar(Envio e) { }
}