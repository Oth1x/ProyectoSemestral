package MenuPrincipal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Combate extends JFrame {
    private JPanel panelCombate;
    private JButton ataque1Button;
    private JButton ataque2Button;
    private JButton ataque3Button;
    private JButton rendirseButton;
    private JLabel lbPokemon1;
    private JLabel lbPokemon2;
    private JLabel lbPokemonImg1;
    private JLabel lbPokemonImg2;
    private JProgressBar pgbVida1;
    private JProgressBar pgbVida2;
    private JTextArea textCombate;
    private JScrollPane scrollPaneLog;

    private PokemonPanama pokemonJugador;
    private PokemonPanama pokemonOponente;

    public Combate(PokemonPanama pokemonJugador, PokemonPanama pokemonOponente) {
        this.pokemonJugador = pokemonJugador;
        this.pokemonOponente = pokemonOponente;

        setTitle("¡Combate Pokémon!");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(panelCombate);

        if (textCombate == null || lbPokemon1 == null || lbPokemon2 == null || lbPokemonImg1 == null || lbPokemonImg2 == null) {
            throw new IllegalStateException("Asegúrate de que todos los componentes (textCombate, lbPokemon1, lbPokemon2, lbPokemonImg1, lbPokemonImg2) están vinculados en el UI Designer.");
        }
        textCombate.setEditable(false);
        textCombate.setLineWrap(true);
        textCombate.setWrapStyleWord(true);
        pgbVida1.setStringPainted(true);
        pgbVida2.setStringPainted(true);
        inicializarCombate();
    }

    private void agregarAlLog(String mensaje) {
        textCombate.append(mensaje + "\n");
        textCombate.setCaretPosition(textCombate.getDocument().getLength());
    }

    private void inicializarCombate() {
        lbPokemon1.setText(pokemonJugador.getNombre());
        lbPokemon2.setText(pokemonOponente.getNombre());
        cargarImagen(lbPokemonImg1, pokemonJugador.getRutaImagen());
        cargarImagen(lbPokemonImg2, pokemonOponente.getRutaImagen());
        actualizarBarraDeVida(pgbVida1, pokemonJugador.getVida(), pokemonJugador.getVida());
        actualizarBarraDeVida(pgbVida2, pokemonOponente.getVida(), pokemonOponente.getVida());
        if (pokemonJugador.getAtaques().size() > 0) ataque1Button.setText(pokemonJugador.getAtaques().get(0).getNombre());
        if (pokemonJugador.getAtaques().size() > 1) ataque2Button.setText(pokemonJugador.getAtaques().get(1).getNombre());
        if (pokemonJugador.getAtaques().size() > 2) ataque3Button.setText(pokemonJugador.getAtaques().get(2).getNombre());
        agregarAlLog("¡Un " + pokemonOponente.getNombre() + " salvaje apareció!");
        agregarAlLog("¡Adelante, " + pokemonJugador.getNombre() + "!");
        setupButtonListeners();
    }

    private void cargarImagen(JLabel label, String path) {
        if (label == null) { return; }
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            ImageIcon originalIcon = new ImageIcon(imgURL);
            Image originalImage = originalIcon.getImage();
            Image imagenRedimensionada = originalImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(imagenRedimensionada));
            label.setText("");
        } else {
            System.err.println("No se pudo encontrar el archivo de imagen: " + path);
            label.setText("No img");
        }
    }

    private void actualizarBarraDeVida(JProgressBar barra, int vidaActual, int vidaMaxima) {
        barra.setMaximum(vidaMaxima);
        barra.setValue(vidaActual);
        barra.setString(vidaActual + " / " + vidaMaxima);
    }

    private void setupButtonListeners() {
        for (ActionListener al : ataque1Button.getActionListeners()) ataque1Button.removeActionListener(al);
        for (ActionListener al : ataque2Button.getActionListeners()) ataque2Button.removeActionListener(al);
        for (ActionListener al : ataque3Button.getActionListeners()) ataque3Button.removeActionListener(al);
        for (ActionListener al : rendirseButton.getActionListeners()) rendirseButton.removeActionListener(al);

        if (pokemonJugador.getAtaques().size() > 0) ataque1Button.addActionListener(e -> ejecutarAtaque(0));
        if (pokemonJugador.getAtaques().size() > 1) ataque2Button.addActionListener(e -> ejecutarAtaque(1));
        if (pokemonJugador.getAtaques().size() > 2) ataque3Button.addActionListener(e -> ejecutarAtaque(2));

        rendirseButton.addActionListener(e -> {
            agregarAlLog(pokemonJugador.getNombre() + " se ha rendido.");
            JOptionPane.showMessageDialog(this, "Te has rendido. ¡Has perdido el combate!", "Derrota", JOptionPane.INFORMATION_MESSAGE);
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
            dispose();
        });
    }

    private void ejecutarAtaque(int indiceAtaque) {
        deshabilitarBotonesAtaque();
        Ataque ataque = pokemonJugador.getAtaques().get(indiceAtaque);
        int dmg = ataque.getDanio();

        cargarImagen(lbPokemonImg1, pokemonJugador.getRutaImagenAtaque());
        cargarImagen(lbPokemonImg2, pokemonOponente.getRutaImagenDmgRecibido());

        Timer timerAnimacion = new Timer(500, e -> {
            cargarImagen(lbPokemonImg1, pokemonJugador.getRutaImagen());
            cargarImagen(lbPokemonImg2, pokemonOponente.getRutaImagen());

            int vidaActualOponente = pgbVida2.getValue();
            int nuevaVidaOponente = Math.max(0, vidaActualOponente - dmg);
            actualizarBarraDeVida(pgbVida2, nuevaVidaOponente, pokemonOponente.getVida());
            agregarAlLog(pokemonJugador.getNombre() + " usa " + ataque.getNombre() + " e inflige " + dmg + " de daño!");

            if (verificarFinDeJuego()) return;
            turnoOponente();
        });
        timerAnimacion.setRepeats(false);
        timerAnimacion.start();
    }

    private void turnoOponente() {
        agregarAlLog("Turno del oponente...");
        Timer timerEspera = new Timer(1000, e -> {
            Random random = new Random();
            int indiceAtaqueOponente = random.nextInt(pokemonOponente.getAtaques().size());
            Ataque ataqueOponente = pokemonOponente.getAtaques().get(indiceAtaqueOponente);
            int dmgOponente = ataqueOponente.getDanio();

            cargarImagen(lbPokemonImg2, pokemonOponente.getRutaImagenAtaque());
            cargarImagen(lbPokemonImg1, pokemonJugador.getRutaImagenDmgRecibido());

            Timer timerAnimacionOponente = new Timer(500, e2 -> {
                cargarImagen(lbPokemonImg2, pokemonOponente.getRutaImagen());
                cargarImagen(lbPokemonImg1, pokemonJugador.getRutaImagen());

                int vidaActualJugador = pgbVida1.getValue();
                int nuevaVidaJugador = Math.max(0, vidaActualJugador - dmgOponente);
                actualizarBarraDeVida(pgbVida1, nuevaVidaJugador, pokemonJugador.getVida());
                agregarAlLog(pokemonOponente.getNombre() + " usa " + ataqueOponente.getNombre() + " e inflige " + dmgOponente + " de daño!");

                if (verificarFinDeJuego()) return;
                habilitarBotonesAtaque();
            });
            timerAnimacionOponente.setRepeats(false);
            timerAnimacionOponente.start();
        });
        timerEspera.setRepeats(false);
        timerEspera.start();
    }

    private boolean verificarFinDeJuego() {
        if (pgbVida2.getValue() <= 0) {
            agregarAlLog("¡" + pokemonOponente.getNombre() + " ha sido derrotado!");
            JOptionPane.showMessageDialog(this, "¡Felicidades! ¡Has ganado el combate!", "Victoria", JOptionPane.INFORMATION_MESSAGE);
            deshabilitarBotonesAtaque();

            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
            dispose();

            return true;
        }
        if (pgbVida1.getValue() <= 0) {
            agregarAlLog("¡" + pokemonJugador.getNombre() + " no puede continuar!");
            JOptionPane.showMessageDialog(this, "¡Has sido derrotado! " + pokemonOponente.getNombre() + " gana.", "Derrota", JOptionPane.ERROR_MESSAGE);
            deshabilitarBotonesAtaque();

            // --- CÓDIGO AÑADIDO ---
            // Vuelve al menú principal después de perder
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
            dispose(); // Cierra la ventana de combate

            return true;
        }
        return false;
    }

    private void deshabilitarBotonesAtaque() {
        ataque1Button.setEnabled(false);
        ataque2Button.setEnabled(false);
        ataque3Button.setEnabled(false);
    }

    private void habilitarBotonesAtaque() {
        ataque1Button.setEnabled(true);
        ataque2Button.setEnabled(true);
        ataque3Button.setEnabled(true);
    }
}