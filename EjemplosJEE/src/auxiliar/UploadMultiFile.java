package auxiliar;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UploadMultiFile extends HttpServlet{
	
	Logger log = LogManager.getRootLogger();
	
	private String extraerRuta (String contentheader)
	{
		String ruta = null;

		ruta = contentheader.substring(contentheader.indexOf("filename=")+10, contentheader.length()-1);
		
		log.trace("Ruta obtenida =" + ruta);
		return ruta;
	}
	
	//TODO hacer método que lea la extensión del archivo
	private String verExtension (String contentheader)
	{
		String extension = null;
		int finArchivo = contentheader.length();
		
		extension = contentheader.substring(contentheader.indexOf(".")+1, finArchivo);
		
		log.trace("Extensión =" + extension);
		return extension;
	}
	
	//TODO hacer método que lea los tipos mime
	
	/**
	 * Método que obtiene la cabecera del mensaje
	 * @param request
	 * @param nombre_parte
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws ServletException
	 */
	private String obtenerCabecera(HttpServletRequest request, String nombre_parte) throws IllegalStateException, IOException, ServletException{
		String cabecera = null;
	    Collection<String> headers = null;
	    Iterator<String> it = null;
	    
	    	headers = request.getPart(nombre_parte).getHeaders("content-disposition");
	    	it= headers.iterator();
	    	if (it.hasNext())
	    	{
	    		cabecera = it.next();
	    	}
	    
	    return cabecera;
	}
	
	
	private TreeSet<Object> guardarArchivo (String tipo, HttpServletRequest request, HttpServletResponse resp) throws IllegalStateException, IOException, ServletException{
		
		TreeSet<Object> resultado = new TreeSet<Object>();
		
		FileOutputStream f_salida = null;
		
		String cabecera = obtenerCabecera(request, "fResumen");
		String nombre_fichero_origen = extraerRuta (cabecera);
		String nombre_fichero_destino = getServletConfig().getInitParameter("RUTA_SUBIDA_FICHEROS")+"\\"+nombre_fichero_origen;
	
		log.trace("Entro en doPost " + getClass().getName());
		resp.setContentType(tipo);
		try (InputStream f_entrada = request.getPart("fResumen").getInputStream();) 
			{//el try con recursos () significa que lo está declarando y a la vez se cerraran solos
			 //esto se pude usar con objetos que implementen la interface autocloseable
				f_salida = new FileOutputStream(nombre_fichero_destino);
			
				byte array_intermedio[] = new byte[1024];
				int bytes_leidos = 0;
		        while ((bytes_leidos = f_entrada.read(array_intermedio)) != -1) 
		        {
		        	f_salida.write(array_intermedio, 0, bytes_leidos);
		        }
		    
		    } catch (Exception e) 
			{
		    	e.printStackTrace();
		    	log.error(e.toString());
		    } finally{
		    	try {f_salida.close();}catch(Exception e){e.printStackTrace();log.error(e.toString());}
		    }
		
		return resultado;
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		String cabecera = obtenerCabecera(request, "fResumen");
		String nombre_fichero_origen = extraerRuta (cabecera);
		String nombre_fichero_destino = getServletConfig().getInitParameter("RUTA_SUBIDA_FICHEROS")+"\\"+nombre_fichero_origen;
		ServletOutputStream out = null;
		
			log.trace("Entro en doPost " + getClass().getName());
			resp.setContentType("application/vnd.ms-powerpoint");
			out = resp.getOutputStream();
			try (InputStream f_entrada = request.getPart("fResumen").getInputStream(); FileOutputStream f_salida = new FileOutputStream(nombre_fichero_destino);) 
				{//el try con recursos () significa que lo está declarando y a la vez se cerraran solos
				 //esto se pude usar con objetos que implementen la interface autocloseable
				
					byte array_intermedio[] = new byte[1024];
					int bytes_leidos = 0;
			        while ((bytes_leidos = f_entrada.read(array_intermedio)) != -1) 
			        {
			        	out.write(array_intermedio, 0, bytes_leidos);
			        	f_salida.write(array_intermedio, 0, bytes_leidos);
			        	
			        }
			    
			    } catch (Exception e) 
				{
			    	e.printStackTrace();
			    	log.error(e.toString());
			    }
			
		
	}

}
