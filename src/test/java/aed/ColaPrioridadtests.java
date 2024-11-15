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
        assertEquals(1,cola.eliminar(0).valor);
        assertEquals(null,cola.maximo());
    }
    @Test 
    void heapConDosEnDesorden(){
        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(c);
        Nodo<Integer> n1 = new Nodo<Integer>(1);
        Nodo<Integer> n2 = new Nodo<Integer>(2);
        cola.agregar(n1);
        cola.agregar(n2);
        assertEquals(2,cola.eliminar(0).valor);
        assertEquals(1,cola.eliminar(0).valor);
    }
    @Test 
    void heapConVariosEnDesorden(){
        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(c);

        for (int i = 1; i <= 6;i++){
            Nodo<Integer> n = new Nodo<Integer>(i);
            cola.agregar(n);
        }
        for (int i = 6; i >=1 ;i--){
            assertEquals(i,cola.eliminar(0).valor);
        }
    }

    void heapifyConVariosEnDesorden(){
        ArrayList<Nodo<Integer>> datos =new ArrayList<>();
        for (int i = 1; i <= 6;i++){
            Nodo<Integer> n = new Nodo<Integer>(i);
            datos.add(n);
        }

        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(datos,c);

        for (int i = 6; i >=1 ;i--){
            assertEquals(i,cola.eliminar(0).valor);
        }
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


    @Test 
    void heapConVariosRepetidosEnDesorden(){
        ColaPrioridad<Integer> cola = new ColaPrioridad<Integer>(c);

        for (int i = 1; i <= 6;i++){
            Nodo<Integer> n = new Nodo<Integer>(i);
            cola.agregar(n);
        }
        for (int i = 6; i >= 1;i--){
            Nodo<Integer> n = new Nodo<Integer>(i);
            cola.agregar(n);
        }
        for (int i = 6; i >=1 ;i--){
            assertEquals(i,cola.eliminar(0).valor);
            assertEquals(i,cola.eliminar(0).valor);
        }
    }

}