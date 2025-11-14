package ar.edu.unahur.obj2.parcial.congestiones;

import ar.edu.unahur.obj2.parcial.observer.Cruce;

public class Promedio implements Congestionable{

    @Override
    public Double calcularCongestion(Cruce cruce) {
        return cruce.sumaDeGravedades() / cruce.totalEventos();
    }

}
