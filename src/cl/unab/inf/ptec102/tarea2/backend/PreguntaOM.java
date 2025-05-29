package cl.unab.inf.ptec102.tarea2.backend;

public class PreguntaOM extends Pregunta {
    private String[] opciones;
    private String respuestaCorrecta;

    public PreguntaOM(int id, int tipo, String enunciado, int nivelBloom, int tiempoEstimado, String respuestaCorrecta,  String[] opciones) {
        super(id, tipo, enunciado, nivelBloom, tiempoEstimado);
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    @Override
    public boolean esCorrecta() {
        if (respuestaUsuario == null) {return false;}
        return respuestaCorrecta.equalsIgnoreCase(respuestaUsuario);
    }

    @Override
    public String getTipo() {
        return "OPCION_MULTIPLE";
    }

    @Override
    public void setJustificacionUsuario(String justificacion) {
    }

    public String[] getOpciones() { return opciones; }
}

