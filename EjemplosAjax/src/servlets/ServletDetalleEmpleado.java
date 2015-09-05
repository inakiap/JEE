package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.EmpJDBCDAO;
import dto.EmpleadoDTO;

public class ServletDetalleEmpleado extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String idEmp = req.getParameter("empleado");
		EmpJDBCDAO eDAO = new EmpJDBCDAO();
		Gson gson = new Gson();
		EmpleadoDTO empleado = eDAO.leerEmpleado(Integer.parseInt(idEmp));
		
		String empJson = gson.toJson(empleado);
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(empJson);
		
	}
}
