package aed;

import java.util.Comparator;

public class CompInteger implements Comparator<Nodo<Integer>> {
    public int compare(Nodo<Integer> t1, Nodo<Integer> t2) {
        int res;
        res = t1.valor - t2.valor;
        return res;
    }
}
