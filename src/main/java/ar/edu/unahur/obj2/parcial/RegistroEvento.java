package ar.edu.unahur.obj2.parcial;

import java.util.HashSet;
import java.util.Set;

import ar.edu.unahur.obj2.parcial.observer.Observadores;

public class RegistroEvento {
    private Evento evento;
    Set<Observadores> observadores =new HashSet<>();
    public RegistroEvento(Evento evento, Set<Observadores> observadores) {
        this.evento = evento;
        this.observadores = observadores;
    }

    public Integer cantidadNotificaciones() {
        return observadores.size();
    }

    public Evento getEvento() {
        return evento;
    }

    
}
