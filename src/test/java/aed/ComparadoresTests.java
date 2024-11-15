package aed;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ComparadoresTests {
    Traslado Tmayor;
    Traslado Tmenor;
    Traslado TIntermedio;
    CompRedituabilidad cRed;
    CompAntiguedad cAnt;
    CompSuperavit cSup;
    Nodo<Traslado> NMayor;
    Nodo<Traslado> NIntermedio;
    Nodo<Traslado> NMenor;


    TMayor = new Traslado(0, 0, 3, 5000, 0);
    TIntermedio = new Traslado(1, 2, 3, 5000, 10);
    TMenor = new Traslado(2 ,4, 5, 2000, 20);
    NMayor = new Nodo<Traslado>(Tmayor);
    NIntermedio = new Nodo<Traslado>(TIntermedio);
    NMenor = new Nodo<Traslado>(Tmenor);
    cRed = new CompRedituabilidad();
    cAnt = new CompAntiguedad();
    cSup = new CompSuperavit();



  
    @Test
    void comparar_redituabilidad(){
        assertTrue(cRed.compare(this.NMayor,this.NMenor) > 0);
        assertTrue(cRed.compare(this.NMayor, this.NIntermedio) > 0);
        assertTrue(cRed.compare(this.NMenor, this.NMayor) < 0);
        assertTrue(cRed.compare(this.NIntermedio, this.NMayor) < 0);
    }
    @Test
    void comparar_antiguedad(){
        assertTrue(cAnt.compare(this.NMayor,this.NMenor) > 0);
        assertTrue(cAnt.compare(this.NMenor, this.NMayor) < 0);
    }
}
