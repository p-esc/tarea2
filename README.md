# Sistema de Evaluación por Taxonomía de Bloom

## Descripción

Este proyecto tiene como objetivo administrar y ejecutar evaluaciones académicas compuestas por ítems que están clasificados según los niveles de la Taxonomía de Bloom a través del uso de Java Swing. La aplicación está pensada para facilitar la elaboración, ejecución y análisis de estas pruebas para su implementación en todas las asignaturas, aliviar el trabajo de los docentes y dar seguimiento al proceso de aprendizaje de los estudiantes.

El programa permite cargar un archivo con los ítems de la prueba, visualizar las preguntas una por una, registrar las respuestas del usuario y entregar un resumen con el porcentaje de logro segmentado por tipo de pregunta (implementando opción múltiple y verdadero o falso) y nivel taxonómico.

## Funcionalidades Principales

- ### Carga de ítems desde un archivo externo (ejemplo1.csv)

Localizado en la raíz del programa, el archivo sigue una estructura en específico relacionada directamente con las funcionalidades de las clases definidas dentro del paquete backend. La estructura del archivo contiene los siguientes campos separados por punto y comas: 

  - Tipo de pregunta (1 = opción múltiple, 2 = verdadero o falso)
  - Enunciado
  - Tiempo estimado (en minutos)
  - Nivel de Bloom (usando un id del 1-6 para cada uno)
  - Respuesta correcta ("A", "D" "TRUE", "FALSE", etc.)
  - 4 alternativas separadas por guión o la justificación esperada.

Ejemplo de la estructura del archivo:

| tipo | enunciado   | tiempo estimado | nivel de bloom | correcta | alternativas o justificación      |
|------|-------------|------------------|----------------|----------|----------------------------------|
| 1    | ¿Cuál de estos colores es primario? | 2  | 2      | C  | Naranjo-Rosado-Rojo-Morado           |
| 2    | ¿2+2=6?     | 1                | 1              | FALSE    | 2 + 2 suman 4                    |


- ### Visualización de la prueba

Para el paquete frontend se muestran la ventanas para cada una de las preguntas y la retroalimentación con los porcentajes de logros una vez enviadas las respuestas. En su desarrollo se implementa el uso Layouts, como Card y Grid, además de utilizar diferentes paneles con componentes como Jlabels, Jbuttons, etc. para posibilitar las funcionalidades de marcar las respuestas, avanzar y retroceder entre preguntas, y finalmente, visualizar la ventana con un resumen y las respuestas correctas de la prueba conectándolos con el backend el main del programa para asegurar su visualización. 

- ### Revisión de las Respuestas:

Una vez finalizada la prueba y enviadas las respuestas del usuario, se presenta un resumen con:

  - Porcentaje de respuestas correctas por tipo de ítem.
  - Porcentaje de respuestas correctas por nivel de la taxonomía de Bloom.
  - Revisión individual de ítems con un indicador si la respuesta fue correcta o no, la justificación esperada y la respuesta correcta.

## Supuestos y validaciones:

- El paquete frontend gestiona la interfaz gráfica del usuario usando Java Swing, desde la visualización del archivo usando una ventana por cada pregunta, un resumen completo de la prueba y sus porcentaje alcanzado, además de la revisión con la respuesta correcta en cada uno de los ítems.

- El paquete backend contiene la lógica de negocio, gestiona las preguntas con los atributos anteriormente mencionados que son controlados a través de una lista y a su vez dividiendo el programa en varias clases, evalúa las respuestas que fueron indicadas por el usuario y las valida en caso de ser correctas.

- Se mantiene el estado de las respuestas al navegar por la prueba, y se deshabilita la opción de responder una vez se cliclea la opción "Enviar Respuestas".

- Se aplica el patrón de notificación-suscripción entre frontend y backend.

- El archivo .csv debe estar correctamente estructurado según lo descrito para funcionar correctamente.
