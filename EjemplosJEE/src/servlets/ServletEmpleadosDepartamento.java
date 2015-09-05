package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.DepartmentDAO;
import dao.EmpJDBCDAO;
import dominio.Departments;
import dominio.EmpleadoDTO;

public class ServletEmpleadosDepartamento extends HttpServlet{

	private static Logger log = LogManager.getRootLogger();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		EmpJDBCDAO eJDBC = new EmpJDBCDAO();
		DepartmentDAO dDAO = new DepartmentDAO();
		
		List <EmpleadoDTO> list = eJDBC.leerTodosEmpleados(Integer.parseInt(req.getParameter("dep_id")));
		
		Departments d = (Departments) dDAO.leerDepartamento(Integer.parseInt(req.getParameter("dep_id")));
		
		req.setAttribute("listaEmpleados", list);
		req.setAttribute("departamento", d);
		
		req.getRequestDispatcher("empleadosXdep.jsp").forward(req, resp);
		
	}

}

