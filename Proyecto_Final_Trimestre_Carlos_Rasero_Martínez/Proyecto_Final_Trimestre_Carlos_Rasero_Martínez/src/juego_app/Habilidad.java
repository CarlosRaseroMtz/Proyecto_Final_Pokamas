package juego_app;

import java.util.Random;

public class Habilidad {

	private int idHabilidad;
	private String nombre;
	private int poder;
	private int penalizacionDefensa;
	private double probabilidadCritico; // Probabilidad de un golpe crítico (0 a 1)

	public Habilidad(int idHabilidad, String nombre, int poder, int penalizacionDefensa, double probabilidadCritico) {
		this.idHabilidad = idHabilidad;
		this.nombre = nombre;
		this.poder = poder;
		this.penalizacionDefensa = penalizacionDefensa;
		this.probabilidadCritico = probabilidadCritico;
	}

	public int getIdHabilidad() {
		return idHabilidad;
	}

	public void setIdHabilidad(int idHabilidad) {
		this.idHabilidad = idHabilidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPoder() {
		return poder;
	}

	public void setPoder(int poder) {
		this.poder = poder;
	}

	public int getPenalizacionDefensa() {
		return penalizacionDefensa;
	}

	public void setPenalizacionDefensa(int penalizacionDefensa) {
		this.penalizacionDefensa = penalizacionDefensa;
	}

	public double getProbabilidadCritico() {
		return probabilidadCritico;
	}

	public void setProbabilidadCritico(double probabilidadCritico) {
		this.probabilidadCritico = probabilidadCritico;
	}

	/**
	 * Aplica el efecto de la habilidad al jugador objetivo.
	 * 
	 * @param atacante El jugador que realiza la habilidad.
	 * @param objetivo El jugador que recibe el efecto de la habilidad.
	 */
	public void aplicarEfecto(Jugador atacante, Jugador objetivo) {
		final String RESET = "\u001B[0m";
		final String RED = "\u001B[31m";
		Random random = new Random();
		double probabilidad = random.nextDouble(); // Generar un número aleatorio entre 0 y 1
		if (probabilidad <= probabilidadCritico) { // Si el número está dentro de la probabilidad de crítico
			// Golpe crítico: incrementar el poder del ataque
			objetivo.setVida(objetivo.getVida() - this.poder * 2); // Doble de daño
			System.out.println(RED + "¡Golpe crítico!" + RESET);
		} else {
			// Ataque normal
			objetivo.setVida(objetivo.getVida() - this.poder);
		}
		objetivo.setDefensa(objetivo.getDefensa() + this.penalizacionDefensa);
	}

	public void aplicarEfectoTipoAlumno(Jugador atacante, Jugador objetivo) {
		if (objetivo instanceof Padre) {
			// Efecto para atacar a un Padre
			// Por ejemplo:
			System.out.println("¡Es super efectivo contra un Padre!");
			// Aquí puedes agregar el efecto específico para atacar a un Padre
		} else if (objetivo instanceof Profesor) {
			// Efecto para atacar a un Profesor
			// Por ejemplo:
			System.out.println("Es poco efectivo contra un Profesor...");
			// Aquí puedes agregar el efecto específico para atacar a un Profesor
		}
	}

	public void aplicarEfectoTipoProfesor(Jugador atacante, Jugador objetivo) {
		if (objetivo instanceof Padre) {
			// Efecto para atacar a un Padre
			// Por ejemplo:
			System.out.println("Es poco efectivo contra un Padre...");
			// Aquí puedes agregar el efecto específico para atacar a un Padre
		} else if (objetivo instanceof Alumno) {
			// Efecto para atacar a un Profesor
			// Por ejemplo:
			System.out.println("¡Es super efectivo contra un Alumno!");
			// Aquí puedes agregar el efecto específico para atacar a un Profesor
		}
	}

	public void aplicarEfectoTipoPadres(Jugador atacante, Jugador objetivo) {
		if (objetivo instanceof Alumno) {
			// Efecto para atacar a un Padre
			// Por ejemplo:
			System.out.println("Es poco efectivo contra un Alumno...");
			// Aquí puedes agregar el efecto específico para atacar a un Padre
		} else if (objetivo instanceof Profesor) {
			// Efecto para atacar a un Profesor
			// Por ejemplo:
			System.out.println("¡Es super efectivo contra un Profesor!");
			// Aquí puedes agregar el efecto específico para atacar a un Profesor
		}
	}

}