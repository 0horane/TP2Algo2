package aed;

public class Ciudad {
    public int ident;
    private int ganancias;
    private int perdidas;

    public Ciudad(int i) {
        this.ident = i;
        this.ganancias = 0;
        this.perdidas = 0;
    }

    public void agregarGanancia(int n) {
        this.ganancias = this.ganancias + 1;
    }

    public void agregarPerdidas(int n) {
        this.perdidas = this.perdidas + 1;
    }

    public int superavit() {
        return this.ganancias - this.perdidas;
    }
}
