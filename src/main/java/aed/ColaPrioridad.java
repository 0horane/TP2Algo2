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
    
    public ColaPrioridad(ArrayList<Nodo<T>> datos, Comparator<Nodo<T>> comparator){ //O(|datos|)
        this.datos = (ArrayList<Nodo<T>>) datos.clone(); 

        for (int i = 0; i < datos.size(); i++){ // O(|datos|)
            datos.get(i).pospropia = i;
        }

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

    public Nodo<T> eliminar(int pos){
        Nodo<T> res = datos.get(pos);
        datos.set(pos, datos.get(datos.size()));
        siftUp(pos);
        siftDown(pos);
        datos.remove(datos.size());
        return res;
    }

    public Nodo<T> sacarMaximo(){ // O(log |datos|)
        if (len()==0) return null;
        Nodo<T> dato = (Nodo<T>) datos.get(0);
        Nodo<T> nuevoMax = (Nodo<T>) datos.get(len()-1);
        swap(dato, nuevoMax);
        datos.remove(len()-1);
        siftDown(0);
        return dato;
    }
    
    public int len(){
        return datos.size();
    }

    private void siftUp(int index){ //O(log |datos|)
        while (existeIndicePadre(index) && comparator.compare(datos.get(index), datos.get(indicePadre(index))) > 0){
            int parentIndex  = indicePadre(index);
            Nodo<T> datoactual = datos.get(index);
            Nodo<T> datopadre = datos.get(parentIndex);

            swap(datoactual, datopadre);

            index = parentIndex;
        }
    }
    
    private void siftDown(int index){ 
        int greatestChildIndex;
        while (existeIndiceHijoIzq(index)){
            Nodo<T> datoactual = datos.get(index);
            if (existeIndiceHijoDer(index) && comparator.compare(hijoIzq(datoactual), hijoDer(datoactual)) < 0){
                greatestChildIndex = indiceHijoDer(index);
            } else {
                greatestChildIndex = indiceHijoIzq(index);
            }

            Nodo<T> greatestChild = datos.get(greatestChildIndex);
            if (comparator.compare(datoactual, greatestChild) < 0 ){
                swap(greatestChild, datoactual);
                
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

    private Nodo<T> hijoIzq(Nodo<T> padre){
        return datos.get(padre.pospropia*2+1);
    }

    private Nodo<T> hijoDer(Nodo<T> padre){
        return datos.get(padre.pospropia*2+2);
    }
    private Nodo<T> padre(Nodo<T> hijo){
        return datos.get((hijo.pospropia-1)/2);
    }

    private Boolean existeHijoIzq(Nodo<T> padre){
        return padre.pospropia*2+1 < len();
    }

    private Boolean existeHijoDer(Nodo<T> padre){
        return padre.pospropia*2+2 < len();
    }

    private Boolean existePadre(Nodo<T> hijo){
        return hijo.pospropia != 0;
    }

    private Boolean existeIndiceHijoIzq(int index){
        return index*2+1 < len();
    }

    private Boolean existeIndiceHijoDer(int index){
        return index*2+2 < len();
    }

    private Boolean existeIndicePadre(int index){
        return index != 0;
    }


    private void swap(Nodo<T> nodo1, Nodo<T> nodo2){
        datos.set(nodo1.pospropia, nodo2);
        datos.set(nodo2.pospropia, nodo1);

        int temp = nodo1.pospropia;
        nodo1.pospropia = nodo2.pospropia;
        nodo2.pospropia = temp;
    }


}
