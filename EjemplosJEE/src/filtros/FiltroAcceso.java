package filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FiltroAcceso implements Filter {
	
	private Logger log = LogManager.getRootLogger();
	static String rutaLogin1 = "/JaveandoWebs/acceso.html";
	static String rutaLogin2 = "/JaveandoWebs/autentificacion";
	static String index = "/JaveandoWebs/index.html";
	static String inicio = "/JaveandoWebs/";
	static String recursos = "/JaveandoWebs/res/";
	static String matriculas = "/JaveandoWebs/matriculas.html";
	static String smatricula = "/JaveandoWebs/matricula";
	static String cookie3usos = "/JaveandoWebs/cookie3usos";
	

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession hS = req.getSession(false);
		String ruta = req.getRequestURI();
		
		log.trace("La ruta con la que viene " + ruta);

		if (null != hS) {
			
			chain.doFilter(req, resp);
			
			log.trace("Cliente con sesión");
			
		} else {
			log.trace("Dónde vas?");
			if(ruta.equals(inicio) || ruta.equals(rutaLogin1) || ruta.equals(rutaLogin2)
					|| ruta.equals(index) || ruta.contains(recursos) || ruta.equals(matriculas)
					|| ruta.equals(smatricula) || ruta.equals(cookie3usos)) {			
				chain.doFilter(req, resp);
				log.trace("A inicio o a acceso");
				
			} else {
				resp.sendRedirect(rutaLogin1);
				log.trace("Le redirecciono a acceso");
			}
			
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
