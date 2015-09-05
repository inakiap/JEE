package cookies;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import vista.CodigoHTML;

public class CookieServicio3Veces extends HttpServlet{
	
	private static Logger log = LogManager.getRootLogger();
	private static String cookieName = "miCookie";
	
	/**
	 * Método para comprobar que la petición tenga mi cookie o no, si no la tiene le dará una a 0, si la tienen aumenta el valor.
	 * Si el valor excede de 2 le escribiré un comentario.
	 * @param req la petición que nos pasa la información necesaria
	 * @param cookieName 
	 * @return
	 */
	private static Cookie comprobarCookie (HttpServletRequest req, String cookieName){
		Cookie cookie = null;
		Cookie [] cookies= req.getCookies();
		Cookie cookieAux = null;
		boolean encontrada = false;
		int i = 0;
		
		if(cookies != null){ //No hay ninguna cookie en el array de cookies que me devuelve la petición
			
			while ( (i < cookies.length) && (!encontrada)) { // recorro el array
				cookieAux = cookies[i];

				if(cookieAux.getName().equals(cookieName)){ // tengo mi cookie
					
					encontrada = true;
					cookie = cookieAux;
					
				}else {
					i++;
				}
				if (!encontrada){cookie = new Cookie("miCookie", "0");}
			}
		}else {
			cookie = new Cookie("miCookie", "0");
		}
		
		return cookie;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Cookie galleta = comprobarCookie(req, cookieName);
		String rutaExceso = "index.html";
		
		if(null != galleta){
			String valorGalleta = galleta.getValue();
			int valorNumGalleta = Integer.parseInt(valorGalleta);
			
			if( valorNumGalleta <= 1){
				
				galleta.setMaxAge(60*60*24);
				valorNumGalleta++;
				galleta.setValue(valorNumGalleta + "");
				resp.addCookie(galleta);
				
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				
				CodigoHTML.cabecera(resp);
				
				out.println("<body>");
				out.println("<div class='container'>");
				out.println("<div class='header clearfix'>");
				out.println("<nav>");
				out.println("<ul class='nav nav-pills pull-right'>");
				out.println("<li role='presentation' class='active'><a href='index.html'>Volver</a></li>");
				out.println("</ul>");
				out.println("</nav>");
				out.println("<h3 class='text-muted'>Javeando</h3>");
				out.println("</div>");
				out.println("<h4>Tengo galleta</h4>");
				out.println("<h5> El valor de la galleta es: " + galleta.getValue() + "</h5>");
				
				CodigoHTML.pie(resp);
			} else {
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				
				CodigoHTML.cabeceraTemp(resp, rutaExceso);
				
				out.println("<body>");
				out.println("<div class='container'>");
				out.println("<div class='header clearfix'>");
				out.println("<nav>");
				out.println("<ul class='nav nav-pills pull-right'>");
				out.println("<li role='presentation' class='active'><a href='index.html'>Volver</a></li>");
				out.println("</ul>");
				out.println("</nav>");
				out.println("<h3 class='text-muted'>Javeando</h3>");
				out.println("</div>");
				out.println("<h4>La galleta dice que ya has estado más de dos veces por aquí</h4>");
				
				CodigoHTML.pie(resp);
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
			out.println("<li role='presentation' class='active'><a href='index.html'>Volver</a></li>");
			out.println("</ul>");
			out.println("</nav>");
			out.println("<h3 class='text-muted'>Javeando</h3>");
			out.println("</div>");
			out.println("<h4>Galleta entregada</h4>");
			
			CodigoHTML.pie(resp);
		}
			
		
	}
}
