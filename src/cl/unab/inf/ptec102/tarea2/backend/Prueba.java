package cl.unab.inf.ptec102.tarea2.backend;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Prueba {
    private ArrayList<Pregunta> preguntas;

    public Prueba() {
        this.preguntas = new ArrayList<>();
        this.cargarArchivo();
    }

    private void cargarArchivo(){
        try {
            BufferedReader archivo = new BufferedReader(new FileReader("ejemplo1.csv"));

            String linea = archivo.readLine();
            int i = 1;
            while(linea != null){
                String[] datos = linea.split(";");
                int tipo = Integer.parseInt(datos[0]);
                if (tipo == 2) {
                    Pregunta nuevo = new PreguntaVF(i, tipo, datos[1], Integer.parseInt(datos[2]), Integer.parseInt(datos[3]), datos[4], datos[5]);
                    this.preguntas.add(nuevo);
                }
                else {
                    String[] alternativas = datos[5].split("-");
                    Pregunta nuevo = new PreguntaOM(i, tipo, datos[1], Integer.parseInt(datos[2]), Integer.parseInt(datos[3]), datos[4], alternativas);
                    this.preguntas.add(nuevo);
                }
                linea = archivo.readLine();
                i++;
            }
            archivo.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getCantidadPreguntas() {
        return this.preguntas.size();
    }

    public float getTiempoTotal() {
        return this.preguntas.stream().mapToInt(Pregunta::getTiempoEstimado).sum();
    }

    public int calcularRespuestasCorrectas() {
        int correctas = 0;
        for (Pregunta pregunta : this.preguntas) {
            if (pregunta.esCorrecta()) {
                correctas++;
            }
        }
        return correctas;
    }

    public String porcentajePreguntasNivel(int nivel) {
        float correctas = 0;
        float n = 0;
        for (Pregunta pregunta : this.preguntas) {
            if (pregunta.getNivelBloom() == nivel){
                n++;
                if (pregunta.esCorrecta()) {
                    correctas++;
                }
            }
        }
        if (n != 0) {
            return Float.toString(correctas / n * 100)+"%";
        }
        return "--";
    }

    public String calcularPorcentajePorTipo(int tipo) {
        float correctas = 0;
        float n = 0;
        for (Pregunta p : this.preguntas) {
            if (p.getTipoPregunta() == tipo) {
                n++;
                if (p.esCorrecta()) {
                    correctas++;
                }
            }
        }
        if (n != 0) {
            return Float.toString(correctas / n * 100)+"%";
        }
        return "--";
    }

    public Pregunta getPreguntaPos(int posicion) {
        return this.preguntas.get(posicion);
    }

    public void guardarRespuestas(String respuesta, int id) {
        this.preguntas.get(id).setRespuestaUsuario(respuesta);
    }

    public void guardarJustificacion(String justificacion, int id) {
        this.preguntas.get(id).setJustificacionUsuario(justificacion);
    }

}

