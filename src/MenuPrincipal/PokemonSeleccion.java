package MenuPrincipal;

import javax.swing.*;
import java.awt.*; // Se importa la clase Image
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokemonSeleccion extends JFrame {
    private JPanel panelElegirPokemon;
    private JButton nequeButton;
    private JButton ranaDoradaButton;
    private JButton aguilaArpiaButton;
    private JButton iguanaButton;
    private JLabel lbSeleccion;
    private JLabel lbNeque;
    private JLabel lbRanaDorada;
    private JLabel lbAguilaArpia;
    private JLabel lbIguana;

    public PokemonSeleccion() {
        setTitle("Elige tu Pokémon");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(panelElegirPokemon);

        if (lbSeleccion != null) {
            lbSeleccion.setText("Elija su Pokémon");
        }

        cargarImagenes();

        nequeButton.addActionListener(e -> iniciarCombate(PokemonPanama.crearNeque()));
        ranaDoradaButton.addActionListener(e -> iniciarCombate(PokemonPanama.crearRanaDorada()));
        aguilaArpiaButton.addActionListener(e -> iniciarCombate(PokemonPanama.crearAguilaArpia()));
        iguanaButton.addActionListener(e -> iniciarCombate(PokemonPanama.crearIguana()));
    }

    /**
     * Este método ahora crea un JPanel personalizado directamente aquí,
     * sin necesidad de una clase externa.
     */
    private void cargarImagenes() {
        cargarImagen(lbNeque, "imagen/neque.png");
        cargarImagen(lbRanaDorada, "imagen/ranaDorada.png");
        cargarImagen(lbAguilaArpia, "imagen/aguilaArpia.png");
        cargarImagen(lbIguana, "imagen/iguana.png");
    }

    private void cargarImagen(JLabel label, String path) {
        if (label == null) {
            System.err.println("El JLabel para la ruta " + path + " es nulo.");
            return;
        }
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            ImageIcon originalIcon = new ImageIcon(imgURL);
            Image originalImage = originalIcon.getImage();
            Image imagenRedimensionada = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(imagenRedimensionada));
            label.setText("");
        } else {
            System.err.println("No se pudo encontrar el archivo de imagen: " + path);
            label.setText("No img");
        }
    }

    private void iniciarCombate(PokemonPanama pokemonJugador) {
        System.out.println("Has elegido a: " + pokemonJugador.getNombre());
        List<PokemonPanama> posiblesOponentes = new ArrayList<>();
        posiblesOponentes.add(PokemonPanama.crearNeque());
        posiblesOponentes.add(PokemonPanama.crearRanaDorada());
        posiblesOponentes.add(PokemonPanama.crearAguilaArpia());
        posiblesOponentes.add(PokemonPanama.crearIguana());
        Random random = new Random();
        int indiceAleatorio = random.nextInt(posiblesOponentes.size());
        PokemonPanama oponente = posiblesOponentes.get(indiceAleatorio);
        System.out.println("Tu oponente será: " + oponente.getNombre());
        Combate combate = new Combate(pokemonJugador, oponente);
        combate.setVisible(true);
        dispose();
    }
}