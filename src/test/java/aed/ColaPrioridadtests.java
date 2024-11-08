package aed;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class ColaPrioridadtests {
    
    public class CompInt implements Comparator<Nodo<Integer>>{
        public int compare(Nodo<Integer> t1, Nodo<Integer> t2){
            int res = t1.valor - t2.valor;
            return res;
        }
    }
    Comparator<Nodo<Integer>> c = new CompInt();
    @Test
    void inicializarHeapVacio(){
        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(c);
        assertEquals(cola.maximo(),null);
    }
    @Test
    void heapConUnicoElem(){
        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(c);
        Nodo<Integer> n1 = new Nodo<Integer>(1);
        cola.agregar(n1);
        assertEquals(1,cola.sacarMaximo().valor);
        assertEquals(null,cola.maximo());
    }
    @Test 
    void heapConDosEnDesorden(){
        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(c);
        Nodo<Integer> n1 = new Nodo<Integer>(1);
        Nodo<Integer> n2 = new Nodo<Integer>(2);
        cola.agregar(n1);
        cola.agregar(n2);
        assertEquals(2,cola.sacarMaximo().valor);
        assertEquals(1,cola.sacarMaximo().valor);
    }
    @Test 
    void heapConVariosEnDesorden(){
        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(c);
        Nodo<Integer> n1 = new Nodo<Integer>(1);
        Nodo<Integer> n2 = new Nodo<Integer>(2);
        Nodo<Integer> n3 = new Nodo<Integer>(3);
        Nodo<Integer> n4 = new Nodo<Integer>(4);
        Nodo<Integer> n5 = new Nodo<Integer>(5);
        Nodo<Integer> n6 = new Nodo<Integer>(6);

        cola.agregar(n1);
        cola.agregar(n2);
        cola.agregar(n4);
        cola.agregar(n3);
        cola.agregar(n6);
        cola.agregar(n5);
        assertEquals(6,cola.sacarMaximo().valor);
        assertEquals(5,cola.sacarMaximo().valor);
        assertEquals(4,cola.sacarMaximo().valor);
        assertEquals(3,cola.sacarMaximo().valor);
        assertEquals(2,cola.sacarMaximo().valor);
        assertEquals(1,cola.sacarMaximo().valor);
    }
    @Test
    void heapToString(){
        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(c);
        Nodo<Integer> n1 = new Nodo<Integer>(1);
        Nodo<Integer> n2 = new Nodo<Integer>(2);
        Nodo<Integer> n3 = new Nodo<Integer>(3);
        Nodo<Integer> n4 = new Nodo<Integer>(4);
        Nodo<Integer> n5 = new Nodo<Integer>(5);
        Nodo<Integer> n6 = new Nodo<Integer>(6);

        cola.agregar(n1);
        cola.agregar(n2);
        cola.agregar(n4);
        cola.agregar(n3);
        cola.agregar(n6);
        cola.agregar(n5);
        assertEquals("[6, 4, 5, 1, 3, 2]",cola.toString());
    }
}