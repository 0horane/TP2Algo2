package aed;

public class Nodo<T> {
    T valor;                // datos contenidos
    int pospropia;          // posicion en heap
    Nodo<T> nodoAlterno;    // referencia a nodo en otro heap, si existe

    public Nodo(T v){    //El método es O(1)
        this.valor = v;    //O(1)
    }

    public Nodo(T v, Nodo<T> nodoAlterno){    //El método es O(1)
        this.valor = v;    //O(1)
        this.nodoAlterno = nodoAlterno;    //O(1)
    }
}
