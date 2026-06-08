package com.logismart.domain.state;
import com.logismart.domain.model.Envio;

public class EstadoConfirmado implements EstadoEnvio {
    @Override public String getNombre() { return "CONFIRMADO"; }
    @Override public void validar(Envio e) { e.setEstado(new EstadoEnTransito()); }
    @Override public void cancelar(Envio e) { e.setEstado(new EstadoCancelado()); }
    @Override public void ponerEnReparto(Envio e) { System.out.println("Error: No se puede repartir sin validar."); }
    @Override public void entregar(Envio e) { System.out.println("Error: No entregable."); }
    @Override public void retener(Envio e) { System.out.println("Error: No retenible."); }
    @Override public void liberar(Envio e) { }
    @Override public void reclamar(Envio e) { }
}