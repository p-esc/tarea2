package cl.unab.inf.ptec102.tarea2.frontend;

import cl.unab.inf.ptec102.tarea2.backend.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Ventana extends JFrame {
    private JPanel panel1;
    private JLabel prueba;
    private JLabel tiempo;
    private JLabel cantPreguntas;
    private JButton iniciarPrueba;
    private JPanel botonera;
    private JPanel inicio;
    private JPanel panelPreguntas;
    private JButton atras;
    private JButton adelante;
    private JLabel tiempoLabel;
    private JLabel cantLabel;
    private JButton entregar;
    private JButton retroalimentacion;
    private JLabel error;
    private Prueba backend;
    private int tarjetaActual = 0;
    private ArrayList<Object> preguntas = new ArrayList<>();

    public Ventana(Prueba backend) {
        this.setContentPane(this.panel1);
        this.backend = backend;
        if (backend.getCantidadPreguntas() == 0) {
            this.error.setText("No se pudieron cargar las preguntas");
            this.error.setForeground(new Color(192, 0, 0));
            this.error.setVisible(true);
            this.iniciarPrueba.setVisible(false);
            return;
        }
        this.tiempo.setText(backend.getTiempoTotal()+" min");
        this.cantPreguntas.setText(String.valueOf(backend.getCantidadPreguntas()));

        Resumen resumen  = new Resumen();
        this.panelPreguntas.add(resumen.getPanel(), "resumenFinal");

        for (int i = 0; i < backend.getCantidadPreguntas(); i++) {
            Pregunta data = backend.getPreguntaPos(i);
            if (data.getTipoPregunta() == 1) {
                VisualPreguntaOM card = new VisualPreguntaOM();
                card.rellenarDatos((PreguntaOM) data);
                this.panelPreguntas.add(card.getPanel(), "card" + i);
                this.preguntas.add(card);
            }
            if (data.getTipoPregunta() == 2) {
                VisualPreguntaVF card = new VisualPreguntaVF();
                card.rellenarDatos((PreguntaVF) data);
                this.panelPreguntas.add(card.getPanel(), "card" + i);
                this.preguntas.add(card);
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
                sincronizarRespuestas();
                if (tarjetaActual < backend.getCantidadPreguntas() - 1) {
                    tarjetaActual++;
                    mostrarTarjeta(tarjetaActual);
                }
            }
        });
        atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sincronizarRespuestas();
                if (tarjetaActual > 0) {
                    tarjetaActual--;
                    mostrarTarjeta(tarjetaActual);
                }
            }
        });
        entregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sincronizarRespuestas();
                resumen.setRetroalimentacion(backend);
                entrega();
            }
        });
        retroalimentacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) panelPreguntas.getLayout()).show(panelPreguntas, "card"+tarjetaActual);
                mostrarRespuestas();
                adelante.setVisible(true);
                atras.setVisible(false);
                retroalimentacion.setVisible(false);
            }
        });
    }

    public void sincronizarRespuestas() {
        String respuesta = "";
        for (int i = 0; i < this.backend.getCantidadPreguntas(); i++) {
            Object p = this.preguntas.get(i);
            if (p instanceof VisualPreguntaOM) {
                respuesta = ((VisualPreguntaOM) p).getRespuestaSeleccionada();
                ((VisualPreguntaOM) p).setRespuestaSeleccionada(respuesta);

            }
            else if (p instanceof VisualPreguntaVF) {
                respuesta = ((VisualPreguntaVF) p).getRespuestaSeleccionada();
                ((VisualPreguntaVF) p).setRespuestaSeleccionada(respuesta);
                this.backend.guardarJustificacion(((VisualPreguntaVF) p).getJustificacion(), i);
            }
            this.backend.guardarRespuestas(respuesta, i);
        }
    }

    public void mostrarRespuestas() {
        for (int i = 0; i < this.backend.getCantidadPreguntas(); i++) {
            Object p = this.preguntas.get(i);
            Pregunta data = this.backend.getPreguntaPos(i);
            if (p instanceof VisualPreguntaOM) {
                ((VisualPreguntaOM) p).mostrarRetroalimentacion((PreguntaOM) data);
            }
            else if (p instanceof VisualPreguntaVF) {
                ((VisualPreguntaVF) p).mostrarRetroalimentacion((PreguntaVF) data);
            }
        }
    }

    public void actualizarBotonera() {
        atras.setVisible(tarjetaActual > 0);
        adelante.setVisible(tarjetaActual < backend.getCantidadPreguntas() - 1);
        entregar.setVisible(tarjetaActual == backend.getCantidadPreguntas() - 1);
    }

    public void mostrarTarjeta(int index) {
        CardLayout layout = (CardLayout) panelPreguntas.getLayout();
        tarjetaActual = index;
        layout.show(panelPreguntas, "card" + index);
        actualizarBotonera();
    }

    public void entrega() {
        CardLayout layout = (CardLayout) panelPreguntas.getLayout();
        layout.show(panelPreguntas, "resumenFinal");
        atras.setVisible(false);
        adelante.setVisible(false);
        entregar.setVisible(false);
        retroalimentacion.setVisible(true);
        tarjetaActual = 0;
        entregar.setText("Ver resumen");
    }

}
