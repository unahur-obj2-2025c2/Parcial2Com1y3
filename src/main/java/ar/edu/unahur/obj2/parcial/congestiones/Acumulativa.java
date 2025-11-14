package ar.edu.unahur.obj2.parcial.congestiones;

import ar.edu.unahur.obj2.parcial.Evento;
import ar.edu.unahur.obj2.parcial.observer.Cruce;

public class Acumulativa implements Congestionable {

    @Override
    public Double calcularCongestion(Cruce cruce) {
        return cruce.eventosGraves().stream().mapToDouble(Evento::getGravedad).sum();
    }

}
