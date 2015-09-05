package listeners;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EscuchaPeticion implements ServletRequestListener {
	
	private final Logger log = LogManager.getRootLogger();

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		log.trace("Petición destruida");
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		
		ServletContext sC = arg0.getServletContext();
		HashMap <String, HttpSession> hM = (HashMap<String, HttpSession>) sC.getAttribute("sesiones");
		log.trace("Petición iniciada");
		log.trace("Sesiones activas= " + hM.size() );
		
	}

}
