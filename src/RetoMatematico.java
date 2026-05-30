import java.util.Random;

public class RetoMatematico {
    private int num1;
    private int num2;
    private int respuesta;

    public RetoMatematico() {
        Random rand = new Random();
        this.num1 = rand.nextInt(50) + 1;
        this.num2 = rand.nextInt(50) + 1;
        this.respuesta = num1 + num2;
    }

    public String getPregunta() { 
        return "¿Cuánto es " + num1 + " + " + num2 + "?"; 
    }
    
    public boolean verificarRespuesta(int entradaUsuario) { 
        return entradaUsuario == respuesta; 
    }
}