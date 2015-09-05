package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import servlets.DepartamentoServices;
import dto.Departments;

public class ServletListadoDepartamentos extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		DepartamentoServices depService = new DepartamentoServices();
		List <Departments> list = depService.todosLosDepartamentos();
		req.setAttribute("listadeps", list);
		req.getRequestDispatcher("reinicio.jsp").forward(req, resp);
		
	}

}
