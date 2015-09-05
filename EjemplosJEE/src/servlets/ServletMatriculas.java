package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Collator;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import vista.CodigoHTML;
import dao.Matricula;

/**
 * Servlet que dada una matrícula te da su año de matriculación
 * @author inakiap
 *
 */
public class ServletMatriculas extends HttpServlet {
	
	private static Logger log = LogManager.getRootLogger();
	private static String url = "http://www.infolaso.com/sistema-actual.html";
	private static Matricula matricula = null;
	
	/**
	 * Método que imprime en el navegador el resultado de la búsqueda de la matrícula
	 * @Override
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String matricula = req.getParameter("matricula");
		Pattern p = Pattern.compile("\\d{4}[B-DF-HJ-NP-TV-Z]{3}");// funciona!!
		Matcher mat = p.matcher(matricula);
		String ruta = "matriculas.html";
		String url = "http://www.infolaso.com/sistema-actual.html";
		Matricula objmatricu = null;
		Iterator<Matricula> it = null;
		
		if (!mat.matches()){
			
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			
			CodigoHTML.cabeceraTemp(resp, ruta);

			out.println("<body>");
			out.println("<div class='container'>");
			out.println("<div class='header clearfix'>");
			out.println("<nav>");
			out.println("<ul class='nav nav-pills pull-right'>");
			out.println("<li role='presentation' class='active'><a href='matriculas.html'>Volver</a></li>");
			out.println("</ul>");
			out.println("</nav>");
			out.println("<h3 class='text-muted'>La jodida matrícula</h3>");
			out.println("</div>");
			out.println("<h2>La matricula no está bien escrita</h2>");
			
			CodigoHTML.pie(resp);
			
			
		} else {
			if(ServletMatriculas.ordenarMatriculas(matricula, "0113JGN") <= 0){
			
				String bruto = ServletMatriculas.extraerBrutoMatriculas(url);
				String aniosYmatriculas = ServletMatriculas.preProcesarCadenaAniosYmatriculas(bruto);
				
				TreeMap<String, Matricula> colecc = ServletMatriculas.postProcesoAniosYMatriculas(aniosYmatriculas);
				
				String resultado = ServletMatriculas.fecharMatricula(matricula, colecc);
				
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				
				CodigoHTML.cabecera(resp);
	
				out.println("<body>");
				out.println("<div class='container'>");
				out.println("<div class='header clearfix'>");
				out.println("<nav>");
				out.println("<ul class='nav nav-pills pull-right'>");
				out.println("<li role='presentation' class='active'><a href='matriculas.html'>Volver</a></li>");
				out.println("</ul>");
				out.println("</nav>");
				out.println("<h3 class='text-muted'>La jodida matrícula</h3>");
				out.println("</div>");
				out.println("<h2>La matricula está bien escrita, digo...</h2>");
				out.println("<h1>El año de esa matrícula es: "+ resultado +"</h1>");
				out.println("<p>La verdad es que esto es bastante jodido... incluso hay programas de pago que hacen esto"
						+". Pero es que además la DGT no da datos sobre el tema... seguiré buscando.</p>");
				
				CodigoHTML.pie(resp);
			} else{
				
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				
				CodigoHTML.cabeceraTemp(resp, ruta);

				out.println("<body>");
				out.println("<div class='container'>");
				out.println("<div class='header clearfix'>");
				out.println("<nav>");
				out.println("<ul class='nav nav-pills pull-right'>");
				out.println("<li role='presentation' class='active'><a href='matriculas.html'>Volver</a></li>");
				out.println("</ul>");
				out.println("</nav>");
				out.println("<h3 class='text-muted'>La jodida matrícula</h3>");
				out.println("</div>");
				out.println("<h2>¿Estás seguro de que la matrícula introducida es correcta?</h2>");
				out.println("<p>Para mi que te has flipado y has metido una matrícula futurista ;), vamos que no existe aún.</p>");
				
				CodigoHTML.pie(resp);
			}
		}
		
	}
	
	/**
	 * Metro que dadas dos matrículas te dice cual es la más reciente
	 * @param s1 matricula 1
	 * @param s2 matricula 2
	 * @return si la matricula 1 es menor que la 2 devuelve -1, 0 si son igual es y 1 si es mayor s1 que s2
	 */
	static int ordenarMatriculas (String s1, String s2) {
		int resultado = 55;
		
		Collator comparador = Collator.getInstance();
		
		resultado = comparador.compare(s1.substring(4), s2.substring(4));
		
		if (resultado == 0){
			int i1 = Integer.parseInt(s1.substring(0,3));
			int i2 = Integer.parseInt(s2.substring(0,3));
			
			if (i1 < i2){ 
				resultado = -1;
			} else{ 
				
				if(i1 > i2){
					
					resultado = 1;
					
				}else{
					
					resultado = 0;
				}
			}
			
		}
		
		return resultado;
	}
	
	/**
	 * Método que dada una url extrae los años y las matrículas 
	 * @param url de la página web www.infolaso.com
	 * @return devuelve una cadena con la página entera
	 */
	public static String extraerBrutoMatriculas (String url){
		String linea = "";
		String brutoAniosYmatriculas = "";
		URL ficheroUrl = null;
		BufferedReader in = null;
		
		try {
			ficheroUrl = new URL(url);
			in = new BufferedReader(new InputStreamReader(ficheroUrl.openStream()));
			while ((linea = in.readLine()) != null){
				brutoAniosYmatriculas = brutoAniosYmatriculas + linea;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			log.error("La URL es erronea, algo falta o sobra");
		} catch (IOException e1) {
			e1.printStackTrace();
			log.error("Se ha interrumpido la conexión con el servidor");
		} finally{
			   try {
				in.close();
			   } catch (IOException e) {
				e.printStackTrace();
			} 
		}
		return brutoAniosYmatriculas;
	}
	
	/**
	 * Método que partiendo del string con todo el código html de la página web
	 * nos da una cadena con los años y las matrículas y algo de ruido
	 * @param bruto cadena que le introducimos con todo el html
	 * @return cadena con solo años y matrículas
	 */
	public static String preProcesarCadenaAniosYmatriculas (String bruto){
		
		String inicioEtiqueta ="<strong>";
		String finalEtiqueta = "</strong>";
		
		String aniosYmatriculas = "";
		int posInicial= bruto.indexOf(inicioEtiqueta);
		int posFinal = bruto.indexOf(finalEtiqueta);
		
		while ((posInicial!=-1) &&(posFinal!=-1)){
			aniosYmatriculas = aniosYmatriculas + bruto.substring(posInicial+7, posFinal);
			bruto = bruto.substring(posFinal+9);
			posInicial = bruto.indexOf(inicioEtiqueta);
			posFinal = bruto.indexOf(finalEtiqueta);
		}
		return aniosYmatriculas;
	}
	
	/**
	 * Método que toma una cadena con años y matrículas + ruido y lo introduce en un hashmap de años, objetos matrícula 
	 * @param s cadena con años y matrículas + ruido
	 * @return un HashMap <String años, Objeto matrícula (año y matricula)> ordenado :D
	 */
	public static TreeMap <String, Matricula> postProcesoAniosYMatriculas (String s){
		
		String [] listaAniosYmatriculas = s.split(">", 32);
		TreeMap <String, Matricula> coleccionMatriculas = new TreeMap<String, Matricula>();
		matricula = new Matricula();
		
		for (int i= 1; 31>i; i++){
			matricula = new Matricula();
			matricula.setAnio(listaAniosYmatriculas[i]);
			i++;
			matricula.setValorMatricula(listaAniosYmatriculas[i].substring(0, 4)+listaAniosYmatriculas[i].substring(5, 8));
			coleccionMatriculas.put(matricula.getAnio(), matricula);
		}
		Matricula m = new Matricula("2015", "0113JGN");
		coleccionMatriculas.put("2015", m);
		
		return (TreeMap<String, Matricula>) coleccionMatriculas;
	}
	
	/**
	 * Método que te dice el año de la mátricula dada
	 * @param matricula Un String con la matrícula (del tipo posterior al 2000).
	 * @param listadoMatriculas Una colección con las matriculas obtenidas de la web http://www.infolaso.com/sistema-actual.html
	 * @return devuelve un String con el año de la matrícula
	 */
	public static String fecharMatricula (String matricula, TreeMap<String, Matricula> listadoMatriculas){
		
		String m1 = matricula;
		String m2 = "";
		Matricula objmatricu = null;
			
		Collection<Matricula> c = listadoMatriculas.values();
		Iterator<Matricula> it = c.iterator();
		it = c.iterator();
		do {
			objmatricu = (Matricula) it.next();
			m2 = objmatricu.getValorMatricula();
			
		} while ((ServletMatriculas.ordenarMatriculas(m1, m2) > 0));
 
		return objmatricu.getAnio();
		
	}
	
}
