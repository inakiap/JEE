package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import vista.CodigoHTML;
import dao.EmpJDBCDAO;
import dominio.EmpleadoDTO;

public class EmpleadoMVC extends HttpServlet{

	private static Logger log = LogManager.getRootLogger();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		EmpleadoDTO empleado = null;
		String id = req.getParameter("ID");
		HttpSession s = req.getSession(true);
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
	
		if (id != "") {
			int in = Integer.parseInt(id); //recojo el ID del empleado del formulario y lo transformo en un entero
		//obtengo el objeto empleado asociado al id dado
			try{
				EmpJDBCDAO eS = new EmpJDBCDAO();
				empleado = (EmpleadoDTO) eS.leerEmpleado(in);
				log.trace("Obtengo el objeto empleado");
				
				if (null != empleado){
					// he obtenido un empleado valido y lo parametrizo en la sesión
					s.setAttribute("empleado", empleado);
					
				} else {
					
					CodigoHTML.cabecera(resp);
					
					out.println("<div class='container'>");
					out.println("<div class='header clearfix'>");
					out.println("<nav>");
					out.println("<ul class='nav nav-pills pull-right'>");
					out.println("<li role='presentation' class='active'><a href='empleados.html'>Volver a buscar</a></li>");
					out.println("</ul>");
					out.println("</nav>");
					out.println("<h3 class='text-muted'>Javeando</h3>");
					out.println("</div>");
					out.println("<h4>Empleado encontrado (JDBC)</h4>");
					out.println("<h2>Ese ID no corresponde con ningún empleado</h2>");
					
					CodigoHTML.pie(resp);
					
					log.info("Muestra la web con un aviso de que no encontró ningún empleado con el ID dado");
				}
				
			} catch(Exception e){
				e.printStackTrace();
				log.error("Ha fallado la conexión con la bbdd");
			}
		} else{
			
			CodigoHTML.cabecera(resp);
			
			out.println("<div class='container'>");
			out.println("<div class='header clearfix'>");
			out.println("<nav>");
			out.println("<ul class='nav nav-pills pull-right'>");
			out.println("<li role='presentation' class='active'><a href='empleados.html'>Volver a buscar</a></li>");
			out.println("</ul>");
			out.println("</nav>");
			out.println("<h3 class='text-muted'>Javeando</h3>");
			out.println("</div>");
			out.println("<h4>Empleado encontrado (JDBC)</h4>");
			out.println("<h2>Ese ID no corresponde con ningún empleado</h2>");
			
			CodigoHTML.pie(resp);
			
			log.trace("El id no se introdujo");
			
			
		}
		
		try {
			req.getRequestDispatcher("empleadoMVC.jsp").forward(req, resp);
		} catch (IOException e) {
			log.error("Falló la redirección");
			e.printStackTrace();
		}
	}
}
