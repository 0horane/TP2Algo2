package aed;

import java.util.Comparator;

public class CompAntiguedad implements Comparator<Nodo<Traslado>> {
    //Si devuelve positivo, t1 > t2 (t1 más antiguo que t2).
    public int compare(Nodo<Traslado> t1, Nodo<Traslado> t2) {    //El método es O(1)
        int res;    //O(1)
        res = t2.valor.timestamp - t1.valor.timestamp;    //O(1)
        return res;    //O(1)
    }
}