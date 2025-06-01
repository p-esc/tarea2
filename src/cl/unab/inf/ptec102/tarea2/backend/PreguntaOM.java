package cl.unab.inf.ptec102.tarea2.backend;

public class PreguntaOM extends Pregunta {
    private String[] opciones;

    public PreguntaOM(int id, int tipo, String enunciado, int nivelBloom, int tiempoEstimado, String respuestaCorrecta,  String[] opciones) {
        super(id, tipo, enunciado, nivelBloom, tiempoEstimado);
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    @Override
    public String getJustificacion() {
        return "";
    }

    @Override
    public void setJustificacionUsuario(String justificacion) {}

    public String[] getOpciones() { return opciones; }
}

