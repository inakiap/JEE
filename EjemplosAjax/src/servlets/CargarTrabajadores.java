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


public class CargarTrabajadores extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarTrabajadores() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		PrintWriter out = null;
		
		String department_id = null;;

		try 
			{
			
			
			department_id = request.getParameter("dpto");
			conexion = Pool.getConnection();
			st = conexion.createStatement();
			rs = st.executeQuery("select first_name from employees where department_id = " + department_id);
			
			
			response.setContentType("text/xml");
			response.setCharacterEncoding("UTF-8");
			
			out = response.getWriter();
			
			out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			
			out.println("<empleados>");
			
			while (rs.next())
			{
				System.out.println(rs.getString(0) + " "+ rs.getString(1) + " " + rs.getString(2) + " "+ rs.getString(3)+
						" " + rs.getString(7));
				
				out.println("<empleado>");
				out.println("<id>"+ rs.getString(0)+"</id>");
				out.println("<nombre>"+ rs.getString(1)+"</nombre>");
//				out.println("<apellido>"+ rs.getString(2)+"</apellido>");
//				out.println("<email>"+ rs.getString(3)+"</email>");
//				out.println("<salario>"+ rs.getString(7)+"</salario>");
				out.println("</empleado>");
			}
			out.println("</empleados>");
			
			
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
