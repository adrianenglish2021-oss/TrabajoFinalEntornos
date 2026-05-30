import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Alarma {
    // Atributos básicos
    private String id;
    private String etiqueta;
    private LocalTime hora;
    private boolean estaActiva;
    private Set<Integer> diasActivos; // 1=Lunes, 7=Domingo
    
    // Funcionalidades de Sonido
    private int volumen;
    private String tonoSonido;
    
    // Funcionalidades avanzadas (Agregación)
    private RetoMatematico retoMatematico;
    private ConfiguracionCircadiana configuracionCircadiana;

    // Constructor
    public Alarma(String id, String etiqueta, LocalTime hora) {
        this.id = id;
        this.etiqueta = etiqueta;
        this.hora = hora;
        this.estaActiva = true;
        this.diasActivos = new HashSet<>(); // Por defecto vacío (Suena 1 sola vez)
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

    // --- MÉTODOS DE SONIDO Y VOLUMEN ---
    public int getVolumen() { return volumen; }
    public void setVolumen(int volumen) {
        if (volumen >= 1 && volumen <= 10) this.volumen = volumen;
        else System.out.println("Error: El volumen debe estar entre 1 y 10.");
    }
    public String getTonoSonido() { return tonoSonido; }
    public void setTonoSonido(String tonoSonido) { this.tonoSonido = tonoSonido; }

    // --- MÉTODOS DE RETOS Y CIRCADIANO ---
    public RetoMatematico getRetoMatematico() { return retoMatematico; }
    public void setRetoMatematico(RetoMatematico retoMatematico) { this.retoMatematico = retoMatematico; }
    public ConfiguracionCircadiana getConfiguracionCircadiana() { return configuracionCircadiana; }
    public void setConfiguracionCircadiana(ConfiguracionCircadiana configuracionCircadiana) {
        this.configuracionCircadiana = configuracionCircadiana;
    }

    // --- LÓGICA DE TRADUCCIÓN DE DÍAS ---
    public String getDiasFormateados() {
        if (diasActivos.isEmpty()) return "Solo 1 vez";
        if (diasActivos.size() == 7) return "Todos los días";
        
        boolean laborables = diasActivos.contains(1) && diasActivos.contains(2) && 
                             diasActivos.contains(3) && diasActivos.contains(4) && 
                             diasActivos.contains(5) && diasActivos.size() == 5;
        if (laborables) return "Laborables";
        
        boolean findes = diasActivos.contains(6) && diasActivos.contains(7) && diasActivos.size() == 2;
        if (findes) return "Fines de semana";
        
        String[] nombres = {"", "L", "M", "X", "J", "V", "S", "D"};
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 7; i++) {
            if (diasActivos.contains(i)) sb.append(nombres[i]).append("-");
        }
        if (sb.length() > 0) sb.setLength(sb.length() - 1); // Quitar el último guion
        return sb.toString();
    }

    @Override
    public String toString() {
        String estado = estaActiva ? "ON" : "OFF";
        String extras = "";
        if (retoMatematico != null) extras += "[Reto Matemático] ";
        if (configuracionCircadiana != null) extras += "[Modo Circadiano] ";
        
        // Ahora imprimimos también los días de repetición
        return String.format("%s - %s (%s) | %s | Vol: %d/10 | Tono: %s %s", 
            hora.toString(), etiqueta, estado, getDiasFormateados(), volumen, tonoSonido, extras);
    }
}