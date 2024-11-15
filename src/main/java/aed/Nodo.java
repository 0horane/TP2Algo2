package aed;

public class Nodo<T> {
    T valor;                // datos contenidos
    int pospropia;          // posicion en heap
    Nodo<T> nodoAlterno;    // referencia a nodo en otro heap, si existe

    public Nodo(T v){
        this.valor = v;
    }

    public Nodo(T v, Nodo<T> nodoAlterno){
        this.valor = v;
        this.nodoAlterno = nodoAlterno;
    }
}
