package webservice;

public class Jugador {

	private String nombre;
	private float tiempo;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getTiempo() {
		return tiempo;
	}
	public void setTiempo(float tiempo) {
		tiempo /=100;
		this.tiempo = tiempo;
	}
	
	public Jugador(){
		
	}
}
