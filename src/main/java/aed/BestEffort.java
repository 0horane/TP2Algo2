package aed;

import java.util.ArrayList;

public class BestEffort {
    private ColaPrioridad<Ciudad> ciudadesSuperavit;
    private ColaPrioridad<Traslado> trasladosAntiguo;
    private ColaPrioridad<Traslado> trasladosRedi;
    private ArrayList<Nodo<Ciudad>> conjCiudades;
    private ArrayList<Integer> ciudadesMasGanancias;
    private ArrayList<Integer> ciudadesMasPerdidas;
    private int gananciasTotales = 0;
    private int despachosHechos = 0;



    public BestEffort(int cantCiudades, Traslado[] traslados){
        conjCiudades = new ArrayList<>();
        ciudadesMasGanancias = new ArrayList<>();
        ciudadesMasPerdidas = new ArrayList<>();
        for (int i = 0; i < cantCiudades; i++){
            Nodo<Ciudad> ciudad = new Nodo<Ciudad>(new Ciudad(i));
            conjCiudades.add(ciudad);
            ciudadesMasGanancias.add(i);
            ciudadesMasPerdidas.add(i);
        }
        ciudadesSuperavit = new ColaPrioridad<>(conjCiudades, new CompSuperavit());




        ArrayList<Nodo<Traslado>> arrTrasladosAntiguo = new ArrayList<>();
        ArrayList<Nodo<Traslado>> arrTrasladosRedi = new ArrayList<>();
        for (int i = 0; i < traslados.length; i++){
            Nodo<Traslado> nodo = new Nodo<>(traslados[i]);
            Nodo<Traslado> nodoRef = new Nodo<>(null, nodo);
            nodo.nodoAlterno = nodoRef;

            arrTrasladosAntiguo.add(nodo);
            arrTrasladosRedi.add(nodoRef);
        }
        
        trasladosAntiguo = new ColaPrioridad<>(arrTrasladosAntiguo, new CompAntiguedad());
        trasladosAntiguo = new ColaPrioridad<>(arrTrasladosRedi, new CompRedituabilidad());


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
