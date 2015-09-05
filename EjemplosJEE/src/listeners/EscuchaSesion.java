package listeners;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EscuchaSesion implements HttpSessionListener {
	
	private static Logger log = LogManager.getRootLogger();

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		log.trace("Se crea una sesión");	
		ServletContext sC = arg0.getSession().getServletContext();
		HashMap <String, HttpSession> sesiones = (HashMap<String, HttpSession>) sC.getAttribute("sesiones");
		sesiones.put(arg0.getSession().getId(), arg0.getSession());
		//sC.setAttribute("sesiones", sesiones); Al ser un objeto la modificación pasa por referencia y se "actualiza solo".
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		ServletContext sC = arg0.getSession().getServletContext();
		HashMap <String, HttpSession> sesiones = (HashMap<String, HttpSession>) sC.getAttribute("sesiones");
		HttpSession hS = sesiones.remove(arg0.getSession().getId());
		sC.setAttribute("sesiones", sesiones);
		log.trace("Sesión cerrada");
		
	}

}