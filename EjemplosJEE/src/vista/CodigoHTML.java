package vista;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CodigoHTML {
	
	private static Logger log = LogManager.getRootLogger();

	
	public static Object pie (HttpServletResponse resp) throws IOException{
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter(); 
		
		
		out.println("<footer class='footer'>");
		out.println("<p>Página web realizada por <a href='http://codigonatura.com' target='_blank'><img src='http://codigonatura.com/wp-content/uploads/2014/10/cn-logo1-e1413870836327.png' width='100px' style='vertical-align:middle;'></a></p>");
		out.println("</footer>");
		out.println("</div>");
		out.println("<script src='res/js/jquery.js'></script>");
		out.println("</body>");
		out.println("</html>");
		
		log.trace("Imprime el pie de página");
		
		return out;
		
	}
	
public static Object cabecera (HttpServletResponse resp) throws IOException{
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter(); 
		
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='utf-8'>");
		out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
		out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
		out.println("<link rel='stylesheet' href='res/css/bootstrap.min.css'>");
		out.println("<link href='res/css/style.css' rel='stylesheet'>");
		out.println("<script src='res/js/bootstrap.min.js'></script>");
		out.println("<title>Javeando Web</title>");
		out.println("</head>");
		
		log.trace("Imprime la cabecera");
		
		return out;
		
	}
	public static Object cabeceraTemp (HttpServletResponse resp, String ruta) throws IOException{
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter(); 
		
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='utf-8'>");
		out.println("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
		out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
		out.println("<meta http-equiv='refresh' content='3;URL=" + ruta + "'>");

		out.println("<link rel='stylesheet' href='res/css/bootstrap.min.css'>");
		out.println("<link href='res/css/style.css' rel='stylesheet'>");
		out.println("<script src='res/js/bootstrap.min.js'></script>");
		out.println("<title>Javeando Web</title>");
		out.println("</head>");
		
		log.trace("Imprime la cabecera");
		
		return out;
		
	}

}
