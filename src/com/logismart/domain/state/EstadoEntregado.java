package com.logismart.domain.state;
import com.logismart.domain.model.Envio;

public class EstadoEntregado implements EstadoEnvio {
    @Override public String getNombre() { return "ENTREGADO"; }
    @Override public void validar(Envio e) { }
    @Override public void cancelar(Envio e) { }
    @Override public void ponerEnReparto(Envio e) { }
    @Override public void entregar(Envio e) { }
    @Override public void retener(Envio e) { }
    @Override public void liberar(Envio e) { }
    @Override public void reclamar(Envio e) { System.out.println("Reclamo registrado."); }
}