package aed;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class ColaPrioridadtests {
    
    @Test
    void probarsifuncionaqsy(){
        ArrayList list = new ArrayList<Integer>(Arrays.asList(new Integer[]{3,5,4,1,2}));
        System.out.println(list.toString());
        ColaPrioridad test = new ColaPrioridad<Integer>(list,Comparator.naturalOrder()); 
        System.out.println(test.toString());
        System.out.println(test.sacarMaximo());
        System.out.println(test.toString());
        test.agregar(100);
        test.agregar(101);
        test.agregar(50);
        test.agregar(50);
        System.out.println(test.toString());
    }
    @Test
    void inicializarHeapVacio(){
        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(Comparator.naturalOrder());
        assertEquals(cola.maximo(),null);
    }
    @Test
    void heapConUnicoElem(){
        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(Comparator.naturalOrder());
        cola.agregar(1);
        assertEquals(1,cola.sacarMaximo());
        assertEquals(null,cola.maximo());
    }
    @Test 
    void heapConDosEnDesorden(){
        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(Comparator.naturalOrder());
        cola.agregar(1);
        cola.agregar(2);
        assertEquals(2,cola.sacarMaximo());
        assertEquals(1,cola.sacarMaximo());
    }
    @Test 
    void heapConVariosEnDesorden(){
        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(Comparator.naturalOrder());
        cola.agregar(1);
        cola.agregar(2);
        cola.agregar(4);
        cola.agregar(3);
        cola.agregar(6);
        cola.agregar(5);
        assertEquals(6,cola.sacarMaximo());
        assertEquals(5,cola.sacarMaximo());
        assertEquals(4,cola.sacarMaximo());
        assertEquals(3,cola.sacarMaximo());
        assertEquals(2,cola.sacarMaximo());
        assertEquals(1,cola.sacarMaximo());
    }
    void heapToArray(){
        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(Comparator.naturalOrder());
        cola.agregar(1);
        cola.agregar(2);
        cola.agregar(4);
        cola.agregar(3);
        cola.agregar(6);
        cola.agregar(5);
        assertEquals("[6, 4, 5, 1, 3, 2]",cola.toString());
    }
}