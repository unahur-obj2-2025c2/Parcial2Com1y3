package ar.edu.unahur.obj2.parcial.observer;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.parcial.Evento;
import ar.edu.unahur.obj2.parcial.congestiones.Congestionable;
import ar.edu.unahur.obj2.parcial.congestiones.Critica;

public class Cruce implements Observadores{
    List<Evento> eventos = new ArrayList<>();
    private Congestionable congestion = new Critica();
    

    @Override
    public void actualizar(Evento evento) {
        eventos.add(evento);
    }


    public void setCongetstion(Congestionable congestion) {
        this.congestion = congestion;
    }

    public Double calcularCongestion() {
        return congestion.calcularCongestion(this);
    }

    public Evento ultimoEventoRecibido() {
        return eventos.get(eventos.size() - 1);
    }

    public Integer totalEventos() {
        return eventos.size();
    }

    public Double sumaDeGravedades() {
        return eventos.stream().mapToDouble(Evento::getGravedad).sum();
    }

    public List<Evento> eventosGraves() {
        return eventos.stream().filter(Evento::esGrave).toList();
    }

    public Integer gravedadUltimoEvento() {
        return  ultimoEventoRecibido().getGravedad();
    }
    
}
