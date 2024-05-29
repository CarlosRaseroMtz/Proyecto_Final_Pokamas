package juego_app;

/**
 * La clase Items representa un ítem en el juego, que puede tener un efecto en 
 * el poder y la defensa de los jugadores.
 */
public class Items {

    private int idItem;
    private String nombre;
    private int poder;
    private int mejoraCombate;

    /**
     * Constructor que inicializa un objeto Items con todos los atributos.
     * 
     * @param idItem El ID del ítem.
     * @param nombre El nombre del ítem.
     * @param poder El poder del ítem.
     * @param mejoraCombate La mejora en combate proporcionada por el ítem.
     */
    public Items(int idItem, String nombre, int poder, int mejoraCombate) {
        this.idItem = idItem;
        this.nombre = nombre;
        this.poder = poder;
        this.mejoraCombate = mejoraCombate;
    }

    /**
     * Constructor que inicializa un objeto Items sin el ID del ítem.
     * 
     * @param nombre El nombre del ítem.
     * @param poder El poder del ítem.
     * @param mejoraCombate La mejora en combate proporcionada por el ítem.
     */
    public Items(String nombre, int poder, int mejoraCombate) {
        this.nombre = nombre;
        this.poder = poder;
        this.mejoraCombate = mejoraCombate;
    }

    /**
     * Obtiene el ID del ítem.
     * 
     * @return El ID del ítem.
     */
    public int getIdItem() {
        return idItem;
    }

    /**
     * Establece el ID del ítem.
     * 
     * @param idItem El ID del ítem.
     */
    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    /**
     * Obtiene el nombre del ítem.
     * 
     * @return El nombre del ítem.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del ítem.
     * 
     * @param nombre El nombre del ítem.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el poder del ítem.
     * 
     * @return El poder del ítem.
     */
    public int getPoder() {
        return poder;
    }

    /**
     * Establece el poder del ítem.
     * 
     * @param poder El poder del ítem.
     */
    public void setPoder(int poder) {
        this.poder = poder;
    }

    /**
     * Obtiene la mejora en combate del ítem.
     * 
     * @return La mejora en combate del ítem.
     */
    public int getMejoraCombate() {
        return mejoraCombate;
    }

    /**
     * Establece la mejora en combate del ítem.
     * 
     * @param mejoraCombate La mejora en combate del ítem.
     */
    public void setMejoraCombate(int mejoraCombate) {
        this.mejoraCombate = mejoraCombate;
    }

    /**
     * Usa el ítem, aplicando sus efectos al jugador objetivo.
     * 
     * @param atacante El jugador que usa el ítem.
     * @param objetivo El jugador objetivo sobre el que se aplica el ítem.
     */
    public void usarItem(Jugador atacante, Jugador objetivo) {
        objetivo.setVida(objetivo.getVida() + this.poder);
        objetivo.setDefensa(objetivo.getDefensa() + this.mejoraCombate);
    }
}
