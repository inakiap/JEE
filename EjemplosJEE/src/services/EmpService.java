package services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.Recuperable;



public class EmpService {
	
	private final Logger log = LogManager.getRootLogger();
	private Recuperable obj_DAO;
	
	/**
	 * Método que establece el objeto recuperable que va a trabajar con la bbdd
	 * @param r hay que introducir un objeto de la interface Recuperable
	 */
	public void setRecuperable (Recuperable r){
		this.obj_DAO = r;
		log.info("Método setRecuperable utilizado con un objeto del interfaz Recuperable");
		
	}
	
	/**
	 * Método que devuelve un empleado
	 * @param n introducir un int con el valor del identificador del empleado
	 * @return devuelve un objeto empleado
	 */
	public Object LeerEmpleado (int n){
		Object obj = obj_DAO.leerEmpleado(n);
		log.info( "Método que devuelve un objeto del tipo empleado a partir de un ID");
		return obj;
	}
	
	
	

}
