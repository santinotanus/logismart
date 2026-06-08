package com.logismart.infrastructure.test;

import com.logismart.domain.model.Envio;
import com.logismart.domain.strategy.*;
import com.logismart.infrastructure.memento.Caretaker;

public class SuitePruebasIntegracion {
    public static void main(String[] args) {
        System.out.println("=== Iniciando Suite de Pruebas LogiSmart ===");
        int passCount = 0;

        try {
            // Prueba 1: Creación y estado inicial
            Envio e1 = new Envio("TEST-01", 10.0, "CABA");
            assert "CONFIRMADO".equals(e1.getEstadoActual().getNombre()) : "Fallo estado inicial";
            System.out.println("[PASS] T01 - Estado inicial correcto"); passCount++;

            // Prueba 2: Transición de estado válida (State)
            e1.validar();
            assert "EN_TRANSITO".equals(e1.getEstadoActual().getNombre()) : "Fallo transición a tránsito";
            System.out.println("[PASS] T02 - Transición CONFIRMADO -> EN_TRANSITO exitosa"); passCount++;

            // Prueba 3: Transición inválida (State)
            e1.entregar(); // No se puede entregar si está en tránsito (requiere reparto)
            assert "EN_TRANSITO".equals(e1.getEstadoActual().getNombre()) : "Fallo bloqueo de transición";
            System.out.println("[PASS] T03 - Bloqueo de estado inválido correcto"); passCount++;

            // Prueba 4: Estrategia de costo por peso (Strategy)
            EstrategiaCalculoCosto estPeso = new CostoPorPeso();
            assert estPeso.calcular(e1) == 450.0 : "Fallo cálculo peso";
            System.out.println("[PASS] T04 - Estrategia Costo por Peso validada"); passCount++;

            // Prueba 5: Estrategia híbrida (Strategy)
            e1.setDistanciaKm(100);
            e1.setEsUrgente(true);
            EstrategiaCalculoCosto estHibrida = new CostoHibrido();
            assert estHibrida.calcular(e1) == 1150.0 : "Fallo cálculo híbrido"; // (100*5) + (10*15) + 500
            System.out.println("[PASS] T05 - Estrategia Costo Híbrido validada"); passCount++;

            // Prueba 6: Funcionalidad Memento (Undo/Redo de estados)
            Caretaker caretaker = new Caretaker();
            caretaker.save(e1.guardarEstado()); // Guarda EN_TRANSITO
            e1.ponerEnReparto();
            assert "EN_REPARTO".equals(e1.getEstadoActual().getNombre()) : "Fallo reparto";
            e1.restaurarEstado(caretaker.undo());
            assert "EN_TRANSITO".equals(e1.getEstadoActual().getNombre()) : "Fallo memento";
            System.out.println("[PASS] T06 - Memento guarda y restaura estado exitosamente"); passCount++;

            System.out.println("=== Suite Finalizada: " + passCount + " Casos Exitosos ===");

        } catch (AssertionError e) {
            System.err.println("[FAIL] Prueba fallida: " + e.getMessage());
        }
    }
}