package juego_app;

import java.util.Random;

public abstract class Jugador {
	
	protected int idJugador;
	protected String nombre;
	protected int vida;
	protected int ataque;
	protected int defensa;
	protected Habilidad[] habilidades;
	protected Items[] items;

	public Jugador(int idJugador, String nombre, int vida, int ataque, int defensa) {
		super();
		this.idJugador = idJugador;
		this.nombre = nombre;
		this.vida = vida;
		this.ataque = ataque;
		this.defensa = defensa;
		this.habilidades = new Habilidad[4];
		this.items = new Items[4];
		inicializarHabilidades();
		inicializarItems();
	}

	public int getIdJugador() {
		return idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}
	
	//Método para inicializar habilidades
	protected abstract void inicializarHabilidades();
	
	//Método para inicializar habilidades
	protected abstract void inicializarItems();

    // Método para seleccionar una habilidad aleatoria
    public Habilidad seleccionarHabilidad() {
        Random random = new Random();
        int indice = random.nextInt(habilidades.length);
        return habilidades[indice];
    }
    
    public Items seleccionarItem() {
        Random random = new Random();
        int indice = random.nextInt(items.length);
        return items[indice];
    }
    

	public void atacar(Jugador oponente) {
		// TODO Auto-generated method stub
		
	}

}
