package cl.unab.inf.ptec102.tarea2.frontend;

import cl.unab.inf.ptec102.tarea2.backend.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Ventana extends JFrame {
    private JPanel panel1;
    private JLabel Prueba;
    private JLabel tiempo;
    private JLabel cantPreguntas;
    private JButton iniciarPrueba;
    private JPanel botonera;
    private JPanel cards;
    private JPanel panelPreguntas;
    private JButton atras;
    private JButton adelante;
    private JLabel tiempoLabel;
    private JLabel cantLabel;
    private JButton entregar;
    private JButton guardar;
    private Prueba backend;
    private int tarjetaActual = 0;
    private List<Object> vistasPreguntas = new ArrayList<>();

    public Ventana(Prueba backend) {
        this.setContentPane(this.panel1);
        this.backend = backend;
        this.tiempo.setText(String.valueOf(backend.getTiempoTotal())+" min");
        this.cantPreguntas.setText(String.valueOf(backend.getCantidadPreguntas()));

        Resumen resumen  = new Resumen();
        panelPreguntas.add(resumen.getPanel(), "resumenFinal");

        for (int i = 0; i < backend.getCantidadPreguntas(); i++) {
            Pregunta data = backend.getPreguntaPos(i);
            if (data.getTipoPregunta() == 1) {
                VisualPreguntaOM card = new VisualPreguntaOM();
                card.populateFromModel((PreguntaOM) data);
                panelPreguntas.add(card.getPanel(), "card" + i);
                vistasPreguntas.add(card);
            }
            if (data.getTipoPregunta() == 2) {
                VisualPreguntaVF card = new VisualPreguntaVF();
                card.populateFromModel((PreguntaVF) data);
                panelPreguntas.add(card.getPanel(), "card" + i);
                vistasPreguntas.add(card);
            }
        }

        iniciarPrueba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = (CardLayout) panelPreguntas.getLayout();
                iniciarPrueba.setVisible(false);
                adelante.setVisible(true);
                layout.show(panelPreguntas, "card0");
            }
        });
        adelante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seed_preguntas(tarjetaActual);
                CardLayout layout = (CardLayout) panelPreguntas.getLayout();
                if (tarjetaActual == backend.getCantidadPreguntas()-2) {
                    adelante.setVisible(false);
                    entregar.setVisible(true);
                    layout.show(panelPreguntas, "resumenFinal");
                }
                if (backend.getCantidadPreguntas()-1 > tarjetaActual) {
                    tarjetaActual++;
                    layout.show(panelPreguntas, "card" + tarjetaActual);
                }
                if (tarjetaActual == 0) {atras.setVisible(false);}
                else { atras.setVisible(true);                }
            }
        });
        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seed_preguntas(tarjetaActual);
                if (tarjetaActual > 1 && tarjetaActual < backend.getCantidadPreguntas()) {
                    atras.setVisible(true);
                    adelante.setVisible(true);
                    entregar.setVisible(false);
                    CardLayout layout = (CardLayout) panelPreguntas.getLayout();
                    tarjetaActual--;
                    layout.show(panelPreguntas, "card"+tarjetaActual);
                }
                else if (tarjetaActual == 1) {
                    CardLayout layout = (CardLayout) panelPreguntas.getLayout();
                    tarjetaActual--;
                    layout.show(panelPreguntas, "card"+tarjetaActual);
                    atras.setVisible(false);
                }
            }
        });
        entregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seed_preguntas(tarjetaActual);
                CardLayout layout = (CardLayout) panelPreguntas.getLayout();
                resumen.populateFromData(backend);
                layout.show(panelPreguntas, "resumenFinal");
                atras.setVisible(false);
                adelante.setVisible(false);
            }
        });
    }

    public void seed_preguntas(int tarjetaActual) {
            Object vista = vistasPreguntas.get(tarjetaActual);
            String respuesta = "";
            if (vista instanceof VisualPreguntaOM) {
                respuesta = ((VisualPreguntaOM) vista).getRespuestaSeleccionada();
                ((VisualPreguntaOM) vista).setRespuestaSeleccionada(respuesta);
            }
            else if (vista instanceof VisualPreguntaVF){
                respuesta = ((VisualPreguntaVF) vista).getRespuestaSeleccionada();
                ((VisualPreguntaVF) vista).setRespuestaSeleccionada(respuesta);
                backend.guardarJustificacion(((VisualPreguntaVF) vista).getJustificacion(), tarjetaActual);
            }
            backend.guardarRespuestas(respuesta, tarjetaActual);
    }
}
