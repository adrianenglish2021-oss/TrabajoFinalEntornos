import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Alarma {
    // Atributos básicos
    private String id;
    private String etiqueta;
    private LocalTime hora;
    private boolean estaActiva;
    private Set<Integer> diasActivos;
    
    // --- NUEVAS FUNCIONALIDADES: SONIDO Y VOLUMEN ---
    private int volumen; // Nivel de 1 a 10
    private String tonoSonido; // Nombre del tono (ej. "Radar", "Pájaros")
    
    // Funcionalidades avanzadas (Agregación)
    private RetoMatematico retoMatematico;
    private ConfiguracionCircadiana configuracionCircadiana;

    // Constructor
    public Alarma(String id, String etiqueta, LocalTime hora) {
        this.id = id;
        this.etiqueta = etiqueta;
        this.hora = hora;
        this.estaActiva = true;
        this.diasActivos = new HashSet<>();
        
        // Valores por defecto para que no dé error si el usuario no los cambia
        this.volumen = 5; 
        this.tonoSonido = "Radar (Predeterminado)";
    }

    // --- GETTERS Y SETTERS BÁSICOS ---
    public String getId() { return id; }
    public String getEtiqueta() { return etiqueta; }
    public LocalTime getHora() { return hora; }
    public boolean isEstaActiva() { return estaActiva; }
    
    public void alternarActivacion() {
        this.estaActiva = !this.estaActiva;
    }

    public void setDiasRepeticion(Set<Integer> diasActivos) {
        this.diasActivos = diasActivos;
    }

    // --- MÉTODOS DE SONIDO PERSONALIZADO Y VOLUMEN ---
    public int getVolumen() { return volumen; }
    
    public void setVolumen(int volumen) {
        if (volumen >= 1 && volumen <= 10) {
            this.volumen = volumen;
        } else {
            System.out.println("Error: El volumen debe estar entre 1 y 10.");
        }
    }

    public String getTonoSonido() { return tonoSonido; }
    
    public void setTonoSonido(String tonoSonido) {
        this.tonoSonido = tonoSonido;
    }

    // --- MÉTODOS PARA FUNCIONALIDADES AVANZADAS ---
    public RetoMatematico getRetoMatematico() { return retoMatematico; }
    
    public void setRetoMatematico(RetoMatematico retoMatematico) {
        this.retoMatematico = retoMatematico;
    }

    public ConfiguracionCircadiana getConfiguracionCircadiana() { return configuracionCircadiana; }
    
    public void setConfiguracionCircadiana(ConfiguracionCircadiana configuracionCircadiana) {
        this.configuracionCircadiana = configuracionCircadiana;
    }

    // --- MÉTODO PARA IMPRIMIR LA ALARMA (Actualizado con Volumen y Tono) ---
    @Override
    public String toString() {
        String estado = estaActiva ? "ON" : "OFF";
        String extras = "";
        
        if (retoMatematico != null) extras += "[Reto Matemático] ";
        if (configuracionCircadiana != null) extras += "[Modo Circadiano] ";
        
        return String.format("%s - %s (%s) | Vol: %d/10 | Tono: %s %s", 
            hora.toString(), etiqueta, estado, volumen, tonoSonido, extras);
    }
}