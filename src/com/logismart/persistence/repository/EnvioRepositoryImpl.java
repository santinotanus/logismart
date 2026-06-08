package com.logismart.persistence.repository;

import com.logismart.domain.model.Envio;
import com.logismart.persistence.mapper.EnvioDataMapper;

public class EnvioRepositoryImpl implements EnvioRepository {
    private EnvioDataMapper mapper = new EnvioDataMapper();

    @Override
    public void save(Envio envio) {
        System.out.println("[Repository] Persistiendo envío: " + envio.getId());
        mapper.mapToDatabase(envio);
    }

    @Override
    public Envio findById(String id) {
        System.out.println("[Repository] Buscando en BD: " + id);
        return mapper.mapToDomain(id);
    }

    @Override
    public void delete(String id) { System.out.println("[Repository] Borrando: " + id); }
}