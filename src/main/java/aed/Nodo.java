package aed;

public class Nodo<T> {
    T valor;
    int pospropia;
    Nodo<T> nodoAlterno;

    public Nodo(T v, int pos){
        this.valor = v;
        this.pospropia = pos;
    }
}
