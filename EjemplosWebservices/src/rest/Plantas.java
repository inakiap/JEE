package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import dominio.Persona;
import dominio.Planta;

@Path("/plantas")
public class Plantas {
	
	@GET
	@Path("/tipo/{hoja}")
	@Produces("application/xml")
	public String getComun(@PathParam("hoja") String inicial) {
		
		String detalle = null;
		
		if (inicial.equals("larga")) {
			detalle = "<Planta><name>Palmera</name></Planta>";
			
		} else if (inicial.equals("corta")) {
			detalle = "<Planta><name>Encina</name></Planta>";
			
		} else if (inicial.equals("estrecha")) {
			detalle = "<Planta><name>Pino</name></Planta>";
			
		} else {
			detalle = "Planta no encontrada";
		}
		return detalle;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response creaPlanta(String p) {
 
	 	Gson gson = new Gson();
	 	Planta planta = gson.fromJson(p, Planta.class);
	 	
	 	System.out.println(planta.toString());
	 	System.out.println(p.toString());
        
		String result = "Ha ido bien, chaval ;)";
		
		return Response.status(201).entity(result).build();
 
	}
	
}
