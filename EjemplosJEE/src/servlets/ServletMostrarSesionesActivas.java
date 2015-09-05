package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServletMostrarSesionesActivas extends HttpServlet {
	
	private static Logger log = LogManager.getRootLogger();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ServletContext sC = getServletContext();
		HashMap<String, HttpSession> hSM = (HashMap<String, HttpSession>) sC.getAttribute("sesiones");
		
		Iterator<Entry<String, HttpSession>> it = hSM.entrySet().iterator();
		Map.Entry<String, HttpSession> e = null;
		while (it.hasNext()) {
			e = it.next();
			log.trace ("Sesiones activas:");
			log.trace (e.getKey() + e.getValue().toString());
		}
		
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<h4>Sesiones Activas</h4>");
		try {
			out.println("<h5>" + e.getKey()+ "</h5>");
		} catch (Exception e2) {
			log.error("Objeto nulo?? No hay entradas = no hay sesiones");
		}
		
//		CodigoHTML.pie(resp);
		
		
	}
}
