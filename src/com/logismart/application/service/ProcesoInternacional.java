package com.logismart.application.service;
import com.logismart.domain.model.Envio;

public class ProcesoInternacional extends ProcesoEnvio {
    @Override protected void verificarDocumentacion(Envio e) { System.out.println("Validando Pasaporte y Aduana."); }
    @Override protected void calcularArancelesYCostos(Envio e) { System.out.println("Costo Internacional con Aranceles."); }
    @Override protected void asignarTransporte(Envio e) { System.out.println("Asignando contenedor marítimo."); }
}