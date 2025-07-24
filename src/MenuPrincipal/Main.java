package MenuPrincipal;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // --- CAMBIO: Ahora la aplicación inicia con la Presentacion ---
            Presentacion presentacion = new Presentacion();
            presentacion.setVisible(true);
        });
    }
}
