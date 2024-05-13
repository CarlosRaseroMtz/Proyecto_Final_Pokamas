package juego_app;

import java.util.Random;

public class Padre extends Jugador {

    private String sexo;

    public Padre(int idJugador, String nombre, int vida, int ataque, int defensa, String sexo) {
        super(idJugador, nombre, vida, ataque, defensa);
        if (sexo.equalsIgnoreCase("padre") || sexo.equalsIgnoreCase("madre")) {
            this.sexo = sexo.toLowerCase(); // Almacenamos en minúsculas para consistencia
        } else {
            throw new IllegalArgumentException("El sexo debe ser 'padre' o 'madre'.");
        }
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        if (sexo.equalsIgnoreCase("padre") || sexo.equalsIgnoreCase("madre")) {
            this.sexo = sexo.toLowerCase();
        } else {
            throw new IllegalArgumentException("El sexo debe ser 'padre' o 'madre'.");
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
		habilidades[0] = new Habilidad(1, GREEN+"Elocuencia: "+RESET+LIGHT_GREEN+"'Se lo camela básicamente'"+RESET, 20, 0, 0.20); // 20% probabilidad de crítico
		habilidades[1] = new Habilidad(2, GREEN+"Indignacion: "+RESET+LIGHT_GREEN+"'Se indigna tanto que le dices vale.'"+RESET, 20, 0, 0.30); // 40% probabilidad de crítico
		habilidades[2] = new Habilidad(3, GREEN+"Ser Del AMPA: "+RESET+LIGHT_GREEN+"'Prácticamente tiene acciones del colegio'"+RESET, 25, 3, 0.60); // 45% probabilidad de crítico
		habilidades[3] = new Habilidad(4, GREEN+"Justificar a mi hijo: "+RESET+LIGHT_GREEN+"'Mi hijo ese dia estaba malo, lo juro.'"+RESET, 15, -3, 0.30); // 60% probabilidad de crítico
	}
	
	@Override
	protected void inicializarItems() {
		final String RESET = "\u001B[0m";
		final String GREEN = "\u001B[32m";
		final String LIGHT_GREEN = "\u001B[92m";
		items[0] = new Items(1, GREEN+"Beber Cerveza: "+RESET+LIGHT_GREEN+"'Además muy fría'"+RESET, 25, 20);
		items[1] = new Items(2, GREEN+"Ticket exlusivo del AMPA: "+RESET+LIGHT_GREEN+"'Recuperas toda la salud, aumenta tu ataque y defensa mucho, desde ahora todos te respetan.'"+RESET, 100, 20);
		items[2] = new Items(3, GREEN+"Tomar una tapita en el bar: "+RESET+LIGHT_GREEN+"'Además una cerveza'"+RESET, 35, 30);
		items[3] = new Items(4, GREEN+"Usa la chancla: "+RESET+LIGHT_GREEN+"'Se pueden hacer muchas cosas con una chancla'"+RESET, 10, 20);
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

		return ""+YELLOW+"Padre [ " +RESET+ LIGHT_YELLOW + "nombre=" + RESET + WHITE + nombre + RESET + LIGHT_GREEN + ", vida=" + RESET + WHITE
				+ vida + RESET + RED + ", ataque=" + RESET + WHITE + ataque + RESET + CYAN + ", defensa=" + RESET
				+ WHITE + defensa + RESET + PINK + ", sexo=" + RESET + WHITE + sexo + RESET + " ]";
	}

}
