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

    public void setRetroalimentacion(Prueba backend) {
        this.recordar.setText("Recordar: "+backend.porcentajePreguntasNivel(1));
        this.entender.setText("Entender: "+backend.porcentajePreguntasNivel(2));
        this.aplicar.setText("Aplicar: "+backend.porcentajePreguntasNivel(3));
        this.analizar.setText("Analizar: "+backend.porcentajePreguntasNivel(4));
        this.evaluar.setText("Evaluar: "+backend.porcentajePreguntasNivel(5));
        this.crear.setText("Crear: "+backend.porcentajePreguntasNivel(6));

        this.vf.setText("Verdadero y falso: "+backend.calcularPorcentajePorTipo(2));
        this.om.setText("Opción múltiple: "+backend.calcularPorcentajePorTipo(1));

        this.respCorrectas.setText("Respuestas correctas: "+backend.calcularRespuestasCorrectas()+"/"+backend.getCantidadPreguntas());
    }

    public JPanel getPanel(){
        return this.panel;
    }
}
