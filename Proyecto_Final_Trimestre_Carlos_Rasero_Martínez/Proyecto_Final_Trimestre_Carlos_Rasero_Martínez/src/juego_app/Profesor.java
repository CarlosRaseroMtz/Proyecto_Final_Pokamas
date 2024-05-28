package juego_app;

import java.util.Random;

public class Profesor extends Jugador {

	private String asignatura;
	
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";
    private static final String PINK = "\u001B[95m";
    private static final String LIGHT_GREEN = "\u001B[92m";
    private static final String YELLOW = "\u001B[33m";
    private static final String LIGHT_YELLOW = "\u001B[93m";
    private static final String GREEN = "\u001B[32m";

	public Profesor(int idJugador, String nombre, int vida, int ataque, int defensa, String asignatura) {
		super(idJugador, nombre, vida, ataque, defensa);
		if (asignatura.equalsIgnoreCase("prog") || asignatura.equalsIgnoreCase("html")
				|| asignatura.equalsIgnoreCase("bdd") || asignatura.equalsIgnoreCase("si")
				|| asignatura.equalsIgnoreCase("ed") || asignatura.equalsIgnoreCase("fol")) {
			this.asignatura = asignatura.toLowerCase();
		} else {
			throw new IllegalArgumentException("La asignatura debe ser 'prog', 'html', 'bdd', 'si', 'ed' o 'fol'.");
		}
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		if (asignatura.equalsIgnoreCase("prog") || asignatura.equalsIgnoreCase("html")
				|| asignatura.equalsIgnoreCase("bdd") || asignatura.equalsIgnoreCase("si")
				|| asignatura.equalsIgnoreCase("ed")) {
			this.asignatura = asignatura.toLowerCase();
		} else {
			throw new IllegalArgumentException("La asignatura debe ser 'prog', 'html', 'bdd', 'si' o 'ed'.");
		}
	}

	@Override
	public void atacar(Jugador oponente) {
		Habilidad habilidad = seleccionarHabilidad();
		Items items = seleccionarItem();
		Random random = new Random();
		double probabilidad = random.nextDouble();
		System.out
				.println(nombre + " utiliza la habilidad " + habilidad.getNombre() + " contra " + oponente.getNombre());
		habilidad.aplicarEfecto(this, oponente);
		habilidad.aplicarEfectoTipoProfesor(this, oponente);
		if (probabilidad <= 0.28) {
			items.usarItem(this, oponente);
			System.out.println("Además usó: ");
			System.out.println(nombre + " utiliza el item " + items.getNombre());
		}
	}

	@Override
	protected void inicializarHabilidades() {
		habilidades[0] = new Habilidad(1, GREEN + "Poner Examen Sorpresa: " + RESET + LIGHT_GREEN + "'Nadie lo ve venir'" + RESET, 25, -3, 0.20);
		habilidades[1] = new Habilidad(2, GREEN + "Enfado: " + RESET + LIGHT_GREEN + "'Con razon por no estudiar'" + RESET, 20, -1, 0.30);
		habilidades[2] = new Habilidad(3, GREEN + "Usar Moodle: " + RESET + LIGHT_GREEN + "'La usa (pero a nadie le gusta*)'" + RESET, 15, 1, 0.50);
		habilidades[3] = new Habilidad(4, GREEN + "Salvar: " + RESET + LIGHT_GREEN + "'Pasa de un 4 a un 5'" + RESET, 20, 3, 0.40);
	}

	@Override
	protected void inicializarItems() {
		items[0] = new Items(1, GREEN + "Beber Café: " + RESET + LIGHT_GREEN + "'Gracias al cafe, aguanta 3H mas'" + RESET, 25, 20);
		items[1] = new Items(2, GREEN + "Ir a la sala de profesores: " + RESET + LIGHT_GREEN + "'Lugar Oculto*'" + RESET, 20, 30);
		items[2] = new Items(3, GREEN + "Poner parte: " + RESET + LIGHT_GREEN + "'En este instituto no toleramos los saltavallas'" + RESET, 15, 15);
		items[3] = new Items(4, GREEN + "Tirar Internet: " + RESET + LIGHT_GREEN + "'Obliga al jugador a tener que irse, sube mucho la salud'" + RESET, 90, 30);
	}

	@Override
	public String toString() {
		return "" + YELLOW + "Profesor [ " + RESET + LIGHT_YELLOW + "nombre=" + RESET + WHITE + nombre + RESET
				+ LIGHT_GREEN + ", vida=" + RESET + WHITE + vida + RESET + RED + ", ataque=" + RESET + WHITE + ataque
				+ RESET + CYAN + ", defensa=" + RESET + WHITE + defensa + RESET + PINK + ", asignatura=" + RESET + WHITE
				+ asignatura + RESET + " ]";
	}
}
