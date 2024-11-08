package aed;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class ColaPrioridad<T> {
    private ArrayList<Nodo<T>> datos;
    private Comparator<Nodo<T>> comparator;

    public ColaPrioridad(Comparator<Nodo<T>> comparator){ //O(1)
        this.datos = new ArrayList<Nodo<T>>();
        this.comparator = comparator;
    }
    
    public ColaPrioridad(ArrayList<T> datos, Comparator<Nodo<T>> comparator){ //O(|datos|)
        this.datos = (ArrayList<Nodo<T>>) datos.clone(); 
        this.comparator = comparator;
        heapify();
    }
    
    public Nodo<T> maximo(){ //O(1)
        if (len() > 0){
            return (Nodo<T>) datos.get(0); 
        } else {
            return null;
        }
    }

    public void agregar(Nodo<T> dato){ //O(log|datos|)
        datos.add(dato);
        int pos = datos.size()-1;
        dato.pospropia = pos; 
        siftUp(pos);
    }

    public Nodo<T> sacarMaximo(){ // O(log |datos|)
        if (len()==0) return null;
        Nodo<T> dato = (Nodo<T>) datos.get(0);
        Nodo<T> nuevoMax = (Nodo<T>) datos.get(len()-1);
        datos.set(0, nuevoMax);
        nuevoMax.pospropia = 0;
        datos.remove(len()-1);
        siftDown(0);
        return dato;
    }
    
    public int len(){
        return datos.size();
    }

    private void siftUp(int index){ //O(log |datos|)
        while (index != 0 && comparator.compare(datos.get(index), datos.get(indicePadre(index))) > 0){
            int parentIndex  = indicePadre(index);
            Nodo<T> datoactual = datos.get(index);
            Nodo<T> datopadre = datos.get(parentIndex);
            datos.set(index, datopadre);
            datos.set(parentIndex, datoactual);

            datoactual.pospropia = parentIndex;
            datopadre.pospropia = index;

            index = parentIndex;
        }
    }
    
    private void siftDown(int index){ 
        int greatestChildIndex;
        while (indiceHijoIzq(index) < len()){
            Nodo<T> datoactual = datos.get(index);
            if (indiceHijoDer(index) < len() && comparator.compare(datos.get(indiceHijoIzq(index)), datos.get(indiceHijoDer(index))) < 0){
                greatestChildIndex = indiceHijoDer(index);
            } else {
                greatestChildIndex = indiceHijoIzq(index);
            }

            Nodo<T> greatestChild = datos.get(greatestChildIndex);
            if (comparator.compare(datoactual, greatestChild) < 0 ){
                datos.set(index, greatestChild);
                datos.set(greatestChildIndex, datoactual);

                greatestChild.pospropia = datoactual.pospropia;
                datoactual.pospropia = greatestChildIndex;
                
                index = greatestChildIndex;
            } else {
                break;
            }
            
        }
    }

    
    private void heapify(){
        if (len() == 0) return;
        int p2menor = 1;
        while (p2menor*2 < len()){
            p2menor *= 2; 
        }
        
        for (int i = p2menor-2 ;i >= 0; i--){ //arbol de altura n tiene 2^n-1 nodos, -1 mas por indice 0.
            siftDown(i);
        }
    }
    
    public String toString(){
        return datos.toString();
    }

    private int indiceHijoIzq(int index){
        return index*2+1;
    }
    private int indiceHijoDer(int index){
        return index*2+2;
    }

    private int indicePadre(int index){
        return (index-1)/2;
    }

}
