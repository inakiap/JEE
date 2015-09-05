package dominio;

public class Planta {

	private String NombreComun;
	private String NombreCientifico;
	private String tipo;
	private String distribucion;
	
	public Planta(String nombreComun, String nombreCientifico, String tipo,
			String distribucion) {
		super();
		NombreComun = nombreComun;
		NombreCientifico = nombreCientifico;
		this.tipo = tipo;
		this.distribucion = distribucion;
	}
	
	public Planta(){
		
	}

	public String getNombreComun() {
		return NombreComun;
	}

	public void setNombreComun(String nombreComun) {
		NombreComun = nombreComun;
	}

	public String getNombreCientifico() {
		return NombreCientifico;
	}

	public void setNombreCientifico(String nombreCientifico) {
		NombreCientifico = nombreCientifico;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDistribucion() {
		return distribucion;
	}

	public void setDistribucion(String distribucion) {
		this.distribucion = distribucion;
	}
	
}
