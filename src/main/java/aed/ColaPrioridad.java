package aed;
import java.util.ArrayList;
import java.util.Comparator;

public class ColaPrioridad<T> {
    private ArrayList<Nodo<T>> datos;
    private Comparator<Nodo<T>> comparator;

    public ColaPrioridad(Comparator<Nodo<T>> comparator){ //O(1)
        this.datos = new ArrayList<Nodo<T>>();  //O(1)
        this.comparator = comparator;   //O(1)
    }
    
    public ColaPrioridad(ArrayList<Nodo<T>> datos, Comparator<Nodo<T>> comparator){ //O(|datos|)
        this.datos = new ArrayList<Nodo<T>>( datos);//.clone();    //O(|datos|) 

        for (int i = 0; i < datos.size(); i++){ // O(|datos|) porque itera |datos| veces
            datos.get(i).pospropia = i; //O(1)
        }

        this.comparator = comparator;   //O(1)
        heapify();  //O(|datos|)
    }
    
    public void agregar(Nodo<T> dato){ //O(log|datos|)
        datos.add(dato);    //O(1)
        int pos = datos.size()-1;   //O(1)
        dato.pospropia = pos;   //O(1)
        siftUp(pos);    //O(log|datos|)
    }

    public Nodo<T> maximo(){ //O(1)
        if (len() > 0){    //O(1)
            return (Nodo<T>) datos.get(0);    //O(1)
        } else {
            return null;    //O(1)
        }
    }

    public Nodo<T> eliminar(int pos){    //El metodo es O(log|datos|)
        if (len() <= pos) return null;    //O(1)

        Nodo<T> res = datos.get(pos);    //O(1)
        swap(res, datos.get(len()-1));    //O(1)
        datos.remove(len()-1);    //O(1)
        if(pos != len()){    //O(1)
            siftUp(pos);    //O(log|datos|)
            siftDown(pos);    //O(log|datos|)
        }
        return res;
    }
    
    public int len(){    //O(1)
        return datos.size();
    }

    public void siftUp(int index){ //O(log |datos|)
        Nodo<T> dato = datos.get(index), padreD = padre(dato);    //O(1)

        while (padreD != null && comparator.compare(dato, padreD) > 0){    //O(log|datos|), el ciclo se repite como maximo log|datos| veces. 
            swap(dato, padreD);    //O(1)

            padreD = padre(dato);    //O(1)
        }
    }
    
    public void siftDown(int index){     //El método es O(log|datos|)
        Nodo<T> dato = datos.get(index);     //O(1)
        Nodo<T> hijoD = hijoDer(dato);    //O(1)
        Nodo<T> hijoI = hijoIzq(dato);    //O(1)
        Nodo<T> mayor = mayorNodo(hijoD, hijoI);    //O(1)
        
        while (mayor != null && comparator.compare(dato, mayor) < 0){    //O(log|datos|), el ciclo se repite como maximo log|datos| veces. 
            swap(mayor, dato);    //O(1)
            
            hijoD = hijoDer(dato);    //O(1)
            hijoI = hijoIzq(dato);    //O(1)
            mayor = mayorNodo(hijoI, hijoD);    //O(1)
        }
    }

    private void swap(Nodo<T> nodo1, Nodo<T> nodo2){    //El método es O(1)
        datos.set(nodo1.pospropia, nodo2);    //O(1)
        datos.set(nodo2.pospropia, nodo1);    //O(1)

        int temp = nodo1.pospropia;    //O(1)
        nodo1.pospropia = nodo2.pospropia;    //O(1)
        nodo2.pospropia = temp;    //O(1)
    }

    private Nodo<T> mayorNodo(Nodo<T> nodo1, Nodo<T> nodo2){    //El método es O(1)
        if (nodo1 == null) return nodo2;    //O(1)
        if (nodo2 == null) return nodo1;    //O(1)
        if (comparator.compare(nodo1, nodo2) >= 0){    //O(1)
            return nodo1;    //O(1)
        } else {
            return nodo2;    //O(1)
        }
    }
    
    private void heapify(){    //El metodo es O(|datos|) porque sigue el algoritmo de Floyd.
        if (len() == 0) return;    //O(1)
        int p2menor = 1;    //O(1)
        while (p2menor*2 <= len()){    //El ciclo se repite log|datos| veces. Entonces es O(log|datos|)
            p2menor *= 2;    //O(1) 
        }
        
        for (int i = p2menor-2 ;i >= 0; i--){ //arbol de altura n tiene 2^n-1 nodos, -1 mas por indice 0. La complejidad es de O(|datos|)
            siftDown(i);    
            //La anteúltima fila el siftdown es 1 cambio, la antepenúltima son 2 como máximo y asi sucesivamernte hasta la primer fila que es como máximo O(log|datos|)
        }
        // O(|datos| + log|datos|) = O(|datos|)
    }
    
    private Nodo<T> hijoIzq(Nodo<T> padre){    //El metodo es O(1)
        if (padre.pospropia*2+1 >= len()){    //O(1)
            return null;    //O(1)
        } else {
            return datos.get(padre.pospropia*2+1);    //O(1)
        }
    }

    private Nodo<T> hijoDer(Nodo<T> padre){    //El metodo es O(1)
        if (padre.pospropia*2+2 >= len()){    //O(1)
            return null;    //O(1)
        } else {
            return datos.get(padre.pospropia*2+2);    //O(1)
        }
    }
    private Nodo<T> padre(Nodo<T> hijo){    //El método es O(1)
        if (hijo.pospropia == 0){    //O(1)
            return null;    //O(1)
        } else {
            return datos.get((hijo.pospropia-1)/2);    //O(1)
        }
    }

    public String toString(){    //Este método es para facilitar los test, no nos interesa su complejidad
        ArrayList<T> arr = new ArrayList<>();
        
        
        for (int i = 0; i<len();i++) {
            arr.add(datos.get(i).valor);
        }
        return arr.toString();
    }


    

}
