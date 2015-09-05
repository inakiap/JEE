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
import dominio.Departments;

public class ServletListadoDepartamentos extends HttpServlet{

	private static Logger log = LogManager.getRootLogger();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		DepartmentDAO eDAO = new DepartmentDAO();
		List <Departments> list = eDAO.leerDepartamentos();
		
		req.setAttribute("listadeps", list);
		
		req.getRequestDispatcher("departamentos.jsp").forward(req, resp);
		
		
	}

}
