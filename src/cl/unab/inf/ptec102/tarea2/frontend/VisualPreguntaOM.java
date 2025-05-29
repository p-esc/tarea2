package cl.unab.inf.ptec102.tarea2.frontend;
import cl.unab.inf.ptec102.tarea2.backend.*;
import javax.swing.*;
import javax.swing.text.html.MinimalHTMLWriter;
import java.util.Objects;

public class VisualPreguntaOM extends JPanel {
    private JPanel panel;
    private JLabel titulo;
    private JRadioButton a;
    private JRadioButton b;
    private JRadioButton c;
    private JRadioButton d;
    private JLabel enunciado;

    ButtonGroup alternativas = new ButtonGroup();
    public VisualPreguntaOM() {
        this.alternativas.add(a);
        this.alternativas.add(b);
        this.alternativas.add(c);
        this.alternativas.add(d);
    }

    public JPanel getPanel(){
        return this.panel;
    }

    public void populateFromModel(PreguntaOM preguntas) {
        this.titulo.setText("Pregunta "+preguntas.getId());
        this.enunciado.setText(preguntas.getEnunciado());
        this.a.setText(preguntas.getOpciones()[0]);
        this.b.setText(preguntas.getOpciones()[1]);
        this.c.setText(preguntas.getOpciones()[2]);
        this.d.setText(preguntas.getOpciones()[3]);
    }

    public void setRespuestaSeleccionada(String opcion) {
        if (opcion == null) return;
        switch (opcion) {
            case "a": a.setSelected(true); break;
            case "b": b.setSelected(true); break;
            case "c": c.setSelected(true); break;
            case "d": d.setSelected(true); break;
            default: break;
        }
    }

    public String getRespuestaSeleccionada() {
        if (a.isSelected()) { return "a"; }
        if (b.isSelected()) { return "b"; }
        if (c.isSelected()) { return "c"; }
        if (d.isSelected()) { return "d"; }
        return null;
    }

}
