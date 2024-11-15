package aed;

import java.util.ArrayList;

public class BestEffort {
    private ColaPrioridad<Ciudad> ciudadesSuperavit;
    private ColaPrioridad<Traslado> trasladosAntiguo;
    private ColaPrioridad<Traslado> trasladosRedi;
    private ArrayList<Nodo<Ciudad>> conjCiudades;
    private ArrayList<Integer> MasGanancias;
    private ArrayList<Integer> MasPerdidas;
    private int gananciasTotales = 0;    // O(1)
    private int despachosHechos = 0;     // O(1)



    public BestEffort(int cantCiudades, Traslado[] traslados){
        // inicializar y llenar todos los arreglos
        conjCiudades = new ArrayList<>();    // O(1)
        MasGanancias = new ArrayList<>();    // O(1)
        MasPerdidas = new ArrayList<>();    // O(1)
        for (int i = 0; i < cantCiudades; i++){    // Se itera |C| veces. En conjunto queda O(|C|).
            Nodo<Ciudad> ciudad = new Nodo<Ciudad>(new Ciudad(i));    // O(1)
            conjCiudades.add(ciudad);    // O(1)
            MasGanancias.add(i);         // O(1)
            MasPerdidas.add(i);          // O(1)
        }
        
        ArrayList<Nodo<Traslado>> arrTrasladosAntiguo = new ArrayList<>();    // O(1)
        ArrayList<Nodo<Traslado>> arrTrasladosRedi = new ArrayList<>();       // O(1)
        for (int i = 0; i < traslados.length; i++){    // Se itera |T| veces. En conjunto queda O(T).
            Nodo<Traslado> nodo = new Nodo<>(traslados[i]);    // O(1)
            Nodo<Traslado> nodoRef = new Nodo<>(traslados[i], nodo);    // O(1)
            nodo.nodoAlterno = nodoRef;    // O(1)

            arrTrasladosAntiguo.add(nodo);    // O(1)
            arrTrasladosRedi.add(nodoRef);    // O(1)
        }

        //generar colas de prioridad usando heapify de los arreglos que corresponde.
        ciudadesSuperavit = new ColaPrioridad<>(conjCiudades, new CompSuperavit());    // O(|C|)
                                                                                       //
        this.trasladosAntiguo = new ColaPrioridad<>(arrTrasladosAntiguo, new CompAntiguedad());    // O(|T|)
        this.trasladosRedi = new ColaPrioridad<>(arrTrasladosRedi, new CompRedituabilidad());      // O(|T|)

    }

    public void registrarTraslados(Traslado[] traslados){    // El metodo es O(|traslados| log|T|).
        int i = 0;    // O(1)
        while(i < traslados.length){    // Son |traslados| ciclos. En conjunto queda O(|traslados| log|T|)
            Nodo<Traslado> nuevo = new Nodo<Traslado>(traslados[i]);              // O(1)
            Nodo<Traslado> nuevoalt = new Nodo<Traslado>(traslados[i], nuevo);    // O(1)
            nuevo.nodoAlterno = nuevoalt;    // O(1)
            this.trasladosAntiguo.agregar(nuevo);    // O(log|T|)
            this.trasladosRedi.agregar(nuevoalt);    // O(log|T|)
            i ++;    // O(1)
        }
    }

    public int[] despacharMasRedituables(int n){    // El metodo es O(n (log|T| + log|C|)).
        return despacharNVeces(trasladosRedi, trasladosAntiguo, n);    // O(n (log|T| + log|C|))
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

    public int[] despacharMasAntiguos(int n){    // El metodo es O(n (log|T| + log|C|)).
        return despacharNVeces(trasladosAntiguo, trasladosRedi, n);    // O(n (log|T| + log|C|))
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

    public int ciudadConMayorSuperavit(){    // El metodo es O(1).
        return this.ciudadesSuperavit.maximo().valor.ident;    // O(1)
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){    // El metodo es O(1).
        return this.MasGanancias;    // O(1)
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){    // El metodo es O(1).
        return this.MasPerdidas;    // O(1)
    }

    public int gananciaPromedioPorTraslado(){    // El metodo es O(1).
        return this.gananciasTotales/this.despachosHechos;    // O(1)
    }

    public int[] despacharNVeces(ColaPrioridad<Traslado> heap, ColaPrioridad<Traslado> heapAlterno, int n){ // El metodo es O(n (log|T| + log|C|)).
        int i=0;    // O(1).
        int[] res;
        if (n > heap.len()) {           // O(1)
            res=new int[heap.len()];    // O(|T|), como n > |T|, tambien dentro de O(n).
        } else {
            res= new int[n];            // O(n)
        }
        while (i< res.length){    // Como maximo n ciclos. En conjunto queda O(n (log|T| + log|C|)).
            Nodo<Traslado> nodoActual =heap.eliminar(0);    // O(log|T|)
            int palt = nodoActual.nodoAlterno.pospropia;    // O(1)
             heapAlterno.eliminar(palt);                    // O(log|T|)
            res[i]=nodoActual.valor.id;                     // O(1)
            conjCiudades.get(nodoActual.valor.origen).valor.agregarGanancia(nodoActual.valor.gananciaNeta);    // O(1)
            conjCiudades.get(nodoActual.valor.destino).valor.agregarPerdidas(nodoActual.valor.gananciaNeta);    // O(1)
            // actualizarGanancias(nodoActual.valor.origen);
            // actualizarPerdidas(nodoActual.valor.destino);
            // actualizarBalance(nodoActual.valor.origen, conjCiudades.get(i).valor.ganancias, conjCiudades.get(MasGanancias.get(0)).valor.ganancias, MasGanancias.get(0), MasGanancias);
            // actualizarBalance(nodoActual.valor.destino, conjCiudades.get(i).valor.perdidas, conjCiudades.get(MasPerdidas.get(0)).valor.perdidas, MasPerdidas.get(0), MasPerdidas);
            actualizarBalance(nodoActual);    // O(1)
            ciudadesSuperavit.siftUp(conjCiudades.get(nodoActual.valor.origen).pospropia);        // O(log|C|)
            ciudadesSuperavit.siftDown(conjCiudades.get(nodoActual.valor.destino).pospropia);     // O(log|C|)
            this.despachosHechos ++;    // O(1)
            this.gananciasTotales +=nodoActual.valor.gananciaNeta;    // O(1)
            i++;    // O(1)
        }
        // La complejidad es de O(1 + 1 + n + n (log|T| + log|C|)) = O(n (log|T| + log|C|)).
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

        public void actualizarBalance(Nodo<Traslado> nodoActual){    // El metodo es O(1).
            int orig = nodoActual.valor.origen;     // O(1)
            int dest = nodoActual.valor.destino;    // O(1)
            resolverMaximo(orig, conjCiudades.get(orig).valor.ganancias, conjCiudades.get(MasGanancias.get(0)).valor.ganancias, MasGanancias.get(0), MasGanancias);    // O(1)
            resolverMaximo(dest, conjCiudades.get(dest).valor.perdidas, conjCiudades.get(MasPerdidas.get(0)).valor.perdidas, MasPerdidas.get(0), MasPerdidas);    // O(1)
        }
        public void resolverMaximo(int i, int valorAnalizado, int valorPrimerPos, int posPrimerPos, ArrayList<Integer> lista){    // El metodo es O(1).
            if (valorAnalizado > valorPrimerPos || (i == posPrimerPos)){    // O()
                lista.clear();                   // O(1)
                Integer j = (Integer) i;         // O(1)
                lista.add(j);                    // O(1)
            } else if (valorAnalizado == valorPrimerPos){     // O(1)
                Integer j = (Integer) i;         // O(1)
                lista.add(j);                    // O(1)
            }}


}
