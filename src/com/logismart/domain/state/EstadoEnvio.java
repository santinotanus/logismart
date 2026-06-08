// EstadoEnvio.java
package com.logismart.domain.state;
import com.logismart.domain.model.Envio;

public interface EstadoEnvio {
    void validar(Envio e);
    void cancelar(Envio e);
    void ponerEnReparto(Envio e);
    void entregar(Envio e);
    void retener(Envio e);
    void liberar(Envio e);
    void reclamar(Envio e);
    String getNombre();
}