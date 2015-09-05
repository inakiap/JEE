package services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.DepartmentDAO;
import dominio.Departments;

public class DepartamentoServices {
	
	private final Logger log = LogManager.getRootLogger();
	private DepartmentDAO dDAO = new DepartmentDAO();
	
	public List<Departments> todosLosDepartamentos(){
		List<Departments> deps = dDAO.leerDepartamentos();
		return deps;
	}
	
	public Departments leerUnDepartamento (int n){
		Departments d = dDAO.leerDepartamento(n);
		return d;
	}

}
