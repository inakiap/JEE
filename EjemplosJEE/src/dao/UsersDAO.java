package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dominio.Users;

public class UsersDAO {
	
	private static Logger log = LogManager.getRootLogger();
	
	public Object leerUsuario (String name){
		
		Users u = null;
		
		String sql = "select * from users where user_name = ?";
		PreparedStatement busqueda = null;
		Connection conn = null;
		ResultSet rset = null;
		log.trace("Incializadas las variables y va a hacer la búsqueda");
		
		try
		{
			conn = Pool.getConnection();
  	        busqueda = conn.prepareStatement(sql);
  	        busqueda.setString(1, name);
  	        rset = busqueda.executeQuery();
  	        if (rset.next()){
  	        	u = new Users(rset.getString(1), rset.getString(2));
  	        }
  	        
  	        log.trace("Se ha hecho la búsqueda en la BBDD con éxito y tengo el usuario " + u.toString());
			conn.commit();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error("Hubo un problema en la transacción con la BBDD");
			
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
		
		
		return u;
	}
	

}
