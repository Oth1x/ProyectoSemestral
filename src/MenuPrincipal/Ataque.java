package MenuPrincipal;

public class Ataque {
    private String nombre;
    private int dmg;

    public Ataque(String nombre, int dmg) {
        this.nombre = nombre;
        this.dmg = dmg;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDanio() {
        return dmg;
    }
}
