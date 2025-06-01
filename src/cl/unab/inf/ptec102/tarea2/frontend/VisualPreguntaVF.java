package cl.unab.inf.ptec102.tarea2.frontend;

import cl.unab.inf.ptec102.tarea2.backend.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VisualPreguntaVF {
    private JPanel panel;
    private JLabel titulo;
    private JLabel enunciado;
    private JRadioButton verdadero;
    private JRadioButton falso;
    private JTextField justificacion;
    private JLabel justificacionLabel;
    private JLabel justificacionEsperada;
    private JLabel justEspLabel;
    private JLabel icono;

    ButtonGroup opciones = new ButtonGroup();

    public VisualPreguntaVF() {
        opciones.add(verdadero);
        opciones.add(falso);
        justificacion.setVisible(false);
        justificacionLabel.setVisible(false);

        falso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (falso.isSelected()) {
                    justificacion.setVisible(true);
                    justificacionLabel.setVisible(true);
                }
            }
        });
        verdadero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verdadero.isSelected()) {
                    justificacion.setVisible(false);
                    justificacionLabel.setVisible(false);
                }
            }
        });
    }

    public JPanel getPanel(){
        return this.panel;
    }

    public void rellenarDatos(PreguntaVF preguntas) {
        this.titulo.setText("Pregunta "+preguntas.getId());
        this.enunciado.setText(preguntas.getEnunciado());
    }

    public String getRespuestaSeleccionada() {
        if (verdadero.isSelected()) {
            return "TRUE";
        }
        if (falso.isSelected()) {
            return "FALSE";
        }
        return null;
    }

    public String getJustificacion() {
        return justificacion.getText();
    }

    public void setRespuestaSeleccionada(String valor) {
        if (valor == null) return;
        if (valor.equals("TRUE")) {
            verdadero.setSelected(true);
        } else if (valor.equals("FALSE")) {
            falso.setSelected(true);
        }
    }

    public void mostrarRetroalimentacion (PreguntaVF pregunta) {
        String respuesta = pregunta.getRespuestaCorrecta();
        String justificacion = pregunta.getJustificacion();
        Color verde = new Color(67, 144, 40);
        if (pregunta.esCorrecta()) {
            icono.setText("✔");
            icono.setForeground(verde);
        }
        else {
            icono.setText("✘");
            icono.setForeground(new Color(192, 0, 0));
        }
        if (respuesta.equals("TRUE")) {
            this.verdadero.setBackground(verde);
        }
        else {
            this.falso.setBackground(verde);
        }
        this.icono.setVisible(true);
        this.verdadero.setEnabled(false);
        this.falso.setEnabled(false);
        this.justificacion.setEditable(false);
        this.justificacionEsperada.setText(justificacion);
        this.justificacionEsperada.setVisible(true);
        this.justEspLabel.setVisible(true);
    }
}

