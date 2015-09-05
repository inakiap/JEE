package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import services.EmpleadoService;

import com.google.gson.Gson;

import dao.EmpJDBCDAO;
import dominio.EmpleadoDTO;

@Path("/empleado")
public class Empleado {
	
	private static EmpleadoService eS = new EmpleadoService();
	
	@GET
	@Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmpleado(@PathParam("id") String identificador) {
	    
		Gson gson = new Gson();
		int id = Integer.parseInt(identificador);
		EmpleadoDTO e = eS.leerUnEmpleado(id);
		String empleado = gson.toJson(e);
	    return empleado;
    }
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crearEmpleado(String e) {
 
	 	Gson gson = new Gson();
	 	EmpleadoDTO emp = gson.fromJson(e, EmpleadoDTO.class);
	 	System.out.println(emp.toString());
	 
	 	System.out.println(e.toString());
        
		String result = "Se ha creado el empleado: "+emp.getF_name();
		return Response.status(201).entity(result).build();
 
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editarEmpleado(String c){
		
		String msg = "Se ha modificado el empleado";
		return Response.status(200).entity(msg).build();
		
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public Response borrarEmpleado(String c){
		
		String msg = "Se ha borrado el empleado";
		return Response.status(200).entity(msg).build();
		
	}
	
}
