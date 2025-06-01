package cl.unab.inf.ptec102.tarea2.backend;

public class PreguntaVF extends Pregunta {
    private String justificacion;
    private String justificacionUsuario;

    public PreguntaVF(int id, int tipo, String enunciado, int nivelBloom, int tiempoEstimado, String respuestaCorrecta, String justificacion) {
        super(id, tipo, enunciado, nivelBloom, tiempoEstimado);
        this.justificacion = justificacion;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    @Override
    public String getJustificacion() {
        return this.justificacion;
    }

    public void setJustificacionUsuario(String justificacionUsuario) {
        this.justificacionUsuario = justificacionUsuario;
    }



}