import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        GestorAlarmas gestor = new GestorAlarmas();

        // 1. Crear alarma normal
        Alarma alarma1 = new Alarma("1", "Trabajo", LocalTime.of(7, 0));
        gestor.agregarAlarma(alarma1);

        // 2. Crear alarma avanzada (Circadiana + Reto Matemático)
        Alarma alarma2 = new Alarma("2", "Estudio", LocalTime.of(8, 30));
        alarma2.setConfiguracionCircadiana(new ConfiguracionCircadiana(15, true));
        alarma2.setRetoMatematico(new RetoMatematico());
        gestor.agregarAlarma(alarma2);

        // 3. Consultar próximas alarmas
        System.out.println("\nPróximas alarmas:");
        for (Alarma a : gestor.getProximasAlarmas()) {
            System.out.println(a.toString());
        }

        // 4. Simular que suena la alarma 2 y el usuario la pospone
        System.out.println("\n--- Simulación de tiempo ---");
        gestor.dispararAlarma(alarma2);
        gestor.posponerAlarma(alarma2);
        
        // 5. Simular apagar la alarma
        gestor.detenerAlarma(alarma2);

        // 6. Revisar estadísticas
        gestor.getEstadisticas().imprimirEstadisticas();
    }
}