package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Currency;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class BestEffortTests {

    int cantCiudades;
    Traslado[] listaTraslados;
    ArrayList<Integer> actual;
    

    @BeforeEach
    void init(){
        //Reiniciamos los valores de las ciudades y traslados antes de cada test
        cantCiudades = 7;
        listaTraslados = new Traslado[] {
                                            new Traslado(1, 0, 1, 100, 10),
                                            new Traslado(2, 0, 1, 400, 20),
                                            new Traslado(3, 3, 4, 500, 50),
                                            new Traslado(4, 4, 3, 500, 11),
                                            new Traslado(5, 1, 0, 1000, 40),
                                            new Traslado(6, 1, 0, 1000, 41),
                                            new Traslado(7, 6, 3, 2000, 42)
                                        };
    }

    static void assertSetEquals(ArrayList<Integer> s1, ArrayList<Integer> s2) {
        assertEquals(s1.size(), s2.size());
        for (int e1 : s1) {
            boolean encontrado = false;
            for (int e2 : s2) {
                if (e1 == e2) encontrado = true;
            }
            assertTrue(encontrado, "No se encontró el elemento " +  e1 + " en el arreglo " + s2.toString());
        }
    }



    @Test
    void despachar_con_mas_ganancia_de_a_uno(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        sis.despacharMasRedituables(1);
        
        assertSetEquals(new ArrayList<>(Arrays.asList(6)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());

        sis.despacharMasRedituables(1);
        sis.despacharMasRedituables(1);

        assertSetEquals(new ArrayList<>(Arrays.asList(1, 6)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(0, 3)), sis.ciudadesConMayorPerdida());
    }
    
    @Test
    void despachar_con_mas_ganancia_de_a_varios(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        sis.despacharMasRedituables(3);

        assertSetEquals(new ArrayList<>(Arrays.asList(1, 6)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(0, 3)), sis.ciudadesConMayorPerdida());

        sis.despacharMasRedituables(3);

        assertSetEquals(new ArrayList<>(Arrays.asList(1, 6)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());

    }
    
    @Test
    void despachar_mas_viejo_de_a_uno(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);
        
        sis.despacharMasAntiguos(1);

        assertSetEquals(new ArrayList<>(Arrays.asList(0)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(1)), sis.ciudadesConMayorPerdida());

        sis.despacharMasAntiguos(1);
        assertSetEquals(new ArrayList<>(Arrays.asList(4)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());

        sis.despacharMasAntiguos(1);
        assertSetEquals(new ArrayList<>(Arrays.asList(0, 4)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(1, 3)), sis.ciudadesConMayorPerdida());
    }
    
    @Test
    void despachar_mas_viejo_de_a_varios(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);
        
        sis.despacharMasAntiguos(3);
        assertSetEquals(new ArrayList<>(Arrays.asList(0, 4)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(1, 3)), sis.ciudadesConMayorPerdida());

        sis.despacharMasAntiguos(3);
        assertSetEquals(new ArrayList<>(Arrays.asList(1, 6)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());
        
    }
    
    @Test
    void despachar_mixtos(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        sis.despacharMasRedituables(3);
        sis.despacharMasAntiguos(3);
        assertSetEquals(new ArrayList<>(Arrays.asList(1, 6)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());

        sis.despacharMasAntiguos(1);
        assertSetEquals(new ArrayList<>(Arrays.asList(1, 6)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());
        
    }
    
    @Test
    void agregar_traslados(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        Traslado[] nuevos = new Traslado[] {
            new Traslado(8, 0, 1, 10001, 5),
            new Traslado(9, 0, 1, 40000, 2),
            new Traslado(10, 0, 1, 50000, 3),
            new Traslado(11, 0, 1, 50000, 4),
            new Traslado(12, 1, 0, 150000, 1)
        };

        sis.registrarTraslados(nuevos);

        sis.despacharMasAntiguos(4);
        assertSetEquals(new ArrayList<>(Arrays.asList(1)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(0)), sis.ciudadesConMayorPerdida());

        sis.despacharMasRedituables(1);
        assertSetEquals(new ArrayList<>(Arrays.asList(0)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(1)), sis.ciudadesConMayorPerdida());

    }
    
    @Test
    void promedio_por_traslado(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        sis.despacharMasAntiguos(3);
        assertEquals(333, sis.gananciaPromedioPorTraslado());

        sis.despacharMasRedituables(3);
        assertEquals(833, sis.gananciaPromedioPorTraslado());

        Traslado[] nuevos = new Traslado[] {
            new Traslado(8, 1, 2, 1452, 5),
            new Traslado(9, 1, 2, 334, 2),
            new Traslado(10, 1, 2, 24, 3),
            new Traslado(11, 1, 2, 333, 4),
            new Traslado(12, 2, 1, 9000, 1)
        };

        sis.registrarTraslados(nuevos);
        sis.despacharMasRedituables(6);

        assertEquals(1386, sis.gananciaPromedioPorTraslado());
        

    }

    @Test
    void mayor_superavit(){
        Traslado[] nuevos = new Traslado[] {
            new Traslado(1, 3, 4, 1, 7),
            new Traslado(7, 6, 5, 40, 6),
            new Traslado(6, 5, 6, 3, 5),
            new Traslado(2, 2, 1, 41, 4),
            new Traslado(3, 3, 4, 100, 3),
            new Traslado(4, 1, 2, 30, 2),
            new Traslado(5, 2, 1, 90, 1)
        };
        BestEffort sis = new BestEffort(this.cantCiudades, nuevos);

        sis.despacharMasAntiguos(1);
        assertEquals(2, sis.ciudadConMayorSuperavit());

        sis.despacharMasAntiguos(2);
        assertEquals(3, sis.ciudadConMayorSuperavit());

        sis.despacharMasAntiguos(3);
        assertEquals(2, sis.ciudadConMayorSuperavit());

        sis.despacharMasAntiguos(1);
        assertEquals(2, sis.ciudadConMayorSuperavit());

    }

    private static Integer clave(Integer NCLAVES, Integer i) {
        return NCLAVES * ((i * i - 100 * i) % NCLAVES) + i;
    }

    public static <T> void insertionSort(ArrayList<T> list, Comparator<T> cmp){
        for (int i=0;i<list.size();i++){
            int j=i;
            T item = list.get(j);
            while(j!=0 && cmp.compare(list.get(j-1), item)>0){
                list.set(j,list.get(j-1));
                j--;
            }
            list.set(j, item);
        }
    }

    
    static Comparator<Ciudad> cmpCiudadXGanancia = new Comparator<Ciudad>() {
        @Override
        public int compare(Ciudad o1, Ciudad o2) {
            return o1.ganancias-o2.ganancias;
        }
    };
    static Comparator<Ciudad> cmpCiudadXPerdida = new Comparator<Ciudad>() {
        @Override
        public int compare(Ciudad o1, Ciudad o2) {
            return o1.perdidas-o2.perdidas;
        }
    };
    static Comparator<Ciudad> cmpCiudadXSuperavit = new Comparator<Ciudad>() {
        @Override
        public int compare(Ciudad o1, Ciudad o2) {
            int res =  o1.ganancias-o1.perdidas-o2.ganancias+o2.perdidas;
            if (res == 0){
                return o2.ident - o1.ident;
            } else{
                return res;
            }
        }
    };
    static Comparator<Traslado> cmpTrasladoXAntiguedad = new Comparator<Traslado>() {
        @Override
        public int compare(Traslado o1, Traslado o2) {
            return o2.timestamp - o1.timestamp; // masaniguos al final
        }
    };
    static Comparator<Traslado> cmpTrasladoXRedit = new Comparator<Traslado>() {
        @Override
        public int compare(Traslado o1, Traslado o2) {
            return o1.gananciaNeta - o2.gananciaNeta;
        }
    };

    public static void validarTodo(BestEffort sistema, ArrayList<Traslado> traslados, ArrayList<Ciudad> ciudades, int sumaDespachosValor, int cantidadDespachosHechos){
        ArrayList<Ciudad> ciudades2 = (ArrayList<Ciudad>) ciudades.clone();
        insertionSort(ciudades2, cmpCiudadXGanancia);
        
        ArrayList<Integer> ciudadesMayorGanancia = new ArrayList<>();
        int index = ciudades2.size()-1;
        while (index>=0 && cmpCiudadXGanancia.compare(ciudades2.get(index), ciudades2.get(ciudades2.size()-1))==0){
            ciudadesMayorGanancia.add(ciudades2.get(index).ident);
            index-=1;
        }
        assertSetEquals(ciudadesMayorGanancia, sistema.ciudadesConMayorGanancia());

        
        insertionSort(ciudades2, cmpCiudadXPerdida);
        
        ArrayList<Integer> ciudadesMayorPerdida = new ArrayList<>();
        int index2 = ciudades2.size()-1;
        while (index2>=0 && cmpCiudadXPerdida.compare(ciudades2.get(index2), ciudades2.get(ciudades2.size()-1))==0){
            ciudadesMayorPerdida.add(ciudades2.get(index2).ident);
            index2-=1;
        }
        assertSetEquals(ciudadesMayorPerdida, sistema.ciudadesConMayorPerdida());


        
        insertionSort(ciudades2, cmpCiudadXSuperavit);

        assertEquals(ciudades2.get(ciudades2.size()-1).ident, sistema.ciudadConMayorSuperavit());
        if (cantidadDespachosHechos>0){
            assertEquals(sumaDespachosValor/cantidadDespachosHechos, sistema.gananciaPromedioPorTraslado());
        }
    }


    int actualizarYVerificarDespachos(int[] trasladosIds, ArrayList<Traslado> traslados, ArrayList<Ciudad> ciudades){
        int valordespahos = 0; 
        int[] trasladosIDsNuestros = new int[trasladosIds.length]; 
        for(int i=0;i<trasladosIds.length;i++){
            Traslado a = traslados.remove(traslados.size()-1);
            valordespahos += a.gananciaNeta;
            ciudades.get(a.destino).perdidas+=a.gananciaNeta;
            ciudades.get(a.origen).ganancias+=a.gananciaNeta;
            trasladosIDsNuestros[ trasladosIds.length-1-i] = a.id;
        }

        assertArrayEquals(trasladosIds, trasladosIDsNuestros);

        return valordespahos;
    }

    @Test
    void stresstest(){
        int NCLAVES = 8; //multiplo de 4
        int NCIUDADES = 2;
        ArrayList<Ciudad> ciudades = new ArrayList<>();
        for (int i=0;i<NCIUDADES;i++){
            ciudades.add(new Ciudad(i));
        }   

        ArrayList<Traslado> traslados = new ArrayList<>();
        for (int i=0;i<NCLAVES/2;i++){
            traslados.add(new Traslado(i, Math.abs((clave(NCLAVES,i)/3)%NCIUDADES), Math.abs((clave(NCLAVES,i+NCLAVES/2)/3)%NCIUDADES), Math.abs(clave(NCLAVES*2,i)), Math.abs(clave(NCLAVES*2,i+NCLAVES))));
        }

        //registrar trslados por constructor
        BestEffort sistema = BestEffort.nuevoSistema(NCIUDADES, listaTraslados);

        //registrar traslados de a 1
        for (int i=NCLAVES/2;i<NCLAVES/4*3;i++){
            traslados.add(new Traslado(i, Math.abs((clave(NCLAVES,i)/3)%NCIUDADES), Math.abs((clave(NCLAVES,i+NCLAVES/2)/3)%NCIUDADES), Math.abs(clave(NCLAVES*2,i)), Math.abs(clave(NCLAVES*2,i+NCLAVES))));
            sistema.registrarTraslados(new Traslado[]{traslados.get(i)});
        }

        //registrar traslados de a varios
        Traslado[] tmpTraslados = new Traslado[NCLAVES/4]; 
        for (int i=NCLAVES/4*3;i<NCLAVES;i++){
            traslados.add(new Traslado(i, Math.abs((clave(NCLAVES,i)/3)%NCIUDADES), Math.abs((clave(NCLAVES,i+NCLAVES/2)/3)%NCIUDADES), Math.abs(clave(NCLAVES*2,i)), Math.abs(clave(NCLAVES*2,i+NCLAVES))));
            tmpTraslados[i-NCLAVES/4*3] = traslados.get(i);
        }
        sistema.registrarTraslados(tmpTraslados);

        int trasladosValorTotal = 0;

        //validar
        validarTodo(sistema, traslados, ciudades, trasladosValorTotal, 0);

        //despachar reduituables y validar, 2 veces
        insertionSort(traslados, cmpTrasladoXRedit);
        int[] red = sistema.despacharMasRedituables(1);
        trasladosValorTotal += actualizarYVerificarDespachos(red, traslados, ciudades);
        validarTodo(sistema, traslados, ciudades, trasladosValorTotal, 1);

        int[] reds = sistema.despacharMasRedituables(NCLAVES/2-1);
        trasladosValorTotal += actualizarYVerificarDespachos(reds, traslados, ciudades);
        validarTodo(sistema, traslados, ciudades, trasladosValorTotal, NCLAVES/2);
        
        //despachar antiguos y validar, 2 veces
        insertionSort(traslados, cmpTrasladoXAntiguedad);
        int[] ants = sistema.despacharMasAntiguos(NCLAVES/2-1);
        trasladosValorTotal += actualizarYVerificarDespachos(ants, traslados, ciudades);
        validarTodo(sistema, traslados, ciudades, trasladosValorTotal, NCLAVES/2-1);


        int[] ant = sistema.despacharMasAntiguos(1);
        trasladosValorTotal += actualizarYVerificarDespachos(ant, traslados, ciudades);
        validarTodo(sistema, traslados, ciudades, trasladosValorTotal, NCLAVES);
    }
}
