package aed;

import java.util.Comparator;

public class CompSuperavit implements Comparator<Nodo<Ciudad>>{
    //Si devuelve positivo, c1 > c2 (c1 mayor superavit que t2).
    public int compare(Nodo<Ciudad> c1, Nodo<Ciudad> c2){    //El método es O(1)
        int res;    //O(1)
        int c1sup = c1.valor.superavit();    //O(1)
        int c2sup = c2.valor.superavit();    //O(1)
        res = c1sup - c2sup;    //O(1) 
        if (res == 0) {    //O(1)
            res = c2.valor.ident - c1.valor.ident; //Ojo, c1 > c2, en caso de empate, si c1.ident < c2.ident. Es O(1)
        }
        return res;    //O(1)
    }
}
