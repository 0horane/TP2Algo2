package aed;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


public class ColaPrioridadtests {
    public class CompInteger implements Comparator<Nodo<Integer>>{
        public int compare(Nodo<Integer> t1, Nodo<Integer> t2){
            int res = t1.valor - t2.valor;
            return res;
        }
    }
    Comparator<Nodo<Integer>> c = new CompInteger();
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
        System.out.println(cola.toString());
        assertEquals(2,cola.eliminar(5).valor);
        for (int i = 6; i >2 ;i--){
            assertEquals(i,cola.eliminar(0).valor);
        }
    }
    @Test
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
        assertEquals(null, cola.eliminar(0));
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












    private static Integer clave(Integer NCLAVES, Integer i) {
        return NCLAVES * ((i * i - 100 * i) % NCLAVES) + i;
    }

    public static <T> void insertionSort(ArrayList<T> list, Comparator<T> cmp){
        for (int i=0;i<list.size();i++){
            int j=i;
            T item = list.get(j);
            while(j!=0 && cmp.compare(list.get(j-1), item)>0){
                list.set(j,list.get(j-1));
                j--;
            }
            list.set(j, item);
        }
    }

    static Comparator<Integer> cmpInteger = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    };


    public static void validarTodo(ArrayList<Integer> datos, ColaPrioridad<Integer> cola){
        ArrayList<Integer> datosCopy = (ArrayList<Integer>) datos.clone();

        insertionSort(datosCopy, cmpInteger);
        
        int index = 0;
        while (index<=datosCopy.size()-1){
            assertEquals(datosCopy.get(0), cola.maximo().valor);
            assertEquals(datosCopy.get(0), cola.eliminar(0).valor);
            if(datosCopy.size()>0){
                datosCopy.remove(0);
            }
            
            assertEquals(datosCopy.size(),cola.len());
            index+=1;
        }
    }

    
    @Test
void stresstest(){
    int NCLAVES = 1000;
    ArrayList<Nodo<Integer>> datosTestNodo = new ArrayList<Nodo<Integer>>();
    ArrayList<Integer> datosTestInteger = new ArrayList<Integer>();

    for (int i=0;i<NCLAVES/2;i++){
        Integer j = (Integer) i;
        datosTestNodo.add(new Nodo<Integer>(clave(NCLAVES, j)));
        datosTestInteger.add(clave(NCLAVES, j));
    }

    //registrar datos por constructor
    ColaPrioridad<Integer> colaTest = new ColaPrioridad(datosTestNodo, new CompInteger());

    //registrar datos de a 1
    for (int i=NCLAVES/2;i<NCLAVES;i++){
        Integer j = (Integer) i;
        datosTestNodo.add(new Nodo<Integer>(clave(NCLAVES, j)));
        datosTestInteger.add(clave(NCLAVES, j));
        colaTest.agregar(datosTestNodo.get(j));
    }

    //validar
    validarTodo(datosTestInteger, colaTest);
}
}