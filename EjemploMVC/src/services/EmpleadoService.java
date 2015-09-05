package services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.EmpJDBCDAO;
import dominio.EmpleadoDTO;


public class EmpleadoService {
	
	private final Logger log = LogManager.getRootLogger();
	private EmpJDBCDAO eDAO = new EmpJDBCDAO();
	
	public List<EmpleadoDTO> listadoEmpleadosDepartamento (int n){
		List<EmpleadoDTO> empleados = eDAO.leerTodosEmpleados(n);
		log.info( "Método que devuelve un objeto del tipo empleado a partir de un ID");
		return empleados;
	}
	
	
	

}
