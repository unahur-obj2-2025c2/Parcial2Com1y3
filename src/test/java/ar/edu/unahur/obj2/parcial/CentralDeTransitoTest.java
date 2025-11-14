package ar.edu.unahur.obj2.parcial;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.parcial.observer.Cruce;


public class CentralDeTransitoTest {

    CentralDeTransito centralDeTransito;
    Cruce c1 = new Cruce();
    Cruce c2 = new Cruce();

    @BeforeEach
    public void setUp() {
        centralDeTransito = CentralDeTransito.getInstance();
        centralDeTransito.clear();
        centralDeTransito.agregar(c1);
        centralDeTransito.agregar(c2);

    }

    @Test
    void dadoElSetUp_alAgregarEventos_SeVerificaCorrectamenteLasNotificacionesYCongestion() {
        centralDeTransito.registarEvento("obras", 6);
        centralDeTransito.registarEvento("accidente", 8);
        assertEquals(2, c1.totalEventos());
        assertEquals(2, c2.totalEventos());
    }

    @Test
    void dadoElSetUp_alCambiarEstrategiaYAgregarEventos_SeVerificaCorrectamenteLasNotificacionesYCongestion() {
        centralDeTransito.registarEvento("obras", 6);
        centralDeTransito.registarEvento("accidente", 8);
        centralDeTransito.quitar(c1);
        centralDeTransito.registarEvento("congestión", 7);
        assertEquals(2, c1.totalEventos());
        assertEquals(10, c1.calcularCongestion());
        assertEquals(3, c2.totalEventos());
        assertEquals(7, c2.calcularCongestion());
        assertEquals(5, centralDeTransito.totalDeEventosNotificados());
    }
}