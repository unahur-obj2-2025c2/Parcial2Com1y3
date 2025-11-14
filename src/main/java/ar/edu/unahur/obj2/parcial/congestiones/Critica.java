package ar.edu.unahur.obj2.parcial.congestiones;

import ar.edu.unahur.obj2.parcial.Evento;
import ar.edu.unahur.obj2.parcial.observer.Cruce;

public class Critica implements Congestionable{

    @Override
    public Double calcularCongestion(Cruce cruce) {
        Evento evento = cruce.ultimoEventoRecibido();
        return evento.esGrave() ? 10.00 : evento.getGravedad();
    }

}
