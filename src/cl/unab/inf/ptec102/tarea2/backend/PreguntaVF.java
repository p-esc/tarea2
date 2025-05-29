package cl.unab.inf.ptec102.tarea2.backend;

public class PreguntaVF extends Pregunta {
    private String respuestaCorrecta;
    private String justificacion;
    private String justificacionUsuario;

    public PreguntaVF(int id, int tipo, String enunciado, int nivelBloom, int tiempoEstimado, String respuestaCorrecta, String justificacion) {
        super(id, tipo, enunciado, nivelBloom, tiempoEstimado);
        this.respuestaCorrecta = respuestaCorrecta;
        this.justificacion = justificacion;
    }

    @Override
    public boolean esCorrecta() {
        if (respuestaUsuario == null) {return false;}
        return respuestaUsuario.equalsIgnoreCase(respuestaCorrecta);
    }

    @Override
    public String getTipo() {
        return "VERDADERO_FALSO";
    }

    public void setJustificacionUsuario(String justificacionUsuario) {
        this.justificacionUsuario = justificacionUsuario;
    }

}