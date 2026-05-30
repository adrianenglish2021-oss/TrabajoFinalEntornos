import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorAlarmas {
    private List<Alarma> alarmas;
    private EstadisticasSueno estadisticas;

    public GestorAlarmas() {
        this.alarmas = new ArrayList<>();
        this.estadisticas = new EstadisticasSueno();
    }

    public void agregarAlarma(Alarma alarma) {
        alarmas.add(alarma);
        System.out.println("Alarma creada: " + alarma.getEtiqueta());
    }

    public void eliminarAlarma(String id) {
        alarmas.removeIf(a -> a.getId().equals(id));
        System.out.println("Alarma eliminada.");
    }

    public List<Alarma> getProximasAlarmas() {
        return alarmas.stream()
            .filter(Alarma::isActiva)
            .sorted((a1, a2) -> a1.getHora().compareTo(a2.getHora()))
            .collect(Collectors.toList());
    }

    public void dispararAlarma(Alarma alarma) {
        System.out.println("¡RING RING! Sonando: " + alarma.getEtiqueta());
        
        if (alarma.getRetoMatematico() != null) {
            System.out.println("Reto matemático activado: " + alarma.getRetoMatematico().getPregunta());
            // Aquí iría la lógica donde el usuario introduce la respuesta
        }
    }

    public void posponerAlarma(Alarma alarma) {
        System.out.println("Alarma pospuesta 5 minutos: " + alarma.getEtiqueta());
        estadisticas.registrarPospuesta(); 
    }

    public void detenerAlarma(Alarma alarma) {
        System.out.println("Alarma detenida.");
        estadisticas.registrarDespertar(LocalTime.now(), alarma.getHora());
    }
    
    public EstadisticasSueno getEstadisticas() { return estadisticas; }
}