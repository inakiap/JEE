package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import vista.CodigoHTML;
import dao.EmpJDBCDAO;
import dominio.EmpleadoDTO;

public class EmpleadoServlet extends HttpServlet {

	private static Logger log = LogManager.getRootLogger();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String in = req.getParameter("ID");
		ServletContext sC = null;
		sC = req.getServletContext();
		int c = (int) sC.getAttribute("contador");
		
		try {
		
		if (in != "") {
			int ID = Integer.parseInt(in);
			EmpJDBCDAO eDAO = new EmpJDBCDAO();
			EmpleadoDTO emp = (EmpleadoDTO) eDAO.leerEmpleado(ID);
			log.trace("Inicializa los DAO y DTO");

			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			
			ServletConfig conf=getServletConfig();
			String s  =conf.getInitParameter("aleluya");
			log.trace("El servlet config parameter que metí en aleluya es: " + s);
			log.trace("El servlet context parameter de hora es: " + getServletContext().getInitParameter("Hora"));

			if (emp != null) {

				CodigoHTML.cabecera(resp);
				
				out.println("<body>");
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
				out.println("<h3>Nombre: " + emp.getF_name() + "</h3>"
						+ "<h3>Apellido: " + emp.getL_name() + "</h3>"
						+ "<h3>Salario: " + emp.getSalary() + " $</h3>");
				out.println("<h1>" + c + "</h1");

				CodigoHTML.pie(resp);
				
				log.info("Muestra la web con el resultado de la búsqueda");
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
				out.println("<h1>" + c + "</h1");
				CodigoHTML.pie(resp);
				log.info("Muestra la web con un aviso de que no encontró ningún empleado con el ID dado");
			}
		} else {
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
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
			out.println("<h2>No introdujiste un ID válido</h2>");
			out.println("<h1>" + c + "</h1");
			CodigoHTML.pie(resp);
			log.info("El ID es una cadena vacia");
		}
		} catch (NumberFormatException n) {
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			CodigoHTML.cabecera(resp);			out.println("<div class='container'>");
			out.println("<div class='header clearfix'>");
			out.println("<nav>");
			out.println("<ul class='nav nav-pills pull-right'>");
			out.println("<li role='presentation' class='active'><a href='empleados.html'>Volver a buscar</a></li>");
			out.println("</ul>");
			out.println("</nav>");
			out.println("<h3 class='text-muted'>Javeando</h3>");
			out.println("</div>");
			out.println("<h4>Empleado encontrado (JDBC)</h4>");
			out.println("<h2>No introdujiste un ID válido</h2>");
			out.println("<h1>" + c + "</h1");
			CodigoHTML.pie(resp);
			log.info("El ID no es un número");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

}
