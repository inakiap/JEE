package services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.UsersDAO;
import dominio.Users;

public class UsersServices {
	
	private final Logger log = LogManager.getRootLogger();
	
	private UsersDAO uDAO = new UsersDAO();
	
	public boolean comprobarUsuario (String usuario, String pass){
		boolean ok = false;
		
		Users u = null;
		
		try {
			u = (Users) uDAO.leerUsuario(usuario);
			log.trace("De la base de datos obtengo: " + u.toString());
			
		} catch (Exception e) {
			log.error("Ha habido un problema en la transacción con la BBDD");
		}
		
		if (u != null) {
			if ((u.getUsuario().equals(usuario)) && (u.getContrasenia().equals(pass))){
				ok = true;
			}
		}
		
		return ok;
		
	}
}
