package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import services.EmpService;
import vista.CodigoHTML;
import dao.EmpHiberDAO;
import dao.Recuperable;
import dominio.Employees;

/**
 * Servlet implementation class EmpleadoHibernate
 */
public class EmpleadoHibernate extends HttpServlet {

	private static Logger log = LogManager.getRootLogger();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ServletContext sC = null;
		sC = req.getServletContext();
		// SessionFactory sf = (SessionFactory) sC.getAttribute("sf");
		// Session s = sf.openSession();
		// log.info("Abro un sesión");
		// s.close();
		// log.info("Cierro la sesión");
		int c = (int) sC.getAttribute("contador");

		String in = req.getParameter("ID");

		try {

			if (in != "") {
				int ID = Integer.parseInt(in);
				EmpService eS = new EmpService();
				Recuperable Obj_recuperable1 = new EmpHiberDAO();
				eS.setRecuperable(Obj_recuperable1);
				Employees emp = (Employees) eS.LeerEmpleado(ID);
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
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
					out.println("<h4>Empleado encontrado (Hibernate)</h4>");
					out.println("<h3>Nombre: " + emp.getFirstName() + "</h3>"
							+ "<h3>Apellido: " + emp.getLastName() + "</h3>"
							+ "<h3>Salario: " + emp.getSalary() + " $</h3>");
					out.println("<h1>" + c + "</h1");
					
					CodigoHTML.pie(resp);
					
					log.info("El ID no está vacio y devuelve un empleado ");

				} else {

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
					out.println("<h4>Empleado encontrado (Hibernate)</h4>");
					out.println("<h2>Ese ID no corresponde con ningún empleado</h2>");
					out.println("<h1>" + c + "</h1");

					CodigoHTML.pie(resp);
					
					log.info("El ID no está vacio per no devuelve un empleado válido");

				}
			} else {
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();

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
				out.println("<h4>Empleado encontrado (Hibernate)</h4>");
				out.println("<h2>No introdujiste un ID válido</h2>");
				out.println("<h1>" + c + "</h1");
				
				CodigoHTML.pie(resp);
				
				log.info("El ID es una cadena vacia");

			}
		} catch (NumberFormatException n) {
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();

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
			out.println("<h4>Empleado encontrado (Hibernate)</h4>");
			out.println("<h2>No introdujiste un ID válido</h2>");
			out.println("<h1>" + c + "</h1");

			CodigoHTML.pie(resp);
			
			log.info("El ID no es un número");
		}
	}

}
