package com.logismart.domain.state;
import com.logismart.domain.model.Envio;

public class EstadoEnTransito implements EstadoEnvio {
    @Override public String getNombre() { return "EN_TRANSITO"; }
    @Override public void validar(Envio e) { System.out.println("Error: Ya validado."); }
    @Override public void cancelar(Envio e) { System.out.println("Error: No cancelable en ruta."); }
    @Override public void ponerEnReparto(Envio e) { e.setEstado(new EstadoEnReparto()); }
    @Override public void entregar(Envio e) { System.out.println("Error: Requiere reparto previo."); }
    @Override public void retener(Envio e) { e.setEstado(new EstadoRetenido()); }
    @Override public void liberar(Envio e) { }
    @Override public void reclamar(Envio e) { }
}