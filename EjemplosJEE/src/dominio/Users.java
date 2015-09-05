package dominio;

public class Users {
	private String usuario;
	private String contrasenia;
	
	
	public Users(){
		
	}
	
	public Users (String user, String pass){
		this.usuario = user;
		this.contrasenia = pass;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@Override
	public String toString() {
		return "Users [usuario= " + usuario + ", contrasenia= " + contrasenia
				+ "]";
	}
	
}
