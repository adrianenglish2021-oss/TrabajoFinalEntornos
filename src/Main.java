import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorAlarmas gestor = new GestorAlarmas();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        System.out.println("======================================");
        System.out.println("⏰ BIENVENIDO A TU SMART ALARM ⏰");
        System.out.println("======================================");

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Crear nueva alarma");
            System.out.println("2. Eliminar una alarma");
            System.out.println("3. Ver próximas alarmas");
            System.out.println("4. Simular que suena una alarma");
            System.out.println("5. Ver perfil de estadísticas");
            System.out.println("6. Configurar sonido y volumen");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    crearAlarmaInteractiva(gestor, scanner);
                    break;
                case "2":
                    System.out.print("Introduce el ID de la alarma a eliminar: ");
                    String idEliminar = scanner.nextLine();
                    gestor.eliminarAlarma(idEliminar);
                    break;
                case "3":
                    System.out.println("\n--- TUS ALARMAS ---");
                    if (gestor.getProximasAlarmas().isEmpty()) {
                        System.out.println("No hay alarmas activas.");
                    } else {
                        for (Alarma a : gestor.getProximasAlarmas()) {
                            System.out.println("ID: " + a.getId() + " | " + a.toString());
                        }
                    }
                    break;
                case "4":
                    simularAlarmaSonando(gestor, scanner);
                    break;
                case "5":
                    gestor.getEstadisticas().imprimirEstadisticas();
                    break;
                case "6":
                    configurarSonido(gestor, scanner);
                    break;
                case "7":
                    salir = true;
                    System.out.println("Apagando despertador... ¡Hasta la próxima!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
        scanner.close();
    }

    private static void crearAlarmaInteractiva(GestorAlarmas gestor, Scanner scanner) {
        System.out.print("Introduce un ID único (ej. 1, 2, A): ");
        String id = scanner.nextLine();
        
        System.out.print("Etiqueta (ej. Trabajo, Gimnasio): ");
        String etiqueta = scanner.nextLine();
        
        System.out.print("Hora en formato HH:MM (ej. 07:30): ");
        String horaStr = scanner.nextLine();
        
        try {
            LocalTime hora = LocalTime.parse(horaStr);
            Alarma nuevaAlarma = new Alarma(id, etiqueta, hora);
            
            System.out.print("¿Añadir modo Circadiano y Reto Matemático? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                nuevaAlarma.setConfiguracionCircadiana(new ConfiguracionCircadiana(15, true));
                nuevaAlarma.setRetoMatematico(new RetoMatematico());
                System.out.println("Funcionalidades avanzadas activadas.");
            }
            
            gestor.agregarAlarma(nuevaAlarma);
            
        } catch (DateTimeParseException e) {
            System.out.println("Error: El formato de la hora es incorrecto. Usa HH:MM.");
        }
    }

    private static void configurarSonido(GestorAlarmas gestor, Scanner scanner) {
        if (gestor.getProximasAlarmas().isEmpty()) {
            System.out.println("No hay alarmas creadas para configurar.");
            return;
        }
        
        System.out.print("Introduce el ID de la alarma a modificar: ");
        String id = scanner.nextLine();
        
        Alarma alarmaModificar = null;
        for (Alarma a : gestor.getProximasAlarmas()) {
            if (a.getId().equals(id)) {
                alarmaModificar = a;
                break;
            }
        }
        
        if (alarmaModificar == null) {
            System.out.println("Error: No se encontró la alarma con ID " + id);
            return;
        }
        
        System.out.print("Introduce el nuevo nivel de volumen (1-10): ");
        try {
            int vol = Integer.parseInt(scanner.nextLine());
            alarmaModificar.setVolumen(vol);
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes introducir un número.");
        }
        
        System.out.print("Introduce el nombre del nuevo tono (ej. Pajaritos, Rock): ");
        String tono = scanner.nextLine();
        alarmaModificar.setTonoSonido(tono);
        
        System.out.println("¡Sonido y volumen actualizados correctamente!");
    }

    private static void simularAlarmaSonando(GestorAlarmas gestor, Scanner scanner) {
        if (gestor.getProximasAlarmas().isEmpty()) {
            System.out.println("No hay alarmas para hacer sonar.");
            return;
        }
        
        Alarma alarmaActual = gestor.getProximasAlarmas().get(0);
        gestor.dispararAlarma(alarmaActual);

        if (alarmaActual.getRetoMatematico() != null) {
            boolean retoSuperado = false;
            while (!retoSuperado) {
                System.out.print("Respuesta: ");
                try {
                    int respuestaUsuario = Integer.parseInt(scanner.nextLine());
                    if (alarmaActual.getRetoMatematico().verificarRespuesta(respuestaUsuario)) {
                        System.out.println("¡Correcto! Reto superado.");
                        retoSuperado = true;
                    } else {
                        System.out.println("Incorrecto. La alarma sigue sonando... ¡Despierta!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, introduce un número.");
                }
            }
        }

        System.out.print("¿Qué deseas hacer? (1) Detener (2) Posponer (Snooze): ");
        String accion = scanner.nextLine();
        
        if (accion.equals("2")) {
            gestor.posponerAlarma(alarmaActual);
        } else {
            gestor.detenerAlarma(alarmaActual);
        }
    }
}