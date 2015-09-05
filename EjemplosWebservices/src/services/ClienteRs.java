package services;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import dominio.EmpleadoDTO;


@XmlRootElement(name="persona")
public class ClienteRs {
	
	
	public static void main(String[] args) {
		
		//Ejemplo get empleado
		Client client = Client.create();
		Gson gson = new Gson();
		WebResource wr = client.resource("http://localhost:8090/EjemplosWebservices/rest/empleado/121");
		String resultado = wr.get(String.class);
		EmpleadoDTO emp = gson.fromJson(resultado, EmpleadoDTO.class);
		System.out.println(emp.toString());
		
		//Ejemplo post empleado
//		Client client = Client.create();
//		WebResource wr = client.resource("http://localhost:8090/RESTful/rest/empleado/");
//		Gson gson = new Gson();
//		EmpleadoDTO emp1 = new EmpleadoDTO(55, "Juan", "Perez", "ig@a.es", "96858748", "12/12/12", "55", "5000", "5", "5", "6");
//		
//		ClientResponse response = wr.type("application/json").post(ClientResponse.class, gson.toJson(emp1));
//		if (response.getStatus() != 201) {
//			throw new RuntimeException("Failed : HTTP error code : "
//					+ response.getStatus());
//		}
//		System.out.println("Respuesta del servidor .... \n");
//		String output = response.getEntity(String.class);
//		System.out.println(output);
		
	}

}
