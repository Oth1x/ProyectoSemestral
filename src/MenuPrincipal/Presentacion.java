package MenuPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Presentacion extends JFrame {
    private JPanel panelPresentacion;
    private JLabel lbLogoUTP;
    private JLabel lbLogoFacultad;
    private JButton continuarButton;
    private JTextArea infoTextArea; // NUEVO: Componente para la información

    public Presentacion() {
        setTitle("Presentación del Proyecto");
        setSize(800, 600); // Puedes ajustar el tamaño
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(panelPresentacion);

        // Cargar los logos en sus respectivos JLabels
        cargarLogos();

        // --- CÓDIGO AÑADIDO: Configurar el JTextArea ---
        if (infoTextArea != null) {
            // Configuración para que el texto se vea bien y no se pueda editar
            infoTextArea.setEditable(false);
            infoTextArea.setLineWrap(true);
            infoTextArea.setWrapStyleWord(true);
            infoTextArea.setOpaque(false); // Opcional: para que se vea el fondo del panel

            // Texto de la presentación
            String textoPresentacion = "Universidad Tecnológica de Panamá\n" +
                    "Facultad de Ingeniería de Sistemas Computacionales\n\n" +
                    "Licenciatura en Desarrollo de Software\n\n" +
                    "Materia: Programacion de Software\n\n" +
                    "Proyecto Semestral:\n" +
                    "PixelMon: PanamaQuest\n\n" +
                    "Estudiante:\n" +
                    "Astrid Madrid 8-992-1420\n" +
                    "Ryan Velasquez 8-1022-291\n" +
                    "Jonathan Velasquez 8-1022-292\n" +
                    "Alex Wu 3-757-290\n" +
                    "Profesor:\n" +
                    "Yanguez Rodrigo\n\n" +
                    "Fecha: 25/07/2025";

            infoTextArea.setText(textoPresentacion);
        }

        // Acción para el botón de continuar
        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crea la ventana del menú principal
                MenuPrincipal menu = new MenuPrincipal();
                menu.setVisible(true);

                // Cierra la ventana actual de presentación
                dispose();
            }
        });
    }

    /**
     * Carga las imágenes de los logos en los JLabels correspondientes.
     */
    private void cargarLogos() {
        // Llama a un método auxiliar para cargar cada imagen
        cargarImagen(lbLogoUTP, "LogoUTP/UTP.png", 200, 200);
        cargarImagen(lbLogoFacultad, "LogoUTP/Facultad.png", 200, 200);
    }

    /**
     * Método auxiliar para cargar y redimensionar una imagen.
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
            Image imagenRedimensionada = originalImage.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(imagenRedimensionada));
            label.setText("");
        } else {
            System.err.println("No se pudo encontrar el archivo de imagen: " + path);
            label.setText("No img");
        }
    }
}