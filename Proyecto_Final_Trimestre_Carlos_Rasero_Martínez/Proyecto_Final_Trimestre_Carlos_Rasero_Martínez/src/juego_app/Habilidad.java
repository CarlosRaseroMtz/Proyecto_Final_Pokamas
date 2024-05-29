package juego_app;

import java.util.Random;

/**
 * La clase Habilidad representa una habilidad que puede ser utilizada por un jugador.
 * Contiene información sobre el nombre, poder, penalización de defensa, y la probabilidad de crítico de la habilidad.
 */
public class Habilidad {

    private int idHabilidad;
    private String nombre;
    private int poder;
    private int penalizacionDefensa;
    private double probabilidadCritico;

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";

    /**
     * Constructor de la clase Habilidad.
     *
     * @param idHabilidad Identificador de la habilidad.
     * @param nombre Nombre de la habilidad.
     * @param poder Poder de la habilidad.
     * @param penalizacionDefensa Penalización a la defensa que aplica la habilidad.
     * @param probabilidadCritico Probabilidad de que la habilidad haga un golpe crítico.
     */
    public Habilidad(int idHabilidad, String nombre, int poder, int penalizacionDefensa, double probabilidadCritico) {
        this.idHabilidad = idHabilidad;
        this.nombre = nombre;
        this.poder = poder;
        this.penalizacionDefensa = penalizacionDefensa;
        this.probabilidadCritico = probabilidadCritico;
    }

    /**
     * Obtiene el identificador de la habilidad.
     * 
     * @return El identificador de la habilidad.
     */
    public int getIdHabilidad() {
        return idHabilidad;
    }

    /**
     * Establece el identificador de la habilidad.
     * 
     * @param idHabilidad El nuevo identificador de la habilidad.
     */
    public void setIdHabilidad(int idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    /**
     * Obtiene el nombre de la habilidad.
     * 
     * @return El nombre de la habilidad.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la habilidad.
     * 
     * @param nombre El nuevo nombre de la habilidad.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el poder de la habilidad.
     * 
     * @return El poder de la habilidad.
     */
    public int getPoder() {
        return poder;
    }

    /**
     * Establece el poder de la habilidad.
     * 
     * @param poder El nuevo poder de la habilidad.
     */
    public void setPoder(int poder) {
        this.poder = poder;
    }

    /**
     * Obtiene la penalización de defensa de la habilidad.
     * 
     * @return La penalización de defensa de la habilidad.
     */
    public int getPenalizacionDefensa() {
        return penalizacionDefensa;
    }

    /**
     * Establece la penalización de defensa de la habilidad.
     * 
     * @param penalizacionDefensa La nueva penalización de defensa de la habilidad.
     */
    public void setPenalizacionDefensa(int penalizacionDefensa) {
        this.penalizacionDefensa = penalizacionDefensa;
    }

    /**
     * Obtiene la probabilidad de crítico de la habilidad.
     * 
     * @return La probabilidad de crítico de la habilidad.
     */
    public double getProbabilidadCritico() {
        return probabilidadCritico;
    }

    /**
     * Establece la probabilidad de crítico de la habilidad.
     * 
     * @param probabilidadCritico La nueva probabilidad de crítico de la habilidad.
     */
    public void setProbabilidadCritico(double probabilidadCritico) {
        this.probabilidadCritico = probabilidadCritico;
    }

    /**
     * Aplica el efecto de la habilidad a un objetivo.
     * 
     * @param atacante El jugador que usa la habilidad.
     * @param objetivo El jugador objetivo de la habilidad.
     */
    public void aplicarEfecto(Jugador atacante, Jugador objetivo) {
        Random random = new Random();
        double probabilidad = random.nextDouble();
        if (probabilidad <= probabilidadCritico) {
            objetivo.setVida(objetivo.getVida() - this.poder * 2);
            System.out.println(RED + "¡Golpe crítico!" + RESET);
        } else {
            objetivo.setVida(objetivo.getVida() - this.poder);
        }
        objetivo.setDefensa(objetivo.getDefensa() + this.penalizacionDefensa);
    }

    /**
     * Aplica el efecto de la habilidad contra un objetivo de tipo Alumno.
     * 
     * @param atacante El jugador que usa la habilidad.
     * @param objetivo El jugador objetivo de la habilidad.
     */
    public void aplicarEfectoTipoAlumno(Jugador atacante, Jugador objetivo) {
        if (objetivo instanceof Padre) {
            System.out.println("¡Es super efectivo contra un Padre!");
        } else if (objetivo instanceof Profesor) {
            System.out.println("Es poco efectivo contra un Profesor...");
        }
    }

    /**
     * Aplica el efecto de la habilidad contra un objetivo de tipo Profesor.
     * 
     * @param atacante El jugador que usa la habilidad.
     * @param objetivo El jugador objetivo de la habilidad.
     */
    public void aplicarEfectoTipoProfesor(Jugador atacante, Jugador objetivo) {
        if (objetivo instanceof Padre) {
            System.out.println("Es poco efectivo contra un Padre...");
        } else if (objetivo instanceof Alumno) {
            System.out.println("¡Es super efectivo contra un Alumno!");
        }
    }

    /**
     * Aplica el efecto de la habilidad contra un objetivo de tipo Padre.
     * 
     * @param atacante El jugador que usa la habilidad.
     * @param objetivo El jugador objetivo de la habilidad.
     */
    public void aplicarEfectoTipoPadres(Jugador atacante, Jugador objetivo) {
        if (objetivo instanceof Alumno) {
            System.out.println("Es poco efectivo contra un Alumno...");
        } else if (objetivo instanceof Profesor) {
            System.out.println("¡Es super efectivo contra un Profesor!");
        }
    }
}
