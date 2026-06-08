package com.logismart.persistence.repository;
import com.logismart.domain.model.Envio;

public interface EnvioRepository {
    void save(Envio envio);
    Envio findById(String id);
    void delete(String id);
}