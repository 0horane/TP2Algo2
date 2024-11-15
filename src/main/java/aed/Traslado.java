package aed;

public class Traslado {
    
    int id;
    int origen;
    int destino;
    int gananciaNeta;
    int timestamp;

    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp){    //El método es O(1)
        this.id = id;    //O(1)
        this.origen = origen;    //O(1)
        this.destino = destino;    //O(1)
        this.gananciaNeta = gananciaNeta;    //O(1)
        this.timestamp = timestamp;    //O(1)
    }

    public String toString(){   //Este método es para uso de los tests, no nos interesa su complejidad
        return "T("+Integer.toString(id)+","+Integer.toString(origen)+",-"+Integer.toString(destino)+",-"+Integer.toString(gananciaNeta)+",-"+Integer.toString(timestamp)+")";
    }
}
