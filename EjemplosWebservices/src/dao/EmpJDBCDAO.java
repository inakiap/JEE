package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dominio.EmpleadoDTO;

public class EmpJDBCDAO {

	public List <EmpleadoDTO> leerTodosEmpleados (int n){
		List <EmpleadoDTO> empleados = new ArrayList<EmpleadoDTO>();
		EmpleadoDTO empleado = null;

		String busquedaEmpleado = "select * from employees where department_id = ?";
		
		Connection conn = null;
		ResultSet rset = null;
		PreparedStatement busqueda = null;
		try
		{
			conn = Pool.getConnection();
  	        busqueda = conn.prepareStatement(busquedaEmpleado);
  	        busqueda.setInt(1, n);
  	        rset = busqueda.executeQuery();
  	        
  	        while (rset.next()){
  	        	empleado = new EmpleadoDTO(rset.getInt(1),
						rset.getString(2),
						rset.getString(3),
						rset.getString(4),
						rset.getString(5),
						rset.getString(6),
						rset.getString(7),
						rset.getString(8),
						rset.getString(9),
						rset.getString(10),
						rset.getString(11));
  	        	
  	        	empleados.add(empleado);
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
		return empleados;
		
	}
	
	public EmpleadoDTO leerEmpleado(int n) {
		
		EmpleadoDTO empleado = null;
		
		String busquedaEmpleado = "select * from employees where employee_id = ?";
		
		Connection conn = null;
		ResultSet rset = null;
		PreparedStatement busqueda = null;
		
		try
		{
			conn = Pool.getConnection();
  	        busqueda = conn.prepareStatement(busquedaEmpleado);
  	        busqueda.setInt(1, n);
  	        rset = busqueda.executeQuery();
  	        if (rset.next()){
  	        	empleado = new EmpleadoDTO(rset.getInt(1),
						rset.getString(2),
						rset.getString(3),
						rset.getString(4),
						rset.getString(5),
						rset.getString(6),
						rset.getString(7),
						rset.getString(8),
						rset.getString(9),
						rset.getString(10),
						rset.getString(11));
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
		return empleado;
	}
}
