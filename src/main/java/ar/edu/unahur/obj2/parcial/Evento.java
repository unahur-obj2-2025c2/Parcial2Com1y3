package ar.edu.unahur.obj2.parcial;

import ar.edu.unahur.obj2.parcial.exceptions.ValorDeGravedadException;

public class Evento {
    private final String tipo;
    private final Integer gravedad;
    
    public Evento(String tipo, Integer gravedad) {
        if (gravedad < 1 || gravedad > 10)
            throw new ValorDeGravedadException("Nivel de gravedad incorrecto");
        this.tipo = tipo;
        this.gravedad = gravedad;
    }

    public Boolean esGrave() {
        return gravedad >= 8;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getGravedad() {
        return gravedad;
    }

    public Evento copy(){
        return new Evento(tipo, gravedad);
    }
    
}
