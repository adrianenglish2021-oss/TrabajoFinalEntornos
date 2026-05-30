# ⏰ Smart Alarm - Lógica de Despertador Inteligente

## 1. Descripción del Proyecto
Este proyecto implementa la lógica de backend en Java para un despertador inteligente inspirado en smartphones modernos. La aplicación se ejecuta sin interfaz gráfica, procesando la gestión de alarmas mediante un modelo puramente Orientado a Objetos.

## 2. Objetivos
- Resolver el problema de la gestión del tiempo y la creación de hábitos de sueño saludables.
- Evitar que los usuarios se queden dormidos utilizando métodos intrusivos o graduales (Retos matemáticos, Despertar circadiano).
- Analizar los hábitos del usuario a través de un perfil de estadísticas de sueño.

## 3. Tecnologías Utilizadas
- **Lenguaje:** Java (JDK 17+)
- **Herramientas de control de versiones:** Git y GitHub
- **Diagramado UML:** Mermaid
- **Editor:** Visual Studio Code

## 4. Instalación y Ejecución
1. Clonar el repositorio: `git clone <URL_DEL_REPO>`
2. Navegar a la carpeta fuente: `cd smart-alarm-project/src`
3. Compilar los archivos Java: `javac *.java`
4. Ejecutar el simulador: `java Main`

## 5. Estructura del Proyecto
El proyecto sigue una estructura limpia, separando el código fuente en `/src`, la documentación técnica en el `README.md` y separando responsabilidades en clases modulares.

## 6. Diseño Orientado a Objetos
El diseño consta de las siguientes clases principales:
- **`Alarma`**: Actúa como el modelo de datos principal. Encapsula información como la hora, etiqueta, días de repetición y configuraciones avanzadas.
- **`GestorAlarmas`**: Es el controlador central. Gestiona la lista de alarmas (crear, borrar, listar) e interactúa con otras clases para disparar o posponer alarmas.
- **`EstadisticasSueno`**: Acumula datos históricos del usuario basándose en los eventos (snooze/stop) que le envía el `GestorAlarmas`.
- **`RetoMatematico` y `ConfiguracionCircadiana`**: Clases auxiliares inyectadas por composición y agregación dentro de `Alarma`, respetando el principio de Responsabilidad Única (SRP).

## 7. Diagrama de Clases UML (Mermaid)

```mermaid
classDiagram
    class GestorAlarmas {
        -List~Alarma~ alarmas
        -EstadisticasSueno estadisticas
        +agregarAlarma(Alarma)
        +eliminarAlarma(String id)
        +getProximasAlarmas() List
        +dispararAlarma(Alarma)
        +posponerAlarma(Alarma)
        +detenerAlarma(Alarma)
    }

    class Alarma {
        -String id
        -String etiqueta
        -LocalTime hora
        -boolean estaActiva
        -Set~Integer~ diasActivos
        -int volumen
        +setDiasRepeticion(Set)
        +alternarActivacion()
        +setConfiguracionCircadiana(ConfiguracionCircadiana)
        +setRetoMatematico(RetoMatematico)
    }

    class EstadisticasSueno {
        -int totalPospuestasEstaSemana
        -int despertaresPuntuales
        +registrarPospuesta()
        +registrarDespertar(LocalTime, LocalTime)
        +imprimirEstadisticas()
    }

    class RetoMatematico {
        -int num1
        -int num2
        -int respuesta
        +getPregunta() String
        +verificarRespuesta(int) boolean
    }

    class ConfiguracionCircadiana {
        -int minutosAumentoProgresivo
        -boolean usarSonidosNaturaleza
    }

    GestorAlarmas "1" *-- "many" Alarma : gestiona >
    GestorAlarmas "1" *-- "1" EstadisticasSueno : actualiza >
    Alarma "1" o-- "0..1" RetoMatematico : puede tener >
    Alarma "1" o-- "0..1" ConfiguracionCircadiana : puede tener >