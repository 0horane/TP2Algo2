package aed;

public class CompSuperavit implements Comparator<Ciudad>{
    //Si devuelve positivo, c1 > c2 (c1 mayor superavit que t2).
    public int comparar(Nodo<Ciudad> c1, Nodo<Ciudad> c2){
        int res;
        int c1sup = c1.valor.superavit();
        int c2sup = c2.valor.superavit();
        res = c1sup - c2sup; 
        if (res == 0) {
            res = c2.valor.ident - c1.valor.ident; //Ojo, c1 > c2, en caso de empate, si c1.ident < c2.ident.
        } //En vez del atributo ident, usar el valor.pospropia del nodo que está en conjCiudades.
        return res;
    }
}
