package aed;

public class Ciudad {
    public int ident;
    public int ganancias;
    public int perdidas;

    public Ciudad(int i) {    //El metodo es O(1)
        this.ident = i;
        this.ganancias = 0;
        this.perdidas = 0;
    }

    public void agregarGanancia(int n) {    //El metodo es O(1)
        this.ganancias = this.ganancias + n;
    }

    public void agregarPerdidas(int n) {    //El metodo es O(1)
        this.perdidas = this.perdidas + n;
    }

    public int superavit() {    //El metodo es O(1)
        return this.ganancias - this.perdidas;
    }

    public String toString(){    //Este método es para uso de los tests, no nos interesa su complejidad
        return "C("+Integer.toString(ident)+","+Integer.toString(ganancias)+",-"+Integer.toString(perdidas)+")";
    }
}
