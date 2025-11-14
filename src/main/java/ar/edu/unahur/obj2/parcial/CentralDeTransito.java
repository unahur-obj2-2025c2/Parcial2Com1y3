package ar.edu.unahur.obj2.parcial;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.unahur.obj2.parcial.observer.Observadores;

class CentralDeTransito implements Observado{

    private final static CentralDeTransito instance = new CentralDeTransito();;
    private CentralDeTransito(){};
    private Set<Observadores> observadores = new HashSet<>();
    private List<RegistroEvento> registros = new ArrayList<>();

    public static CentralDeTransito getInstance() {
        return instance;
    }

    public void registarEvento(String tipo, Integer gravedad){
         Evento evento = new Evento(tipo, gravedad);
         notificar(evento);
         registros.add(new RegistroEvento(evento,  new HashSet<>(observadores)));
    }

    @Override
    public void agregar(Observadores observador) {
        observadores.add(observador);
    }

    @Override
    public void quitar(Observadores observador) {
        observadores.remove(observador);
    }

    @Override
    public void notificar(Evento evento) {
       observadores.forEach(o->o.actualizar(evento.copy()));
    }

    public Integer totalDeEventosNotificados() {
        return registros.stream().mapToInt(r->r.cantidadNotificaciones()).sum();
    }

    public void clear() {
        observadores.clear();
        registros.clear();
    } 
    
}
