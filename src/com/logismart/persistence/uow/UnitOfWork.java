package com.logismart.persistence.uow;

import com.logismart.domain.model.Envio;
import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {
    private List<Envio> nuevos = new ArrayList<>();

    public void registerNew(Envio envio) {
        nuevos.add(envio);
    }

    public void commit() {
        System.out.println("[UnitOfWork] Commit: Persistiendo " + nuevos.size() + " cambios de forma atómica.");
        // Acá iría la lógica real de transacción SQL (ej: connection.commit())
        nuevos.clear();
    }

    public void rollback() {
        System.out.println("[UnitOfWork] Rollback: Operación cancelada.");
        nuevos.clear();
    }
}