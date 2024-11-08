package aed;

import java.util.ArrayList;

public class BestEffort {
    private ColaPrioridad<Ciudad> ciudadesSuperavit;
    private ColaPrioridad<Traslado> trasladosAntiguo;
    private ColaPrioridad<Traslado> trasladosRedi;
    private ArrayList<Nodo<Ciudad>> conjCiudaes;
    private ArrayList<Integer> ciudadesMasGanancias;
    private ArrayList<Integer> ciudadesMasPerdidas;


    public BestEffort(int cantCiudades, Traslado[] traslados){
        // Implementar
    }

    public void registrarTraslados(Traslado[] traslados){
        int i = 0;
        while(i < traslados.length){
            Nodo<Traslado> nuevo = new Nodo<Traslado>(traslados[i]);
            Nodo<Traslado> nuevoalt = new Nodo<Traslado>(traslados[i], nuevo);
            nuevo.nodoAlterno = nuevoalt;
            this.trasladosAntiguo.agregar(nuevo);
            this.trasladosRedi.agregar(nuevoalt);
            i ++;
        }
    }

    public int[] despacharMasRedituables(int n){
        // Implementar
        return null;
    }

    public int[] despacharMasAntiguos(int n){
        // Implementar
        return null;
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
    
}
