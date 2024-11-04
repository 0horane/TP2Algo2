package aed;
import java.util.ArrayList;
import java.util.Comparator;

public class ColaPrioridad<T> {
    private ArrayList<T> datos;
    private Comparator<T> comparator;

    public ColaPrioridad(Comparator<T> comparator){ //O(1)
        this.datos = new ArrayList<T>();
        this.comparator = comparator;
    }
    
    public ColaPrioridad(ArrayList<T> datos, Comparator<T> comparator){ //O(|datos|)
        this.datos = (ArrayList<T>) datos.clone(); 
        this.comparator = comparator;
        heapify();
    }
    
    public T maximo(){ //O(1)
        if (len() > 0){
            return (T) datos.get(0); 
        } else {
            return null;
        }
    }

    public void agregar(T dato){ //O(log|datos|)
        datos.add(dato);
        siftUp(datos.size()-1);
    }

    public T sacarMaximo(){ // O(log |datos|)
        if (len()==0) return null;
        T dato = (T) datos.get(0);
        datos.set(0, datos.get(len()-1));
        datos.remove(len()-1);
        siftDown(0);
        siftDown(0);
        return dato;
    }
    
    public int len(){
        return datos.size();
    }

    private void siftUp(int index){ //O(log |datos|)
        while (index != 0 && comparator.compare(datos.get(index), datos.get(indicePadre(index))) > 0){
            int parentIndex  = indicePadre(index);
            T datoactual = datos.get(index);
            datos.set(index, datos.get(parentIndex));
            datos.set(parentIndex, datoactual);
            index = parentIndex;
        }
    }
    
    private void siftDown(int index){ 
        int childIndex;
        while (indiceHijoIzq(index) < len()){
            T datoactual = datos.get(index);

            if (comparator.compare(datoactual, datos.get(indiceHijoIzq(index))) < 0)
                childIndex = indiceHijoIzq(index);
            else if (  indiceHijoDer(index) < len() &&  comparator.compare(datoactual, datos.get(indiceHijoDer(index))) < 0)
                childIndex = indiceHijoDer(index);
            else break;
            

            datos.set(index, datos.get(childIndex));
            datos.set(childIndex, datoactual);
            index = childIndex;
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
