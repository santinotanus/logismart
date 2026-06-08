package com.logismart.domain.state;
import com.logismart.domain.model.Envio;

public class EstadoEnReparto implements EstadoEnvio {
    @Override public String getNombre() { return "EN_REPARTO"; }
    @Override public void validar(Envio e) { }
    @Override public void cancelar(Envio e) { }
    @Override public void ponerEnReparto(Envio e) { }
    @Override public void entregar(Envio e) { e.setEstado(new EstadoEntregado()); }
    @Override public void retener(Envio e) { }
    @Override public void liberar(Envio e) { }
    @Override public void reclamar(Envio e) { }
}