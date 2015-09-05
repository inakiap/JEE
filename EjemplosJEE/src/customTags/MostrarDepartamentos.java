package customTags;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import dao.EmpJDBCDAO;
import dominio.EmpleadoDTO;

public class MostrarDepartamentos extends SimpleTagSupport{
	
	private int id;


	//TODO Hacer una etiqueta que tomando como atributo el department id nos devuelva el listado de los empleados
	// <mitag:departamentos id="80"> </mitag.depatamentos>
	
	@Override
	public void doTag() throws JspException, IOException {
		//consulta
		EmpJDBCDAO eJDBC = new EmpJDBCDAO();
		EmpleadoDTO e = new EmpleadoDTO();
		List <EmpleadoDTO> list = eJDBC.leerTodosEmpleados(id);
		
		// mostrar
		Iterator<EmpleadoDTO> it = list.iterator();
		getJspContext().getOut().println("<table>");

		getJspContext().getOut().println("<thead> <tr> <th>Identificador</th><th>Nombre</th> <th>Apellido</th><th>e-mail</th> <th>Teléfono</th> <th>Salario</th> </tr> </thead>");
		getJspContext().getOut().println("<tbody>");
		while (it.hasNext()) {
			e = it.next();
			getJspContext().getOut().println("<tr><td>"+ e.getId()+"</td><td>"+e.getF_name()+"</td><td>"+e.getL_name()+"</td><td>"+e.getEmail()+"</td><td>"+e.getN_tlf()+"</td><td>"+e.getSalary()+"</td><td>");
		}
		getJspContext().getOut().println("</tbody>");
		getJspContext().getOut().println("</table>");
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	
}
