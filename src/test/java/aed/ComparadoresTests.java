package aed;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ComparadoresTests {
    Traslado tMayor;
    Traslado tMenor;
    Traslado tIntermedio;
    CompRedituabilidad cRed;
    CompAntiguedad cAnt;
    CompSuperavit cSup;
    Nodo<Traslado> nMayor;
    Nodo<Traslado> nIntermedio;
    Nodo<Traslado> nMenor;
    Nodo<Ciudad> cMayor;
    Nodo<Ciudad> cMenor;
    Nodo<Ciudad> cIntermedia;
    Ciudad cMay;
    Ciudad cInt;
    Ciudad cMen;

    @BeforeEach
    void init(){
        tMayor = new Traslado(0, 0, 3, 5000, 0);
        tIntermedio = new Traslado(1, 2, 3, 5000, 10);
        tMenor = new Traslado(2 ,4, 5, 2000, 20);
        nMayor = new Nodo<Traslado>(tMayor);
        nIntermedio = new Nodo<Traslado>(tIntermedio);
        nMenor = new Nodo<Traslado>(tMenor);
        cRed = new CompRedituabilidad();
        cAnt = new CompAntiguedad();
        cSup = new CompSuperavit();    
        cMay = new Ciudad(0);
        cMay.ganancias = 100; cMay.perdidas = 0;
        cMen = new Ciudad(2);
        cMen.ganancias = 50; cMen.perdidas = 100;
        cInt = new Ciudad(1);
        cInt.ganancias = 100; cInt.perdidas = 0;
        cMayor = new Nodo<Ciudad>(cMay);
        cMenor = new Nodo<Ciudad>(cMen);
        cIntermedia = new Nodo<Ciudad>(cInt);
    }

    @Test
    void comparar_redituabilidad(){
        assertTrue(cRed.compare(this.nMayor,this.nMenor) > 0);
        assertTrue(cRed.compare(this.nMayor, this.nIntermedio) > 0);
        assertTrue(cRed.compare(this.nMenor, this.nMayor) < 0);
        assertTrue(cRed.compare(this.nIntermedio, this.nMayor) < 0);
    }

    @Test
    void comparar_antiguedad(){
        assertTrue(cAnt.compare(this.nMayor,this.nMenor) > 0);
        assertTrue(cAnt.compare(this.nMenor, this.nMayor) < 0);
    }
    @Test
    void comparar_superavit(){
        assertTrue(cSup.compare(this.cMayor, this.cIntermedia) > 0);
        assertTrue(cSup.compare(this.cMayor, this.cMenor) > 0);
        assertTrue(cSup.compare(this.cIntermedia, this.cMayor) < 0);
        assertTrue(cSup.compare(this.cMenor, this.cMayor) < 0);
    }
}
