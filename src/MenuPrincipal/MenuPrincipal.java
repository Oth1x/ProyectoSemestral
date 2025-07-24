package MenuPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    private JPanel panelMenuPrincipal;
    private JButton IniciarButton;
    private JButton SalirButton;
    private JLabel lbTitulo; // Componente para la imagen del título

    public MenuPrincipal() {
        setTitle("Pokemon Panameño");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(panelMenuPrincipal);

        // Cargar la imagen del título
        cargarTitulo();

        // Acción para el botón "INICIAR"
        IniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PokemonSeleccion seleccion = new PokemonSeleccion();
                seleccion.setVisible(true);
                dispose();
            }
        });

        // Acción para el botón "SALIR"
        SalirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    /**
     * Carga la imagen del título en el JLabel correspondiente.
     */
    private void cargarTitulo() {
        // Llama a un método auxiliar para cargar la imagen.
        // Puedes ajustar el ancho y alto según necesites.
        cargarImagen(lbTitulo, "imagen/titulo.png", 500, 250);
    }

    /**
     * Método auxiliar estándar para cargar y redimensionar una imagen.
     * @param label El JLabel donde se colocará la imagen.
     * @param path La ruta del archivo de imagen dentro de la carpeta de recursos.
     * @param ancho El ancho deseado para la imagen.
     * @param alto El alto deseado para la imagen.
     */
    private void cargarImagen(JLabel label, String path, int ancho, int alto) {
        if (label == null) {
            System.err.println("El JLabel para la ruta " + path + " es nulo. Revisa el 'field name' en el UI Designer.");
            return;
        }

        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            ImageIcon originalIcon = new ImageIcon(imgURL);
            Image originalImage = originalIcon.getImage();

            // Redimensionar la imagen
            Image imagenRedimensionada = originalImage.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);

            // Asignar la imagen redimensionada al JLabel
            label.setIcon(new ImageIcon(imagenRedimensionada));
            label.setText(""); // Limpia cualquier texto de marcador de posición
        } else {
            System.err.println("No se pudo encontrar el archivo de imagen: " + path);
            label.setText("No img");
        }
    }
}
