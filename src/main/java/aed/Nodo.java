package aed;

public class Nodo<T> {
    T valor;
    int pospropia;
    Nodo<T> nodoAlterno;

    public Nodo(T v){
        this.valor = v;
    }

    public Nodo(T v, Nodo<T> nodoAlterno){
        this.valor = v;
        this.nodoAlterno = nodoAlterno;
    }
}
