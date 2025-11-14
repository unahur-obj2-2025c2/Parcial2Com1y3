package ar.edu.unahur.obj2.parcial.congestiones;

import ar.edu.unahur.obj2.parcial.observer.Cruce;

public class Innercial implements Congestionable{
    private Double congestionActual = 0.0; 

    @Override
    public Double calcularCongestion(Cruce cruce) {
        Double gravedadUltimoEvento = cruce.gravedadUltimoEvento() * 1.00;
        if (gravedadUltimoEvento > congestionActual) 
            congestionActual = gravedadUltimoEvento;
        else 
            gravedadUltimoEvento = Math.min(0, gravedadUltimoEvento - 1);
        
        return congestionActual;
    }

}
