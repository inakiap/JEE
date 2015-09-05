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

import services.UsersServices;
import vista.CodigoHTML;

/**
 * Servlet implementation class ServletAutentificacion
 */
public class ServletAutentificacion extends HttpServlet {
	
	
	private static Logger log = LogManager.getRootLogger();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usuario = request.getParameter("usuario");
		String pass = request.getParameter("contrasenia");
		UsersServices uS = new UsersServices();
		HttpSession hS = null;
		boolean ok = false;
		log.trace("Incializo parámetros");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		hS = request.getSession(false);

		if (null == hS) {
		
			if (usuario != "" && pass != "") {
				ok = uS.comprobarUsuario(usuario, pass);
				
				if (ok){
					hS = request.getSession(true);
					log.info("Cliente sin sesión asociada");
					hS.setAttribute("Usuario", usuario);
					hS.setAttribute("Contrasenia", pass);
					
					CodigoHTML.cabeceraTemp(response, "index.html");
					
					out.println("<body>");
					out.println("<div class='container'>");
					out.println("<div class='header clearfix'>");
					out.println("<nav>");
					out.println("<ul class='nav nav-pills pull-right'>");
					out.println("<li role='presentation' class='active'><a href='index.html'>Continuar</a></li>");
					out.println("</ul>");
					out.println("</nav>");
					out.println("<h3 class='text-muted'>Javeando</h3>");
					out.println("</div>");
					out.println("<div> ");
					out.println("<div class='jumbotron'>");
					out.println("<h2>Autentificado correctamente, ¡enhorabuena!</h2>");
					out.println("</div>");
					CodigoHTML.pie(response);
					
					log.trace("Autentificación correcta");
					log.info("Información guardada en el objeto sesion. Usuario: " + hS.getAttribute("Usuario") + " y Contraseña: " +hS.getAttribute("Contrasenia"));
					
				} else {
					CodigoHTML.cabecera(response);
					
					out.println("<body>");
					out.println("<div class='container'>");
					out.println("<div class='header clearfix'>");
					out.println("<nav>");
					out.println("<ul class='nav nav-pills pull-right'>");
					out.println("<li role='presentation' class='active'><a href='acceso.html'>Volver</a></li>");
					out.println("</ul>");
					out.println("</nav>");
					out.println("<h3 class='text-muted'>Javeando</h3>");
					out.println("</div>");
					out.println("<div class='jumbotron'>");
					out.println("<h2>No introdujiste un usuario o una contraseña válidos</h2>");
					out.println("</div>");
					
					CodigoHTML.pie(response);
					
					log.trace("Autentificación incorrecta el usuario no existe en la bbdd");
				}
				
			} else {
				CodigoHTML.cabecera(response);
				
				out.println("<body>");
				out.println("<div class='container'>");
				out.println("<div class='header clearfix'>");
				out.println("<nav>");
				out.println("<ul class='nav nav-pills pull-right'>");
				out.println("<li role='presentation' class='active'><a href='acceso.html'>Volver</a></li>");
				out.println("</ul>");
				out.println("</nav>");
				out.println("<h3 class='text-muted'>Javeando</h3>");
				out.println("</div>");
				out.println("<div class='jumbotron'>");
				out.println("<h2>No introdujiste un usuario o una contraseña válidos</h2>");
				out.println("</div>");
				CodigoHTML.pie(response);
				
				log.trace("Autentificación incorrecta datos introducidos erroneos");
	
			}
		} else {
			
			response.sendRedirect("index.html");
			log.info("Cliente con sesión asociada");
			
		}	
		
		
		
	}

}
