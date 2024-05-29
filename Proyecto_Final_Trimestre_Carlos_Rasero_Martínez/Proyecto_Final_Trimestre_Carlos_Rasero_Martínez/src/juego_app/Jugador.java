package juego_app;

import java.util.Random;

/**
 * La clase Jugador representa un jugador en el juego. Esta clase es abstracta 
 * y debe ser extendida por clases específicas de tipos de jugadores.
 */
public abstract class Jugador {
    
    protected int idJugador;
    protected String nombre;
    protected int vida;
    protected int ataque;
    protected int defensa;
    protected Habilidad[] habilidades;
    protected Items[] items;

    /**
     * Constructor para crear un jugador con los atributos especificados.
     *
     * @param idJugador el ID del jugador.
     * @param nombre el nombre del jugador.
     * @param vida la cantidad de vida del jugador.
     * @param ataque la cantidad de ataque del jugador.
     * @param defensa la cantidad de defensa del jugador.
     */
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

    /**
     * Obtiene el ID del jugador.
     *
     * @return el ID del jugador.
     */
    public int getIdJugador() {
        return idJugador;
    }

    /**
     * Establece el ID del jugador.
     *
     * @param idJugador el nuevo ID del jugador.
     */
    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return el nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del jugador.
     *
     * @param nombre el nuevo nombre del jugador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la cantidad de vida del jugador.
     *
     * @return la cantidad de vida del jugador.
     */
    public int getVida() {
        return vida;
    }

    /**
     * Establece la cantidad de vida del jugador.
     *
     * @param vida la nueva cantidad de vida del jugador.
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /**
     * Obtiene la cantidad de ataque del jugador.
     *
     * @return la cantidad de ataque del jugador.
     */
    public int getAtaque() {
        return ataque;
    }

    /**
     * Establece la cantidad de ataque del jugador.
     *
     * @param ataque la nueva cantidad de ataque del jugador.
     */
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    /**
     * Obtiene la cantidad de defensa del jugador.
     *
     * @return la cantidad de defensa del jugador.
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     * Establece la cantidad de defensa del jugador.
     *
     * @param defensa la nueva cantidad de defensa del jugador.
     */
    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    /**
     * Inicializa las habilidades del jugador. Este método debe ser implementado 
     * por las clases que extienden Jugador.
     */
    protected abstract void inicializarHabilidades();

    /**
     * Inicializa los ítems del jugador. Este método debe ser implementado 
     * por las clases que extienden Jugador.
     */
    protected abstract void inicializarItems();

    /**
     * Selecciona una habilidad aleatoria del jugador.
     *
     * @return una habilidad aleatoria.
     */
    public Habilidad seleccionarHabilidad() {
        Random random = new Random();
        int indice = random.nextInt(habilidades.length);
        return habilidades[indice];
    }
    
    /**
     * Selecciona un ítem aleatorio del jugador.
     *
     * @return un ítem aleatorio.
     */
    public Items seleccionarItem() {
        Random random = new Random();
        int indice = random.nextInt(items.length);
        return items[indice];
    }

    /**
     * Realiza un ataque a otro jugador. Este método debe ser implementado 
     * por las clases que extienden Jugador.
     *
     * @param oponente el jugador oponente.
     */
    public void atacar(Jugador oponente) {
        // Implementación específica del ataque en las subclases.
    }
}
