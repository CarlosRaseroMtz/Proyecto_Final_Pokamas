package juego_app;

import java.util.Random;

/**
 * La clase Alumno representa a un jugador que es un alumno, con atributos y métodos específicos.
 * Extiende la clase Jugador.
 */
public class Alumno extends Jugador {

    /**
     * Curso del alumno.
     */
    private String curso;

    // Constantes para colores de texto
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";
    private static final String PINK = "\u001B[95m";
    private static final String LIGHT_GREEN = "\u001B[92m";
    private static final String YELLOW = "\u001B[33m";
    private static final String LIGHT_YELLOW = "\u001B[93m";
    private static final String GREEN = "\u001B[32m";

    /**
     * Constructor de la clase Alumno.
     * 
     * @param idJugador Identificador del jugador.
     * @param nombre Nombre del alumno.
     * @param vida Puntos de vida del alumno.
     * @param ataque Puntos de ataque del alumno.
     * @param defensa Puntos de defensa del alumno.
     * @param curso Curso del alumno, debe ser '1 DAM', '1' o 'DESERTOR'.
     * @throws IllegalArgumentException Si el curso no es válido.
     */
    public Alumno(int idJugador, String nombre, int vida, int ataque, int defensa, String curso) {
        super(idJugador, nombre, vida, ataque, defensa);
        if (curso.equalsIgnoreCase("1 DAM") || curso.equals("1") || curso.equalsIgnoreCase("DESERTOR")) {
            this.curso = curso.equalsIgnoreCase("1 DAM") ? "1 DAM" : curso.equalsIgnoreCase("DESERTOR") ? "DESERTOR" : "1";
        } else {
            throw new IllegalArgumentException("El curso debe ser '1 DAM', '1' o 'DESERTOR'.");
        }
    }

    /**
     * Obtiene el curso del alumno.
     * 
     * @return El curso del alumno.
     */
    public String getCurso() {
        return curso;
    }

    /**
     * Establece el curso del alumno.
     * 
     * @param curso El nuevo curso del alumno, debe ser '1 DAM', '1' o 'DESERTOR'.
     * @throws IllegalArgumentException Si el curso no es válido.
     */
    public void setCurso(String curso) {
        if (curso.equalsIgnoreCase("1 DAM") || curso.equals("1") || curso.equalsIgnoreCase("DESERTOR")) {
            this.curso = curso.equalsIgnoreCase("1 DAM") ? "1 DAM" : curso.equalsIgnoreCase("DESERTOR") ? "DESERTOR" : "1";
        } else {
            throw new IllegalArgumentException("El curso debe ser '1 DAM', '1' o 'DESERTOR'.");
        }
    }

    /**
     * Devuelve una representación en cadena de texto del alumno.
     * 
     * @return Una cadena de texto con la información del alumno.
     */
    @Override
    public String toString() {
        return "" + YELLOW + "Alumno [ " + RESET + LIGHT_YELLOW + "nombre=" + RESET + WHITE + nombre + RESET + LIGHT_GREEN + ", vida=" + RESET + WHITE
                + vida + RESET + RED + ", ataque=" + RESET + WHITE + ataque + RESET + CYAN + ", defensa=" + RESET
                + WHITE + defensa + RESET + PINK + ", curso=" + RESET + WHITE + curso + RESET + " ]";
    }

    /**
     * Realiza un ataque a otro jugador.
     * 
     * @param oponente El jugador oponente que será atacado.
     */
    @Override
    public void atacar(Jugador oponente) {
        Habilidad habilidad = seleccionarHabilidad();
        Items items = seleccionarItem();
        Random random = new Random();
        double probabilidad = random.nextDouble();
        System.out.println(nombre + " utiliza la habilidad " + habilidad.getNombre() + " contra " + oponente.getNombre());
        habilidad.aplicarEfecto(this, oponente);
        habilidad.aplicarEfectoTipoProfesor(this, oponente);
        if (probabilidad <= 0.30) {
            items.usarItem(this, oponente);
            System.out.println("Además usó: ");
            System.out.println(nombre + " utiliza el item " + items.getNombre());
        }
    }

    /**
     * Inicializa las habilidades del alumno.
     */
    @Override
    protected void inicializarHabilidades() {
        habilidades[0] = new Habilidad(1, GREEN + "Mentir: " + RESET + LIGHT_GREEN + "Falló la habilidad*('Se mintió a sí mismo')." + RESET, 10, -10, 0.50);
        habilidades[1] = new Habilidad(2, GREEN + "Usar ChatGPT: " + RESET + LIGHT_GREEN + "'Un gran poder conlleva una gran responsabilidad.'" + RESET, 25, -5, 0.20);
        habilidades[2] = new Habilidad(3, GREEN + "Estudiar: " + RESET + LIGHT_GREEN + "Esta habilidad siempre acierta al objetivo" + RESET, 25, 20, 0.40);
        habilidades[3] = new Habilidad(4, GREEN + "Faltar: " + RESET + LIGHT_GREEN + "'Hoy me quedo en casita.'" + RESET, 20, 3, 0.30);
    }

    /**
     * Inicializa los items del alumno.
     */
    @Override
    protected void inicializarItems() {
        items[0] = new Items(1, GREEN + "Tomar Merienda: " + RESET + LIGHT_GREEN + "'El recreo es sagrado'" + RESET, 25, 20);
        items[1] = new Items(2, GREEN + "Fumar: " + RESET + LIGHT_GREEN + "'Recuperas todas las ganas de volver a clase.'" + RESET, 100, 20);
        items[2] = new Items(3, GREEN + "Beber Bebida Energética: " + RESET + LIGHT_GREEN + "'Te da un Boost a tus 5h de sueño'" + RESET, 15, 10);
        items[3] = new Items(4, GREEN + "Usar Cascos: " + RESET + LIGHT_GREEN + "'La música es poder'" + RESET, 40, 20);
    }
}
