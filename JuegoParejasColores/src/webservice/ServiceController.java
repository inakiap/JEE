package webservice;

import java.awt.image.RescaleOp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ServiceController {

	//crear listado
	private static List<Jugador> listado = new ArrayList<Jugador>();

	@RequestMapping (path="/tiempofinal", produces = "application/json")
	public ResponseEntity <List<Jugador>> resultadoJugador (@ModelAttribute(value = "jugador") Jugador jj) {
		
		System.out.println(jj.getNombre() +" y "+ jj.getTiempo());
		
		listado.add(jj);
		
		return new ResponseEntity<List<Jugador>>(listado, HttpStatus.OK);
	}
}
