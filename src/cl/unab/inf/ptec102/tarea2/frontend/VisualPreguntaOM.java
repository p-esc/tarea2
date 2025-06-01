package cl.unab.inf.ptec102.tarea2.frontend;
import cl.unab.inf.ptec102.tarea2.backend.*;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class VisualPreguntaOM extends JPanel {
    private JPanel panel;
    private JLabel titulo;
    private JRadioButton a;
    private JRadioButton b;
    private JRadioButton c;
    private JRadioButton d;
    private JLabel enunciado;
    private JLabel icono;
    private List<JRadioButton> botones;

    ButtonGroup alternativas = new ButtonGroup();
    public VisualPreguntaOM() {
        this.botones = Arrays.asList(a,b,c,d);
        for (JRadioButton boton : botones) {
            alternativas.add(boton);
        }
    }

    public JPanel getPanel(){
        return this.panel;
    }

    public void rellenarDatos(PreguntaOM preguntas) {
        this.titulo.setText("Pregunta "+preguntas.getId());
        this.enunciado.setText(preguntas.getEnunciado());
        int i = 0;
        for (JRadioButton boton : botones) {
            boton.setText(preguntas.getOpciones()[i]);
            i++;
        }
    }

    public void setRespuestaSeleccionada(String opcion) {
        if (opcion == null) return;
        for (JRadioButton boton : botones) {
            if (opcion.equalsIgnoreCase(boton.getName())) {
                boton.setSelected(true);
            }
        }
    }

    public String getRespuestaSeleccionada() {
        for (JRadioButton boton : botones) {
            if (boton.isSelected()) return boton.getName();
        }
        return null;
    }

    public void mostrarRetroalimentacion (PreguntaOM pregunta) {
        String respuesta = pregunta.getRespuestaCorrecta();
        Color verde = new Color(67, 144, 40);
        String[] letras = {"a", "b", "c", "d"};
        if (pregunta.esCorrecta()) {
            this.icono.setText("✔");
            this.icono.setForeground(verde);
        }
        else {
            this.icono.setText("✘");
            this.icono.setForeground(new Color(192, 0, 0));
        }
        for (int i = 0; i < botones.size(); i++) {
            JRadioButton boton = botones.get(i);
            boton.setEnabled(false);
            if (respuesta.equalsIgnoreCase(letras[i])) {
                boton.setBackground(verde);
            }
        }
        this.icono.setVisible(true);
    }
}
