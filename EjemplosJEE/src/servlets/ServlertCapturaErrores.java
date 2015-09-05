package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ServlertCapturaErrores extends HttpServlet {

	private static Logger log = LogManager.getRootLogger();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Throwable excepcion = (Throwable) req.getAttribute("javax.servlet.error.exception");
		Integer codigoHTTP = (Integer) req.getAttribute("javax.servlet.error.status_code");
		String nombreServlet = (String) req.getAttribute("javax.servlet.error.servlet_name");
		if (null == nombreServlet ){
			nombreServlet = "Desconocido";
		} 
		
		String uriPedida = (String) req.getAttribute("javax.servlet.error.request_uri");
		if (null== uriPedida ){uriPedida = "Desconocida"; } 
		
		resp.sendRedirect("error.html");
		
	}
}
