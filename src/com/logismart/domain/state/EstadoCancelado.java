package com.logismart.domain.state;
import com.logismart.domain.model.Envio;

public class EstadoCancelado implements EstadoEnvio {
    @Override public String getNombre() { return "CANCELADO"; }
    // Estado final: todos los métodos vacíos o con log de error
    @Override public void validar(Envio e) { }
    @Override public void cancelar(Envio e) { }
    @Override public void ponerEnReparto(Envio e) { }
    @Override public void entregar(Envio e) { }
    @Override public void retener(Envio e) { }
    @Override public void liberar(Envio e) { }
    @Override public void reclamar(Envio e) { }
}