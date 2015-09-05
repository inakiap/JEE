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

public class ServletCerrarSesion extends HttpServlet {
	
	private Logger log = LogManager.getRootLogger();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session!=null){
			session.invalidate();
			log.trace("Sesion cerrada");
		}
		
		resp.setContentType("text/http");
		PrintWriter out = resp.getWriter();
		CodigoHTML.cabeceraTemp(resp, "index.html");
		
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
		out.println("<h2>Sesión cerrada, ¡hasta la próxima!</h2>");
		out.println("</div>");
		
		CodigoHTML.pie(resp);
		
	}
}
