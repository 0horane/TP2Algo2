package aed;

import java.util.Comparator;

public class CompRedituabilidad implements Comparator<Nodo<Traslado>>{
    //Ojo, queda modificarlo, porque el valor va a estar en el nodo de su contraparte.
    public int compare(Nodo<Traslado> t1, Nodo<Traslado> t2) {
        int res;
        res = t1.nodoAlterno.valor.gananciaNeta - t2.nodoAlterno.valor.gananciaNeta;
        if (res == 0) {
            res = t2.nodoAlterno.valor.id - t1.nodoAlterno.valor.id; //Ojo, t1 > t2, en caso de empate, si t1.id < t2.id.
        }
        return res;
    }
}

//Redordar que en el Heap de Traslados por Antiguedad se alojan los valores.