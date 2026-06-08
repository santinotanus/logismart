package com.logismart.infrastructure.iterator;
import com.logismart.domain.model.Envio;
import java.util.Iterator;
import java.util.List;

public class EnvioIterator implements Iterator<Envio> {
    private List<Envio> envios;
    private int posicion = 0;

    public EnvioIterator(List<Envio> envios) { this.envios = envios; }

    @Override public boolean hasNext() { return posicion < envios.size(); }
    @Override public Envio next() { return envios.get(posicion++); }
}