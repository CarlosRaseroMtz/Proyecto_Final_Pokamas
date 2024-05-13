package juego_app;

import java.util.Random;

public class Alumno extends Jugador {

    private String curso;

    public Alumno(int idJugador, String nombre, int vida, int ataque, int defensa, String curso) {
        super(idJugador, nombre, vida, ataque, defensa);
        if (curso.equalsIgnoreCase("1 DAM") || curso.equals("1") || curso.equalsIgnoreCase("DESERTOR")) {
            this.curso = curso.equalsIgnoreCase("1 DAM") ? "1 DAM" : curso.equalsIgnoreCase("DESERTOR") ? "DESERTOR" : "1"; // Normalizamos el valor
        } else {
            throw new IllegalArgumentException("El curso debe ser '1 DAM', '1' o 'DESERTOR'.");
        }
    }

    public void setCurso(String curso) {
        if (curso.equalsIgnoreCase("1 DAM") || curso.equals("1") || curso.equalsIgnoreCase("DESERTOR")) {
            this.curso = curso.equalsIgnoreCase("1 DAM") ? "1 DAM" : curso.equalsIgnoreCase("DESERTOR") ? "DESERTOR" : "1";
        } else {
            throw new IllegalArgumentException("El curso debe ser '1 DAM', '1' o 'DESERTOR'.");
        }
    }

    
	@Override
	public void atacar(Jugador oponente) {
		// Seleccionar habilidad aleatoria
		Habilidad habilidad = seleccionarHabilidad();
		Items items = seleccionarItem();
		Random random = new Random();
		double probabilidad = random.nextDouble(); // Generar un número aleatorio entre 0 y 1
		System.out.println(nombre + " utiliza la habilidad " + habilidad.getNombre()+" contra "+oponente.getNombre());
		habilidad.aplicarEfecto(this, oponente);
		habilidad.aplicarEfectoTipoProfesor(this, oponente);
		if (probabilidad <= 0.23) {
			items.usarItem(this, oponente);
			System.out.println("Además usó: ");
			System.out.println(nombre + " utiliza el item " +items.getNombre()+" contra "+oponente.getNombre());
		}
	}

	@Override
	protected void inicializarHabilidades() {
		final String RESET = "\u001B[0m";
		final String GREEN = "\u001B[32m";
		final String LIGHT_GREEN = "\u001B[92m";

		habilidades[0] = new Habilidad(1, GREEN+"Mentir: "+RESET+LIGHT_GREEN+"Falló la habilidad*('Se mintió a sí mismo')."+RESET, 10, -10, 0.50); // 40% probabilidad de crítico
		habilidades[1] = new Habilidad(2, GREEN+"Usar ChatGPT: "+RESET+LIGHT_GREEN+"'Un gran poder conlleva una gran responsabilidad.'"+RESET, 25, -5, 0.20); // 30% probabilidad de crítico
		habilidades[2] = new Habilidad(3, GREEN+"Estudiar: "+RESET+LIGHT_GREEN+"Esta habilidad siempre acierta al objetivo"+RESET, 15, -3, 0.40); // 65% probabilidad de crítico
		habilidades[3] = new Habilidad(4, GREEN+"Faltar: "+RESET+LIGHT_GREEN+"'Hoy me quedo en casita.'"+RESET, 20, 3, 0.30); // 20% probabilidad de crítico
	}
	
	@Override
	protected void inicializarItems() {
		final String RESET = "\u001B[0m";
		final String GREEN = "\u001B[32m";
		final String LIGHT_GREEN = "\u001B[92m";
		items[0] = new Items(1, GREEN+"Tomar Merienda: "+RESET+LIGHT_GREEN+"'El recreo es sagrado'"+RESET, 25, 20);
		items[1] = new Items(2, GREEN+"Fumar: "+RESET+LIGHT_GREEN+"'Recuperas todas las ganas de volver a clase.'"+RESET, 100, 20);
		items[2] = new Items(3, GREEN+"Beber Bebida Energética: "+RESET+LIGHT_GREEN+"'Te da un Boost a tus 5h de sueño'"+RESET, 15, 10);
		items[3] = new Items(4, GREEN+"Usar Cascos: "+RESET+LIGHT_GREEN+"'La música es poder'"+RESET, 40, 20);
	}

	@Override
	public String toString() {
		final String RESET = "\u001B[0m";
		final String RED = "\u001B[31m";
		final String CYAN = "\u001B[36m";
		final String WHITE = "\u001B[37m";
		final String PINK = "\u001B[95m";
		final String LIGHT_GREEN = "\u001B[92m";
		final String YELLOW = "\u001B[33m";
		final String LIGHT_YELLOW = "\u001B[93m";

		return ""+YELLOW+"Alumno [ " +RESET+ LIGHT_YELLOW + "nombre=" + RESET + WHITE + nombre + RESET + LIGHT_GREEN + ", vida=" + RESET + WHITE
				+ vida + RESET + RED + ", ataque=" + RESET + WHITE + ataque + RESET + CYAN + ", defensa=" + RESET
				+ WHITE + defensa + RESET + PINK + ", curso=" + RESET + WHITE + curso + RESET + " ]";
	}
}
