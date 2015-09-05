package listeners;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

public class EscuchaContexto implements ServletContextListener {

	private static Logger log = LogManager.getRootLogger();
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		ServletContext sC = null;
		try {
			sC = arg0.getServletContext();
			SessionFactory sf = (SessionFactory) sC.getAttribute("sf");
			if (sf!=null){sf.close();}
			
			log.info("Se ha destruido el contexto");
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("No se ha destruido el contexto");
		}
		
		log.trace("Se destruye el contexto");

		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		int peticiones = 0;
		Map <String, HttpSession> mHS = null;
		mHS = new HashMap<String, HttpSession>();
		
		//SessionFactory sf = SessionManager.getSessionFactory();
		ServletContext servletContext = null;
		servletContext = arg0.getServletContext();
		//servletContext.setAttribute("sf", sf);
		servletContext.setAttribute("contador", peticiones);
		servletContext.setAttribute("sesiones", mHS);
		log.trace("Se inicia el contexto");
	}

}
