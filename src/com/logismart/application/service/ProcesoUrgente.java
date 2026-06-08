package com.logismart.application.service;
import com.logismart.domain.model.Envio;

public class ProcesoUrgente extends ProcesoEnvio {
    @Override protected void verificarDocumentacion(Envio e) { System.out.println("Validación express."); }
    @Override protected void calcularArancelesYCostos(Envio e) { System.out.println("Costo Prioritario calculado."); }
    @Override protected void asignarTransporte(Envio e) { System.out.println("Asignando transporte aéreo."); }
}