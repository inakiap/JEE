package servlets;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * La primera clase hecha con el JEE
 * @author Iñaki AP
 *
 */
public class Clase1 extends HttpServlet {
	
	private static Logger log = LogManager.getRootLogger();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		log.trace("doGet del servlet de la clase1");
		//super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		int letras = nombre.length();
		out.println("Número de letras de la palabra " + nombre + " es = " + letras);
		
		System.out.println("doPost");
		//super.doPost(req, resp);
	}
	
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		log.trace("service");
		super.service(arg0, arg1);
	}
	
}
