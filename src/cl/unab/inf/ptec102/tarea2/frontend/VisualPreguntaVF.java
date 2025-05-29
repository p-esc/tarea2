package cl.unab.inf.ptec102.tarea2.frontend;

import cl.unab.inf.ptec102.tarea2.backend.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualPreguntaVF {
    private JPanel panel;
    private JLabel titulo;
    private JLabel enunciado;
    private JRadioButton verdadero;
    private JRadioButton falso;
    private JTextField justificacion;
    private JLabel justificacionLabel;
    private Prueba backend;

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

    public void populateFromModel(PreguntaVF preguntas) {
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
}

