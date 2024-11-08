package aed;

public class Traslado {
    
    int id;
    int origen;
    int destino;
    int gananciaNeta;
    int timestamp;

    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp){
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.gananciaNeta = gananciaNeta;
        this.timestamp = timestamp;
    }

    public String toString(){
        return "T("+Integer.toString(id)+","+Integer.toString(origen)+",-"+Integer.toString(destino)+",-"+Integer.toString(gananciaNeta)+",-"+Integer.toString(timestamp)+")";
    }
}
