package filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EjemploFiltros implements Filter {
	
	private Logger log = LogManager.getRootLogger();

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		log.trace("Se destruye el filtro de ejemplo");
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		long pre = 0;
		long post = 0;
		long total = 0;
		
		pre = System.currentTimeMillis();
		arg2.doFilter(arg0, arg1);
		post = System.currentTimeMillis();
		total = post - pre;
		log.info("Tiempo total de ejecución de la petición: " + total + " ms");
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		log.trace("Se inicia el filtro de ejemplo");
	}

}
