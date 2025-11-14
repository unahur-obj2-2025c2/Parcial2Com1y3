package ar.edu.unahur.obj2.parcial;

import ar.edu.unahur.obj2.parcial.observer.Observadores;

public interface Observado {
    void agregar(Observadores observador);
    void quitar(Observadores observador);
    void notificar(Evento evento);
}
