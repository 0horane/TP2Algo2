package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class BestEffortTestsPropios {

    void assertSetEquals(int[] s1, int[] s2) {
        assertEquals(s1.length, s2.length);
        for (int e1 : s1) {
            boolean encontrado = false;
            for (int e2 : s2) {
                if (e1 == e2) encontrado = true;
            }
            assertTrue(encontrado, "No se encontró el elemento " +  e1 + " en el arreglo " + s2.toString());
        }
    }

    void assertSetEquals(ArrayList<Integer> s1, ArrayList<Integer> s2) {
        assertEquals(s1.size(), s2.size());
        for (int e1 : s1) {
            boolean encontrado = false;
            for (int e2 : s2) {
                if (e1 == e2) encontrado = true;
            }
            assertTrue(encontrado, "No se encontró el elemento " +  e1 + " en el arreglo " + s2.toString());
        }
    }

    int cantCiudades;
    Traslado[] listaTraslados;
    ArrayList<Integer> actual;


    @BeforeEach 
    void init(){
        //Reiniciamos los valores de las ciudades y traslados antes de cada test
        cantCiudades = 7;
        listaTraslados = new Traslado[] {
                                            new Traslado(1, 0, 1, 100, 10),
                                            new Traslado(2, 0, 1, 400, 20),
                                            new Traslado(3, 3, 4, 500, 50),
                                            new Traslado(4, 4, 3, 500, 11),
                                            new Traslado(5, 1, 0, 1000, 40),
                                            new Traslado(6, 1, 0, 1000, 41),
                                            new Traslado(7, 6, 3, 2000, 42)
                                        };
    }



@Test
void despacharRedituabilidadYAntiguedad(){
    BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

    
    
    assertSetEquals(new int[] {7}, sis.despacharMasRedituables(1));
    assertSetEquals(new int[] {1}, sis.despacharMasAntiguos(1));
    assertSetEquals(new int[] {6,5,4,3}, sis.despacharMasRedituables(4));
    assertSetEquals(new int[] {2}, sis.despacharMasRedituables(100));
}

@Test
void ciudadesPerdidasYGanancias(){
    BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);
    sis.despacharMasRedituables(1);

    
    
    assertEquals(6, sis.ciudadConMayorSuperavit());
    assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());
    assertSetEquals(new ArrayList<>(Arrays.asList(6)), sis.ciudadesConMayorGanancia());
}
}