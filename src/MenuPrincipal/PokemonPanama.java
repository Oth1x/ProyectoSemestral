package MenuPrincipal;

import java.util.List;
import java.util.ArrayList;

public class PokemonPanama {
    private String nombre;
    private int vida;
    private List<Ataque> ataques;
    private String rutaImagen;
    private String rutaImagenAtaque;
    private String rutaImagenDmgRecibido; // NUEVO: Campo para la imagen de daño

    // CONSTRUCTOR ACTUALIZADO para aceptar la nueva ruta
    public PokemonPanama(String nombre, int vida, List<Ataque> ataques, String rutaImagen, String rutaImagenAtaque, String rutaImagenDmgRecibido) {
        this.nombre = nombre;
        this.vida = vida;
        this.ataques = ataques;
        this.rutaImagen = rutaImagen;
        this.rutaImagenAtaque = rutaImagenAtaque;
        this.rutaImagenDmgRecibido = rutaImagenDmgRecibido;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public List<Ataque> getAtaques() {
        return ataques;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public String getRutaImagenAtaque() {
        return rutaImagenAtaque;
    }

    // NUEVO: Método para obtener la imagen de daño
    public String getRutaImagenDmgRecibido() {
        return rutaImagenDmgRecibido;
    }

    // Los métodos de creación ahora incluyen la ruta de la imagen de daño
    public static PokemonPanama crearNeque() {
        List<Ataque> ataques = new ArrayList<>();
        ataques.add(new Ataque("Mordisco", 20));
        ataques.add(new Ataque("Embestida", 25));
        ataques.add(new Ataque("Arañazo", 18));
        return new PokemonPanama("Agutrix", 100, ataques, "imagen/neque.png", "AtaqueImagen/neque.png", "DmgRecibido/neque.png");
    }

    public static PokemonPanama crearRanaDorada() {
        List<Ataque> ataques = new ArrayList<>();
        ataques.add(new Ataque("Lengüetazo", 22));
        ataques.add(new Ataque("Salto Tóxico", 28));
        ataques.add(new Ataque("Burbuja", 19));
        return new PokemonPanama("Doraflare", 90, ataques, "imagen/RanaDorada.png", "AtaqueImagen/RanaDorada.png", "DmgRecibido/RanaDorada.png");
    }

    public static PokemonPanama crearAguilaArpia() {
        List<Ataque> ataques = new ArrayList<>();
        ataques.add(new Ataque("Picotazo", 25));
        ataques.add(new Ataque("Garra Afilada", 30));
        ataques.add(new Ataque("Vuelo Rápido", 15));
        return new PokemonPanama("Harpeagle", 110, ataques, "imagen/aguilaArpia.png", "AtaqueImagen/aguilaArpia.png", "DmgRecibido/aguilaArpia.png");
    }

    public static PokemonPanama crearIguana() {
        List<Ataque> ataques = new ArrayList<>();
        ataques.add(new Ataque("Golpe Cola", 24));
        ataques.add(new Ataque("Cabezazo", 26));
        ataques.add(new Ataque("Placaje", 20));
        return new PokemonPanama("Iguanix", 120, ataques, "imagen/iguana.png", "AtaqueImagen/iguana.png", "DmgRecibido/iguana.png");
    }
}
