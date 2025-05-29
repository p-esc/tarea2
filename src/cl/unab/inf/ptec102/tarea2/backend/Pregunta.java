package cl.unab.inf.ptec102.tarea2.backend;

public abstract class Pregunta {
    protected int id;
    protected int tipo;
    protected String enunciado;
    protected int tiempoEstimado;
    protected int nivelBloom;
    protected String respuestaUsuario;

    public Pregunta(int id, int tipo, String enunciado, int nivelBloom, int tiempoEstimado) {
        this.id = id;
        this.tipo = tipo;
        this.enunciado = enunciado;
        this.nivelBloom = nivelBloom;
        this.tiempoEstimado = tiempoEstimado;
    }

    public abstract boolean esCorrecta();
    public abstract String getTipo();

    public int getTipoPregunta() {
        return this.tipo;
    }
    public void setRespuestaUsuario(String respuestaUsuario) {this.respuestaUsuario = respuestaUsuario;}

    public String getRespuestaUsuario() {
        return this.respuestaUsuario;
    }
    public int getNivelBloom() { return this.nivelBloom; }
    public int getTiempoEstimado() { return this.tiempoEstimado; }
    public String getEnunciado() { return this.enunciado; }
    public int getId() { return this.id; }

    public abstract void setJustificacionUsuario(String justificacion);
}