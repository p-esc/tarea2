import cl.unab.inf.ptec102.tarea2.backend.*;
import cl.unab.inf.ptec102.tarea2.frontend.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Prueba backend = new Prueba();
        Ventana frontend = new Ventana(backend);

        frontend.setSize(480,300);
        frontend.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frontend.setLocationRelativeTo(null);
        frontend.setVisible(true);
    }
}