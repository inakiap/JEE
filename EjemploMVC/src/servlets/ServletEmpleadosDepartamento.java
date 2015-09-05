package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import services.DepartamentoServices;
import services.EmpleadoService;
import dominio.Departments;
import dominio.EmpleadoDTO;

public class ServletEmpleadosDepartamento extends HttpServlet{

	private static Logger log = LogManager.getRootLogger();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		EmpleadoService empService = new EmpleadoService();
		DepartamentoServices depService = new DepartamentoServices();
		
		List <EmpleadoDTO> list = empService.listadoEmpleadosDepartamento(Integer.parseInt(req.getParameter("dep_id")));
		
		resp.setContentType("text/xml");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		
		
		Departments d = (Departments) depService.leerUnDepartamento(Integer.parseInt(req.getParameter("dep_id")));
		
		req.setAttribute("listaEmpleados", list);
		req.setAttribute("departamento", d);
		
		req.getRequestDispatcher("empleadosXdep.jsp").forward(req, resp);
		
	}

}

