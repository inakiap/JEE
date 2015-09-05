package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmpJDBCDAO;
import dto.EmpleadoDTO;
import static aux.Logs.*;

public class ServletCargarEmpleados extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String dep = req.getParameter("num");
		EmpJDBCDAO eDAO = new EmpJDBCDAO();
		EmpleadoDTO emp = new EmpleadoDTO();
		List<EmpleadoDTO> listado = eDAO.leerTodosEmpleados(Integer.parseInt(dep));
		Iterator<EmpleadoDTO> it = listado.iterator();
		
		log.trace(listado.toString());
		
		PrintWriter out = resp.getWriter();
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/xml");

		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<empleados>");
		
		while (it.hasNext()) {
			emp = it.next();
			out.println("<empleado>");
			out.println("<id>"+emp.getId()+"</id>");
			out.println("<nombre>"+emp.getF_name()+"</nombre>");
//			out.println("<apellido>"+emp.getL_name()+"</apellido>");
//			out.println("<salario>"+emp.getSalary()+"</salario>");
			out.println("</empleado>");
		}
		out.println("</empleados>");
		
	}

}
