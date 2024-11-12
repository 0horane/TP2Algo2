package aed;

import java.util.ArrayList;

public class BestEffort {
    private ColaPrioridad<Ciudad> ciudadesSuperavit;
    private ColaPrioridad<Traslado> trasladosAntiguo;
    private ColaPrioridad<Traslado> trasladosRedi;
    private ArrayList<Nodo<Ciudad>> conjCiudades;
    private ArrayList<Integer> MasGanancias;
    private ArrayList<Integer> MasPerdidas;
    private int gananciasTotales = 0;
    private int despachosHechos = 0;



    public BestEffort(int cantCiudades, Traslado[] traslados){
        conjCiudades = new ArrayList<>();
        MasGanancias = new ArrayList<>();
        MasPerdidas = new ArrayList<>();
        for (int i = 0; i < cantCiudades; i++){
            Nodo<Ciudad> ciudad = new Nodo<Ciudad>(new Ciudad(i));
            conjCiudades.add(ciudad);
            MasGanancias.add(i);
            MasPerdidas.add(i);
        }
        ciudadesSuperavit = new ColaPrioridad<>(conjCiudades, new CompSuperavit());
        for (int i = 0; i < conjCiudades.size(); i++){
            Nodo<Ciudad> ciudadalt = new Nodo<Ciudad>(conjCiudades.get(i).valor,conjCiudades.get(i));
            ciudadesSuperavit.agregar(ciudadalt);
        }



        ArrayList<Nodo<Traslado>> arrTrasladosAntiguo = new ArrayList<>();
        ArrayList<Nodo<Traslado>> arrTrasladosRedi = new ArrayList<>();
        for (int i = 0; i < traslados.length; i++){
            Nodo<Traslado> nodo = new Nodo<>(traslados[i]);
            Nodo<Traslado> nodoRef = new Nodo<>(traslados[i], nodo);
            nodo.nodoAlterno = nodoRef;

            arrTrasladosAntiguo.add(nodo);
            arrTrasladosRedi.add(nodoRef);
        }
        
        this.trasladosAntiguo = new ColaPrioridad<>(arrTrasladosAntiguo, new CompAntiguedad());
        this.trasladosRedi = new ColaPrioridad<>(arrTrasladosRedi, new CompRedituabilidad());


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
        return despacharNVeces(trasladosRedi, trasladosAntiguo, n);
        // int i=0;
        // int[] res;
        // if (n > this.trasladosRedi.len()) {
        //     res=new int[trasladosRedi.len()];
        // } else {
        //     res= new int[n];
        // }
        // while (i<n){
        //     res[i]=(despachar(trasladosRedi, trasladosAntiguo));
        //     i++;
        // }
        // return res;
    }

    public int[] despacharMasAntiguos(int n){
        return despacharNVeces(trasladosAntiguo, trasladosRedi, n);
        // int i=0;
        // int[] res;
        // if (n > this.trasladosAntiguo.len()) {
        //     res=new int[trasladosAntiguo.len()];
        // } else {
        //     res= new int[n];
        // }
        // while (i<n){
        //     res[i]=(despachar(trasladosAntiguo, trasladosRedi));
        //     i++;
        // }
    }

    public int ciudadConMayorSuperavit(){
        return this.ciudadesSuperavit.maximo().valor.ident;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        return this.MasGanancias;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        return this.MasPerdidas;
    }

    public int gananciaPromedioPorTraslado(){
        return this.gananciasTotales/this.despachosHechos;
    }

    public int[] despacharNVeces(ColaPrioridad<Traslado> heap, ColaPrioridad<Traslado> heapAlterno, int n){
        int i=0;
        int[] res;
        if (n > heap.len()) {
            res=new int[heap.len()];
        } else {
            res= new int[n];
        }
        while (i< res.length){
            Nodo<Traslado> nodoActual =heap.eliminar(0);
            int palt = nodoActual.nodoAlterno.pospropia;
             heapAlterno.eliminar(palt);
            res[i]=nodoActual.valor.id;
            conjCiudades.get(nodoActual.valor.origen).valor.agregarGanancia(nodoActual.valor.gananciaNeta);
            conjCiudades.get(nodoActual.valor.destino).valor.agregarPerdidas(nodoActual.valor.gananciaNeta);
            // actualizarGanancias(nodoActual.valor.origen);
            // actualizarPerdidas(nodoActual.valor.destino);
            // actualizarBalance(nodoActual.valor.origen, conjCiudades.get(i).valor.ganancias, conjCiudades.get(MasGanancias.get(0)).valor.ganancias, MasGanancias.get(0), MasGanancias);
            // actualizarBalance(nodoActual.valor.destino, conjCiudades.get(i).valor.perdidas, conjCiudades.get(MasPerdidas.get(0)).valor.perdidas, MasPerdidas.get(0), MasPerdidas);
            actualizarBalance(nodoActual);
            ciudadesSuperavit.siftUp(conjCiudades.get(nodoActual.valor.origen).pospropia);
            ciudadesSuperavit.siftDown(conjCiudades.get(nodoActual.valor.destino).pospropia);
            this.despachosHechos ++;
            this.gananciasTotales +=nodoActual.valor.gananciaNeta;
            i++;
        }
        return res;
    }

    // public void actualizarGanancias(int i){
    //     if (conjCiudades.get(i).valor.ganancias> conjCiudades.get(MasGanancias.get(0)).valor.ganancias
    //         || (i == MasGanancias.get(0) && conjCiudades.get(i).valor.ganancias == conjCiudades.get(MasGanancias.get(0)).valor.ganancias)){
    //         MasGanancias.clear();
    //         Integer j = (Integer) i;
    //         MasGanancias.add(j);
    //     } else if (conjCiudades.get(i).valor.ganancias== conjCiudades.get(MasGanancias.get(0)).valor.ganancias){
    //         Integer j = (Integer) i;
    //         MasGanancias.add(j);
    //     }}

    // public void actualizarPerdidas(int i){
    //     if (conjCiudades.get(i).valor.perdidas> conjCiudades.get(MasPerdidas.get(0)).valor.perdidas
    //         || (i == MasPerdidas.get(0) && conjCiudades.get(i).valor.perdidas == conjCiudades.get(MasPerdidas.get(0)).valor.perdidas)){
    //         MasPerdidas.clear();
    //         Integer j = (Integer) i;
    //         MasPerdidas.add(j);
    //     } else if (conjCiudades.get(i).valor.perdidas== conjCiudades.get(MasPerdidas.get(0)).valor.perdidas){
    //         Integer j = (Integer) i;
    //         MasPerdidas.add(j);
    //     }}

        // public void actualizarBalance(int i, int valorAnalizado, int valorPrimerPos, int posPrimerPos, ArrayList<Integer> lista){
        //     if (valorAnalizado > valorAnalizado || (i == posPrimerPos && valorAnalizado == valorPrimerPos)){
        //         lista.clear();
        //         Integer j = (Integer) i;
        //         lista.add(j);
        //     } else if (valorAnalizado == valorPrimerPos){
        //         Integer j = (Integer) i;
        //         lista.add(j);
        //     }}

        public void actualizarBalance(Nodo<Traslado> nodoActual){
            int orig = nodoActual.valor.origen;
            int dest = nodoActual.valor.destino;
            resolverMaximo(orig, conjCiudades.get(orig).valor.ganancias, conjCiudades.get(MasGanancias.get(0)).valor.ganancias, MasGanancias.get(0), MasGanancias);
            resolverMaximo(dest, conjCiudades.get(dest).valor.perdidas, conjCiudades.get(MasPerdidas.get(0)).valor.perdidas, MasPerdidas.get(0), MasPerdidas);
        }
        public void resolverMaximo(int i, int valorAnalizado, int valorPrimerPos, int posPrimerPos, ArrayList<Integer> lista){
            if (valorAnalizado > valorPrimerPos || (i == posPrimerPos)){
                lista.clear();
                Integer j = (Integer) i;
                lista.add(j);
            } else if (valorAnalizado == valorPrimerPos){
                Integer j = (Integer) i;
                lista.add(j);
            }}


}
