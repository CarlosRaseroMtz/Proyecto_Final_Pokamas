package juego_app;

import java.util.Random;

public class Padre extends Jugador {

    private String sexo;
    
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
     * Crea un nuevo objeto Padre con el ID del jugador, nombre, vida, ataque, defensa y sexo especificados.
     * 
     * @param idJugador El ID del jugador
     * @param nombre El nombre del jugador
     * @param vida La vida del jugador
     * @param ataque El valor de ataque del jugador
     * @param defensa El valor de defensa del jugador
     * @param sexo El sexo del padre ('padre' o 'madre')
     * @throws IllegalArgumentException Si el sexo no es 'padre' o 'madre'
     */
    public Padre(int idJugador, String nombre, int vida, int ataque, int defensa, String sexo) {
        super(idJugador, nombre, vida, ataque, defensa);
        if (sexo.equalsIgnoreCase("padre") || sexo.equalsIgnoreCase("madre")) {
            this.sexo = sexo.toLowerCase();
        } else {
            throw new IllegalArgumentException("El sexo debe ser 'padre' o 'madre'.");
        }
    }

    /**
     * Devuelve el sexo del jugador.
     * 
     * @return El sexo del jugador ('padre' o 'madre')
     */
    public String getSexo() {
        return sexo;
    }

    
    /**
     * Establece el sexo del jugador.
     * 
     * @param sexo El sexo del jugador ('padre' o 'madre')
     * @throws IllegalArgumentException si el sexo no es 'padre' o 'madre'
     */
    public void setSexo(String sexo) {
        if (sexo.equalsIgnoreCase("padre") || sexo.equalsIgnoreCase("madre")) {
            this.sexo = sexo.toLowerCase();
        } else {
            throw new IllegalArgumentException("El sexo debe ser 'padre' o 'madre'.");
        }
    }
    
    /**
     * Realiza un ataque contra un jugador oponente utilizando una habilidad y, opcionalmente, un item.
     * La probabilidad de usar un item en cada ataque es del 30%.
     * 
     * @param oponente El jugador oponente que será atacado.
     */
	@Override
	public void atacar(Jugador oponente) {
		Habilidad habilidad = seleccionarHabilidad();
		Items items = seleccionarItem();
		Random random = new Random();
		double probabilidad = random.nextDouble();
		System.out.println(nombre + " utiliza la habilidad " + habilidad.getNombre()+" contra "+oponente.getNombre());
		habilidad.aplicarEfecto(this, oponente);
		habilidad.aplicarEfectoTipoProfesor(this, oponente);
		if (probabilidad <= 0.30) {
			items.usarItem(this, oponente);
			System.out.println("Además usó: ");
			System.out.println(nombre + " utiliza el item " +items.getNombre());
		}
	}

	/**
	 * Inicializa las habilidades específicas del jugador.
	 * Cada habilidad se inicializa con un identificador único, un nombre, un mensaje de descripción,
	 * un valor de ataque, un valor de defensa y una probabilidad de crítico.
	 */
	@Override
	protected void inicializarHabilidades() {
		habilidades[0] = new Habilidad(1, GREEN+"Elocuencia: "+RESET+LIGHT_GREEN+"'Se lo camela básicamente'"+RESET, 20, 0, 0.20); // 20% probabilidad de crítico
		habilidades[1] = new Habilidad(2, GREEN+"Indignacion: "+RESET+LIGHT_GREEN+"'Se indigna tanto que le dices vale.'"+RESET, 20, 0, 0.30); // 40% probabilidad de crítico
		habilidades[2] = new Habilidad(3, GREEN+"Ser Del AMPA: "+RESET+LIGHT_GREEN+"'Prácticamente tiene acciones del colegio'"+RESET, 25, 3, 0.60); // 45% probabilidad de crítico
		habilidades[3] = new Habilidad(4, GREEN+"Justificar a mi hijo: "+RESET+LIGHT_GREEN+"'Mi hijo ese dia estaba malo, lo juro.'"+RESET, 15, -3, 0.30); // 60% probabilidad de crítico
	}
	
	/**
	 * Inicializa los elementos del array de items del jugador con valores predefinidos.
	 * Los valores predefinidos incluyen varios elementos con diferentes características.
	 * 
	 * Cada elemento del array de items se inicializa con un ID, un nombre, un poder,
	 * y una mejora de combate.
	 */
	@Override
	protected void inicializarItems() {
		items[0] = new Items(1, GREEN+"Beber Cerveza: "+RESET+LIGHT_GREEN+"'Además muy fría'"+RESET, 25, 20);
		items[1] = new Items(2, GREEN+"Ticket exlusivo del AMPA: "+RESET+LIGHT_GREEN+"'Recuperas toda la salud, aumenta tu ataque y defensa mucho, desde ahora todos te respetan.'"+RESET, 100, 20);
		items[2] = new Items(3, GREEN+"Tomar una tapita en el bar: "+RESET+LIGHT_GREEN+"'Además una cerveza'"+RESET, 35, 30);
		items[3] = new Items(4, GREEN+"Usa la chancla: "+RESET+LIGHT_GREEN+"'Se pueden hacer muchas cosas con una chancla'"+RESET, 10, 20);
	}

	/**
	 * Devuelve una representación en forma de cadena de texto del objeto Padre.
	 * 
	 * @return Una cadena que representa al objeto Padre, con detalles sobre su nombre, vida, ataque, defensa y sexo.
	 */
	@Override
	public String toString() {
		return ""+YELLOW+"Padre [ " +RESET+ LIGHT_YELLOW + "nombre=" + RESET + WHITE + nombre + RESET + LIGHT_GREEN + ", vida=" + RESET + WHITE
				+ vida + RESET + RED + ", ataque=" + RESET + WHITE + ataque + RESET + CYAN + ", defensa=" + RESET
				+ WHITE + defensa + RESET + PINK + ", sexo=" + RESET + WHITE + sexo + RESET + " ]";
	}
}
