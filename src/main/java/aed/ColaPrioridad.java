package aed;
import java.util.ArrayList;
import java.util.Comparator;

public class ColaPrioridad<T> {
    private ArrayList<Nodo<T>> datos;
    private Comparator<Nodo<T>> comparator;

    public ColaPrioridad(Comparator<Nodo<T>> comparator){ //O(1)
        this.datos = new ArrayList<Nodo<T>>();
        this.comparator = comparator;
    }
    
    public ColaPrioridad(ArrayList<Nodo<T>> datos, Comparator<Nodo<T>> comparator){ //O(|datos|)
        this.datos = (ArrayList<Nodo<T>>) datos.clone(); 

        for (int i = 0; i < datos.size(); i++){ // O(|datos|)
            datos.get(i).pospropia = i;
        }

        this.comparator = comparator;
        heapify();
    }
    
    public void agregar(Nodo<T> dato){ //O(log|datos|)
        datos.add(dato);
        int pos = datos.size()-1;
        dato.pospropia = pos; 
        siftUp(pos);
    }

    public Nodo<T> maximo(){ //O(1)
        if (len() > 0){
            return (Nodo<T>) datos.get(0); 
        } else {
            return null;
        }
    }

    public Nodo<T> eliminar(int pos){
        if (len() <= pos) return null;

        Nodo<T> res = datos.get(pos);
        swap(res, datos.get(len()-1));
        datos.remove(len()-1);
        if(pos != len()){
            siftUp(pos);
            siftDown(pos);
        }
        return res;
    }
    
    public int len(){
        return datos.size();
    }

    public void siftUp(int index){ //O(log |datos|)
        Nodo<T> dato = datos.get(index), padreD = padre(dato);

        while (padreD != null && comparator.compare(dato, padreD) > 0){ 
            swap(dato, padreD);

            padreD = padre(dato);
        }
    }
    
    public void siftDown(int index){ 
        Nodo<T> dato = datos.get(index); 
        Nodo<T> hijoD = hijoDer(dato);
        Nodo<T> hijoI = hijoIzq(dato);
        Nodo<T> mayor = mayorNodo(hijoD, hijoI);
        
        while (mayor != null && comparator.compare(dato, mayor) < 0){
            swap(mayor, dato);
            
            hijoD = hijoDer(dato);
            hijoI = hijoIzq(dato);
            mayor = mayorNodo(hijoI, hijoD);
        }
    }

    private void swap(Nodo<T> nodo1, Nodo<T> nodo2){
        datos.set(nodo1.pospropia, nodo2);
        datos.set(nodo2.pospropia, nodo1);

        int temp = nodo1.pospropia;
        nodo1.pospropia = nodo2.pospropia;
        nodo2.pospropia = temp;
    }

    private Nodo<T> mayorNodo(Nodo<T> nodo1, Nodo<T> nodo2){
        if (nodo1 == null) return nodo2;
        if (nodo2 == null) return nodo1;
        if (comparator.compare(nodo1, nodo2) >= 0){
            return nodo1;
        } else {
            return nodo2;
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
    
    private Nodo<T> hijoIzq(Nodo<T> padre){ 
        if (padre.pospropia*2+1 >= len()){
            return null;
        } else {
            return datos.get(padre.pospropia*2+1);
        }
    }

    private Nodo<T> hijoDer(Nodo<T> padre){
        if (padre.pospropia*2+2 >= len()){
            return null;
        } else {
            return datos.get(padre.pospropia*2+2);
        }
    }
    private Nodo<T> padre(Nodo<T> hijo){
        if (hijo.pospropia == 0){
            return null;
        } else {
            return datos.get((hijo.pospropia-1)/2);
        }
    }

    public String toString(){
        ArrayList<T> arr = new ArrayList<>();
        
        
        for (int i = 0; i<len();i++) {
            arr.add(datos.get(i).valor);
        }
        return arr.toString();
    }

}
