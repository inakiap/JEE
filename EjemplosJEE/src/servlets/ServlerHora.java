package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import vista.CodigoHTML;

public class ServlerHora extends HttpServlet {
	
	private static Logger log = LogManager.getRootLogger();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		Date d = new Date();
		
		CodigoHTML.cabecera(resp);
		
		out.println("<body>");
		out.println("<div class='container'>");
		out.println("<div class='header clearfix'>");
		out.println("<nav>");
		out.println("<ul class='nav nav-pills pull-right'>");
		out.println("<li role='presentation' class='active'><a href='index.html'>Volver</a></li>");
		out.println("</ul>");
		out.println("</nav>");
		out.println("<h3 class='text-muted'>Javeando también te da la hora</h3>");
		out.println("</div>");
		out.println("<div class='jumbotron'>");
		out.println("<h2>" + d + "</h2>");
		out.println("</div>");
		CodigoHTML.pie(resp);

	}

	
	
}
