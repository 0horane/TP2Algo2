package aed;

import java.util.Comparator;

public class CompAntiguedad implements Comparator<Nodo<Traslado>> {
    //Si devuelve positivo, t1 > t2 (t1 más antiguo que t2).
    public int compare(Nodo<Traslado> t1, Nodo<Traslado> t2) {
        int res;
        res = t2.valor.timestamp - t1.valor.timestamp;
        return res;
    }
}

//Redordar que en el Heap de Traslados por Antiguedad se alojan los valores.