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
            while(linea != null){
                String[] datos = linea.split(";");
                int tipo = Integer.parseInt(datos[1]);
                if (tipo == 2) {
                    Pregunta nuevo = new PreguntaVF(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), datos[2], Integer.parseInt(datos[3]), Integer.parseInt(datos[4]), datos[5], datos[6]);
                    this.preguntas.add(nuevo);
                }
                else {
                    String[] alternativas = datos[6].split("-");
                    Pregunta nuevo = new PreguntaOM(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]), datos[2], Integer.parseInt(datos[3]), Integer.parseInt(datos[4]), datos[5], alternativas);
                    this.preguntas.add(nuevo);
                }
                linea = archivo.readLine();
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

    public float porcentajePreguntasNivel(int nivel) {
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
        return correctas/n*100;
    }

    public float calcularPorcentajePorTipo(int tipo) {
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
        return correctas / n * 100;
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

