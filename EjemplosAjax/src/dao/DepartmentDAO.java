package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dto.Departments;

public class DepartmentDAO {

	/**
	 * Método que devuelve una lista con todos los departamentos que existen en la bbdd.
	 */
	public List<Departments> leerDepartamentos() {
		Departments dep = null;
		List <Departments> lD = new ArrayList<Departments>();
		
		String bdep = "select * from departments";
		
		Connection conn = null;
		ResultSet rset = null;
		Statement busqueda = null;
		
		try
		{
			//Conexión con la bbdd utilizando la clase pool
			conn = Pool.getConnection();
  	        busqueda = conn.createStatement();
  	        rset = busqueda.executeQuery(bdep);
  	        while (rset.next()){
  	        	dep = new Departments(rset.getShort(1), rset.getString(2));
  	        	lD.add(dep);
  	        }
			
			conn.commit();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally 
		{
			Pool.liberarRecursos(conn, busqueda, rset);
		} 
		return lD;
	}
	
	public Departments leerDepartamento(int id) {
		Departments dep = null;
				
		String bdep = "select * from departments where department_id=" +id ;
		
		Connection conn = null;
		ResultSet rset = null;
		Statement busqueda = null;
		
		try
		{
			//Conexión con la bbdd utilizando la clase pool
			conn = Pool.getConnection();
  	        busqueda = conn.createStatement();
  	        rset = busqueda.executeQuery(bdep);
  	        if (rset.next()){
  	        	dep = new Departments(rset.getShort(1), rset.getString(2));
  	        }
			
			conn.commit();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally 
		{
			Pool.liberarRecursos(conn, busqueda, rset);
		} 
		return dep;
	}
}


