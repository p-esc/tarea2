package cl.unab.inf.ptec102.tarea2.frontend;

import cl.unab.inf.ptec102.tarea2.backend.Prueba;

import javax.swing.*;

public class Resumen extends JPanel {
    private JLabel recordar;
    private JLabel entender;
    private JLabel crear;
    private JLabel evaluar;
    private JLabel aplicar;
    private JLabel analizar;
    private JLabel respCorrectas;
    private JLabel vf;
    private JLabel om;
    private JPanel panel;
    private Prueba backend;

    public void populateFromData (Prueba backend) {
        this.backend = backend;
        this.recordar.setText("Recordar: "+String.valueOf(backend.porcentajePreguntasNivel(1))+"%");
        this.entender.setText("Entender: "+String.valueOf(backend.porcentajePreguntasNivel(2))+"%");
        this.aplicar.setText("Aplicar: "+String.valueOf(backend.porcentajePreguntasNivel(3))+"%");
        this.analizar.setText("Analizar: "+String.valueOf(backend.porcentajePreguntasNivel(4))+"%");
        this.evaluar.setText("Evaluar: "+String.valueOf(backend.porcentajePreguntasNivel(5))+"%");
        this.crear.setText("Crear: "+String.valueOf(backend.porcentajePreguntasNivel(6))+"%");

        this.vf.setText("Verdadero y falso: "+String.valueOf(backend.calcularPorcentajePorTipo(2))+"%");
        this.om.setText("Opción múltiple: "+String.valueOf(backend.calcularPorcentajePorTipo(1))+"%");

        this.respCorrectas.setText("Respuestas correctas: "+String.valueOf(backend.calcularRespuestasCorrectas()));
    }

    public JPanel getPanel(){
        return this.panel;
    }
}
