package servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Pool;


public class CargaMenuSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargaMenuSelect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Esto no es lo más extricto, porque para conectar con la bbdd deberías usar una clase servicio que se comunicase con otra dao
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		PrintWriter out = null;
		
		try 
			{
			conexion = Pool.getConnection();
			st = conexion.createStatement();
			rs = st.executeQuery("SELECT DEPARTMENT_ID, DEPARTMENT_NAME FROM DEPARTMENTS"); // WHERE DEPARTMENT_ID IN(SELECT UNIQUE DEPARTMENT_ID FROM EMPLOYEES)");
			
			response.setContentType("text/xml");
			response.setCharacterEncoding("UTF-8");
			
			
			
			out = response.getWriter();
			
			out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); //esta es la primera linea que debe tener un xml
			
			out.println("<listadepartamentos>"); //defino un elemento raiz que lo llamo como me interesa = lista de departamentos
			while (rs.next()) //itero en el resulset para componer la lista de departamentos
			{
				System.out.println((rs.getString(1) +" " +rs.getString(2)));
				out.println("<departamento>");
				out.println("<id>"+ rs.getString(1)+"</id>");
				out.println("<nombre>"+rs.getString(2)+"</nombre>");
				out.println("</departamento>");
			}
			out.println("</listadepartamentos>");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		finally {
			Pool.liberarRecursos(conexion, st, rs);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
