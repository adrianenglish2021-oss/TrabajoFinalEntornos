import java.util.ArrayList;
import java.util.List;

public class GestorAlarmas {
    private List<Alarma> alarmas;
    private EstadisticasSueno estadisticas;

    // Constructor
    public GestorAlarmas() {
        this.alarmas = new ArrayList<>();
        this.estadisticas = new EstadisticasSueno();
    }

    // Agregar una nueva alarma a la lista
    public void agregarAlarma(Alarma alarma) {
        alarmas.add(alarma);
        System.out.println("¡Alarma creada con éxito! ID: " + alarma.getId());
    }

    // Eliminar una alarma por su ID único
    public void eliminarAlarma(String id) {
        boolean encontrada = false;
        for (int i = 0; i < alarmas.size(); i++) {
            if (alarmas.get(i).getId().equals(id)) {
                alarmas.remove(i);
                System.out.println("Alarma con ID " + id + " eliminada correctamente.");
                encontrada = true;
                break;
            }
        }
        if (!encontrada) {
            System.out.println("Error: No se encontró ninguna alarma con el ID: " + id);
        }
    }

    // Obtener la lista de todas las alarmas
    public List<Alarma> getProximasAlarmas() {
        return alarmas;
    }

    // Simular el disparo de una alarma
    public void dispararAlarma(Alarma alarma) {
        System.out.println("\n-------------------------------------------");
        System.out.println("⏰ !!! RIPUUUIPUIP !!! ⏰");
        System.out.println("SUENA LA ALARMA: " + alarma.getEtiqueta().toUpperCase());
        System.out.println("CONFIGURACIÓN -> Tono: " + alarma.getTonoSonido() + " | Vol: " + alarma.getVolumen() + "/10");
        
        // Si tiene modo circadiano, avisamos de la luz progresiva
        if (alarma.getConfiguracionCircadiana() != null) {
            System.out.println("[Circadiano] Incrementando brillo de la pantalla progresivamente...");
        }
        
        // Si tiene reto matemático, mostramos la pregunta
        if (alarma.getRetoMatematico() != null) {
            System.out.println("\n[RETO ACTIVADO] ¡Resuelve para apagar!");
            System.out.println("Pregunta: " + alarma.getRetoMatematico().getPregunta());
        }
        System.out.println("-------------------------------------------");
    }

    // Posponer alarma (Snooze)
    public void posponerAlarma(Alarma alarma) {
        estadisticas.registrarPospuesta();
        System.out.println("Alarma pospuesta 5 minutos. ¡5 minutitos más...!");
    }

    // Detener alarma definitivamente
    public void detenerAlarma(Alarma alarma) {
        // Simulamos que el usuario se despierta en la hora configurada de la alarma
        estadisticas.registrarDespertar(alarma.getHora(), alarma.getHora());
        System.out.println("Alarma detenida. ¡Buenos días!");
    }

    // Obtener el objeto de estadísticas
    public EstadisticasSueno getEstadisticas() {
        return estadisticas;
    }
}