package com.logismart.application.service;
import com.logismart.domain.model.Envio;

public class ProcesoNacional extends ProcesoEnvio {
    @Override protected void verificarDocumentacion(Envio e) { System.out.println("Validando DNI local."); }
    @Override protected void calcularArancelesYCostos(Envio e) { System.out.println("Costo Nacional calculado."); }
    @Override protected void asignarTransporte(Envio e) { System.out.println("Asignando camión terrestre."); }
}