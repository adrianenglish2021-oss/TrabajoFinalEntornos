import java.time.LocalTime;

public class EstadisticasSueno {
    private int totalPospuestasEstaSemana;
    private int despertaresPuntuales;

    public EstadisticasSueno() {
        this.totalPospuestasEstaSemana = 0;
        this.despertaresPuntuales = 0;
    }

    public void registrarPospuesta() { 
        totalPospuestasEstaSemana++; 
    }
    
    public void registrarDespertar(LocalTime horaDespertarReal, LocalTime horaAlarma) {
        // Lógica simple: Si se levanta antes de 5 minutos desde la alarma, es puntual
        if (horaDespertarReal.isBefore(horaAlarma.plusMinutes(5))) {
            despertaresPuntuales++;
        }
    }

    public void imprimirEstadisticas() {
        System.out.println("--- Perfil de Sueño ---");
        System.out.println("Veces pospuestas esta semana: " + totalPospuestasEstaSemana);
        System.out.println("Despertares puntuales: " + despertaresPuntuales);
    }
}