package aed;

import java.util.ArrayList;

public class BestEffort {
    private ColaPrioridad<Ciudad> ciudadesSuperavit;
    private ColaPrioridad<Traslado> trasladosAntiguo;
    private ColaPrioridad<Traslado> trasladosRedi;
    private ArrayList<Nodo<Ciudad>> conjCiudades;
    private ArrayList<Integer> ciudadesMasGanancias;
    private ArrayList<Integer> ciudadesMasPerdidas;


    public BestEffort(int cantCiudades, Traslado[] traslados){
        // Implementar
    }

    public void registrarTraslados(Traslado[] traslados){
        // Implementar
    }

    public int[] despacharMasRedituables(int n){
        int i=0;
        int[] res;
        if (n > this.trasladosRedi.len()) {
            res=new int[trasladosRedi.len()];
        } else {
            res= new int[n];
        }
        while (i<n){
            res[i]=(despachar(trasladosRedi, trasladosAntiguo));
            i++;
        }
        return res;
    }

    public int[] despacharMasAntiguos(int n){
        int i=0;
        int[] res;
        if (n > this.trasladosAntiguo.len()) {
            res=new int[trasladosAntiguo.len()];
        } else {
            res= new int[n];
        }
        while (i<n){
            res[i]=(despachar(trasladosAntiguo, trasladosRedi));
            i++;
        }
        return res;
    }

    public int ciudadConMayorSuperavit(){
        // Implementar
        return 0;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        // Implementar
        return null;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        // Implementar
        return null;
    }

    public int gananciaPromedioPorTraslado(){
        // Implementar
        return 0;
    }

    public int despachar(ColaPrioridad<Traslado> heap, ColaPrioridad<Traslado> heapAlterno){
        Nodo<Traslado> nodoActual =heap.eliminar(0);
        int palt = nodoActual.nodoAlterno.pospropia;
        heapAlterno.eliminar(palt);
        for (i in conjCiudades) {
            
        }
        return nodoActual.valor.id;
    }


    
}
