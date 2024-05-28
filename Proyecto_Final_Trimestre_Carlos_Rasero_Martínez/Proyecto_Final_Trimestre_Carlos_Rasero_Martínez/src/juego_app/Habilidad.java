package juego_app;

import java.util.Random;

public class Habilidad {

	private int idHabilidad;
	private String nombre;
	private int poder;
	private int penalizacionDefensa;
	private double probabilidadCritico;

	public static final String RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";

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

	public void aplicarEfectoTipoAlumno(Jugador atacante, Jugador objetivo) {
		if (objetivo instanceof Padre) {
			System.out.println("¡Es super efectivo contra un Padre!");
		} else if (objetivo instanceof Profesor) {
			System.out.println("Es poco efectivo contra un Profesor...");
		}
	}

	public void aplicarEfectoTipoProfesor(Jugador atacante, Jugador objetivo) {
		if (objetivo instanceof Padre) {
			System.out.println("Es poco efectivo contra un Padre...");
		} else if (objetivo instanceof Alumno) {
			System.out.println("¡Es super efectivo contra un Alumno!");
		}
	}

	public void aplicarEfectoTipoPadres(Jugador atacante, Jugador objetivo) {
		if (objetivo instanceof Alumno) {
			System.out.println("Es poco efectivo contra un Alumno...");
		} else if (objetivo instanceof Profesor) {
			System.out.println("¡Es super efectivo contra un Profesor!");
		}
	}
}