package com.logismart.persistence.mapper;

import com.logismart.domain.model.Envio;

public class EnvioDataMapper {
    public void mapToDatabase(Envio envio) {
        // Acá iría el INSERT INTO... usando JDBC o tu framework
        System.out.println("[DataMapper] Mapeando Envio -> SQL Row");
    }

    public Envio mapToDomain(String id) {
        // Acá iría el SELECT * FROM Envio WHERE id = ?
        System.out.println("[DataMapper] Mapeando SQL Row -> Envio");
        return new Envio(id, 0.0, "Destino Dummy");
    }
}