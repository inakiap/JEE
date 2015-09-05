package filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FiltroJDBC implements Filter{

	private Logger log = LogManager.getRootLogger();
	
	@Override
	public void destroy() {
		log.trace("Se destruye el filtro de JDBC");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		long inicio;
		long fin;
		long total;
		
		int peticiones;
		
		ServletContext sC = null;
		sC = arg0.getServletContext();
		
		peticiones = (int) sC.getAttribute("contador");
		peticiones++;
		sC.setAttribute("contador", peticiones);
		
		inicio = System.currentTimeMillis();
		
		arg2.doFilter(arg0, arg1);
		
		fin = System.currentTimeMillis();
		
		total = fin - inicio;
		
		log.trace("Este es la duración con el servlet de con JDBC: " + total);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		log.trace("Se inicializa el filtro de JDBC");		
	}

}
