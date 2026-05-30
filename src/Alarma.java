import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Alarma {
    private String id;
    private String etiqueta;
    private LocalTime hora;
    private boolean estaActiva;
    private Set<Integer> diasActivos; // 1 = Lunes, 7 = Domingo
    private String rutaSonido;
    private int volumen;
    
    // Funcionalidades avanzadas
    private ConfiguracionCircadiana configuracionCircadiana;
    private RetoMatematico retoMatematico;

    public Alarma(String id, String etiqueta, LocalTime hora) {
        this.id = id;
        this.etiqueta = etiqueta;
        this.hora = hora;
        this.estaActiva = true;
        this.diasActivos = new HashSet<>();
        this.volumen = 50; // Volumen por defecto
    }

    // Métodos de configuración
    public void setDiasRepeticion(Set<Integer> dias) { this.diasActivos = dias; }
    public void alternarActivacion() { this.estaActiva = !this.estaActiva; }
    public void setSonido(String ruta, int volumen) {
        this.rutaSonido = ruta;
        this.volumen = volumen;
    }
    public void setConfiguracionCircadiana(ConfiguracionCircadiana config) { 
        this.configuracionCircadiana = config; 
    }
    public void setRetoMatematico(RetoMatematico reto) { 
        this.retoMatematico = reto; 
    }

    // Getters
    public LocalTime getHora() { return hora; }
    public boolean isActiva() { return estaActiva; }
    public String getEtiqueta() { return etiqueta; }
    public String getId() { return id; }
    public RetoMatematico getRetoMatematico() { return retoMatematico; }
    
    @Override
    public String toString() {
        return "Alarma [" + hora + "] - " + etiqueta + " (Activa: " + estaActiva + ")";
    }
}