package juego_app;

public class Items {

	private int idHabilidad;
	private String nombre;
	private int poder;
	private int mejoraCombate;

	public Items(int idHabilidad, String nombre, int poder, int mejoraCombate) {
		this.idHabilidad = idHabilidad;
		this.nombre = nombre;
		this.poder = poder;
		this.mejoraCombate = mejoraCombate;
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
		return mejoraCombate;
	}

	public void setPenalizacionDefensa(int penalizacionDefensa) {
		this.mejoraCombate = penalizacionDefensa;
	}

	public int getMejoraCombate() {
		return mejoraCombate;
	}

	public void setMejoraCombate(int mejoraCombate) {
		this.mejoraCombate = mejoraCombate;
	}

	public void usarItem(Jugador atacante, Jugador objetivo) {
		objetivo.setVida(objetivo.getVida() + this.poder);
		objetivo.setDefensa(objetivo.getDefensa() + this.mejoraCombate);
	}

}