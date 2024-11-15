package aed;

import java.util.Comparator;

public class CompRedituabilidad implements Comparator<Nodo<Traslado>>{
    public int compare(Nodo<Traslado> t1, Nodo<Traslado> t2) {    //El metodo es O(1)
        int res;    //O(1)
        res = t1.valor.gananciaNeta - t2.valor.gananciaNeta;    //O(1)
        if (res == 0) {    //O(1)
            res = t2.valor.id - t1.valor.id; //Ojo, t1 > t2, en caso de empate, si t1.id < t2.id. Es O(1)
        }
        return res;    //O(1)
    }
}
