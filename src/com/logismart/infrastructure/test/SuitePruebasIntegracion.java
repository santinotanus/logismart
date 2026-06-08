package com.logismart.infrastructure.test;

import com.logismart.domain.model.Envio;
import com.logismart.domain.strategy.*;
import com.logismart.domain.state.*;
import com.logismart.infrastructure.memento.Caretaker;
import com.logismart.application.chain.*;
import com.logismart.application.command.*;

public class SuitePruebasIntegracion {
    public static void main(String[] args) {
        System.out.println("=== Iniciando Suite Exhaustiva LogiSmart (40+ Tests) ===\n");
        int passCount = 0;

        try {
            // --- MÓDULO 1: ENTIDAD BÁSICA (6 Pruebas) ---
            Envio e_base = new Envio("ST-01", 10.0, "CABA");
            assert "ST-01".equals(e_base.getId()) : "Fallo T01"; passCount++;
            assert 10.0 == e_base.getPeso() : "Fallo T02"; passCount++;
            assert "CABA".equals(e_base.getDestino()) : "Fallo T03"; passCount++;
            
            e_base.setVolumen(5.0);
            e_base.setDistanciaKm(100.0);
            e_base.setEsUrgente(true);
            assert 5.0 == e_base.getVolumen() : "Fallo T04"; passCount++;
            assert 100.0 == e_base.getDistanciaKm() : "Fallo T05"; passCount++;
            assert e_base.isEsUrgente() : "Fallo T06"; passCount++;


            // --- MÓDULO 2: ESTRATEGIAS DE COSTO (8 Pruebas) ---
            Envio e_costos = new Envio("ST-02", 50.0, "Rosario");
            e_costos.setVolumen(2.5);
            e_costos.setDistanciaKm(300.0);
            e_costos.setEsUrgente(false);

            assert new CostoPorPeso().calcular(e_costos) == 2250.0 : "Fallo T07"; passCount++;
            assert new CostoPorVolumen().calcular(e_costos) == 625.0 : "Fallo T08"; passCount++;
            assert new CostoPorDistancia().calcular(e_costos) == 3750.0 : "Fallo T09"; passCount++;
            assert new CostoPorUrgencia().calcular(e_costos) == 350.0 : "Fallo T10"; passCount++;
            
            e_costos.setEsUrgente(true);
            assert new CostoPorUrgencia().calcular(e_costos) == 1500.0 : "Fallo T11"; passCount++;
            
            // Híbrido: (300*5.0) + (50*15.0) + (500) = 1500 + 750 + 500 = 2750
            assert new CostoHibrido().calcular(e_costos) == 2750.0 : "Fallo T12"; passCount++;
            
            // Costos límite (0 km, 0 peso)
            Envio e_zero = new Envio("ST-00", 0.0, "Local");
            assert new CostoPorPeso().calcular(e_zero) == 0.0 : "Fallo T13"; passCount++;
            assert new CostoPorDistancia().calcular(e_zero) == 0.0 : "Fallo T14"; passCount++;


            // --- MÓDULO 3: CADENA DE RESPONSABILIDAD (5 Pruebas) ---
            Validator cadena = new PesoValidator();
            cadena.setNext(new DestinoValidator());

            Envio e_ok = new Envio("ST-03", 50.0, "Córdoba");
            Envio e_pesado = new Envio("ST-04", 15000.0, "Mendoza");
            Envio e_limite = new Envio("ST-05", 10000.0, "Salta");
            Envio e_sin_dest = new Envio("ST-06", 10.0, "");
            Envio e_null_dest = new Envio("ST-07", 10.0, null);

            assert cadena.validate(e_ok) : "Fallo T15"; passCount++;
            assert !cadena.validate(e_pesado) : "Fallo T16"; passCount++;
            assert cadena.validate(e_limite) : "Fallo T17"; passCount++; // Límite exacto debe pasar
            assert !cadena.validate(e_sin_dest) : "Fallo T18"; passCount++;
            assert !cadena.validate(e_null_dest) : "Fallo T19"; passCount++;


            // --- MÓDULO 4: COMMAND & MEMENTO (5 Pruebas) ---
            Envio e_tx = new Envio("ST-08", 5.0, "Neuquén");
            ColaComandos cola = new ColaComandos();
            Caretaker caretaker = new Caretaker();

            caretaker.save(e_tx.guardarEstado()); // Memento: CONFIRMADO
            assert "CONFIRMADO".equals(e_tx.getEstadoActual().getNombre()) : "Fallo T20"; passCount++;
            
            cola.ejecutar(new IniciarViajeCommand(e_tx));
            assert "EN_TRANSITO".equals(e_tx.getEstadoActual().getNombre()) : "Fallo T21"; passCount++;
            
            cola.deshacer();
            assert "CONFIRMADO".equals(e_tx.getEstadoActual().getNombre()) : "Fallo T22"; passCount++;

            cola.ejecutar(new CancelarEnvioCommand(e_tx));
            assert "CANCELADO".equals(e_tx.getEstadoActual().getNombre()) : "Fallo T23"; passCount++;

            e_tx.restaurarEstado(caretaker.undo()); // Restaura a CONFIRMADO
            assert "CONFIRMADO".equals(e_tx.getEstadoActual().getNombre()) : "Fallo T24"; passCount++;


            // --- MÓDULO 5: STATE - MATRIZ DE TRANSICIONES (18 Pruebas) ---
            System.out.println("-> Ejecutando Matriz de Transiciones de Estado...");
            
            // Estado Confirmado
            Envio e_conf = new Envio("ST-S1", 1.0, "X");
            e_conf.ponerEnReparto(); // Inválido
            assert "CONFIRMADO".equals(e_conf.getEstadoActual().getNombre()) : "Fallo T25"; passCount++;
            e_conf.entregar(); // Inválido
            assert "CONFIRMADO".equals(e_conf.getEstadoActual().getNombre()) : "Fallo T26"; passCount++;
            e_conf.cancelar(); // Válido
            assert "CANCELADO".equals(e_conf.getEstadoActual().getNombre()) : "Fallo T27"; passCount++;

            // Estado Cancelado (Estado Terminal, nada debería cambiarlo)
            e_conf.validar(); assert "CANCELADO".equals(e_conf.getEstadoActual().getNombre()) : "Fallo T28"; passCount++;
            e_conf.ponerEnReparto(); assert "CANCELADO".equals(e_conf.getEstadoActual().getNombre()) : "Fallo T29"; passCount++;
            e_conf.entregar(); assert "CANCELADO".equals(e_conf.getEstadoActual().getNombre()) : "Fallo T30"; passCount++;

            // Estado En Tránsito
            Envio e_transito = new Envio("ST-S2", 1.0, "X");
            e_transito.validar(); // Pasa a En Tránsito
            e_transito.validar(); // Inválido
            assert "EN_TRANSITO".equals(e_transito.getEstadoActual().getNombre()) : "Fallo T31"; passCount++;
            e_transito.cancelar(); // Inválido
            assert "EN_TRANSITO".equals(e_transito.getEstadoActual().getNombre()) : "Fallo T32"; passCount++;
            e_transito.entregar(); // Inválido
            assert "EN_TRANSITO".equals(e_transito.getEstadoActual().getNombre()) : "Fallo T33"; passCount++;

            // Estado Retenido
            e_transito.getEstadoActual().retener(e_transito); // Forzamos retención
            assert "RETENIDO".equals(e_transito.getEstadoActual().getNombre()) : "Fallo T34"; passCount++;
            e_transito.ponerEnReparto(); // Inválido
            assert "RETENIDO".equals(e_transito.getEstadoActual().getNombre()) : "Fallo T35"; passCount++;
            e_transito.getEstadoActual().liberar(e_transito); // Válido
            assert "EN_TRANSITO".equals(e_transito.getEstadoActual().getNombre()) : "Fallo T36"; passCount++;

            // Estado En Reparto
            e_transito.ponerEnReparto();
            assert "EN_REPARTO".equals(e_transito.getEstadoActual().getNombre()) : "Fallo T37"; passCount++;
            e_transito.cancelar(); // Inválido
            assert "EN_REPARTO".equals(e_transito.getEstadoActual().getNombre()) : "Fallo T38"; passCount++;
            e_transito.validar(); // Inválido
            assert "EN_REPARTO".equals(e_transito.getEstadoActual().getNombre()) : "Fallo T39"; passCount++;

            // Estado Entregado
            e_transito.entregar();
            assert "ENTREGADO".equals(e_transito.getEstadoActual().getNombre()) : "Fallo T40"; passCount++;
            e_transito.ponerEnReparto(); // Inválido
            assert "ENTREGADO".equals(e_transito.getEstadoActual().getNombre()) : "Fallo T41"; passCount++;
            e_transito.cancelar(); // Inválido
            assert "ENTREGADO".equals(e_transito.getEstadoActual().getNombre()) : "Fallo T42"; passCount++;

            System.out.println("\n=======================================================");
            System.out.println("=== SUITE FINALIZADA CON ÉXITO: " + passCount + " ASSERTS PASADOS ===");
            System.out.println("=======================================================");

        } catch (AssertionError e) {
            System.err.println("\n[CRITICAL FAIL] Se rompió la arquitectura: " + e.getMessage());
            e.printStackTrace();
        }
    }
}