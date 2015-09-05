<%@page import="dominio.EmpleadoDTO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>    
<%@ page import="dao.DepartmentDAO, dominio.Departments, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="res/css/bootstrap.min.css">

<!-- Optional theme 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">-->
<!-- Jumbotron theme -->
<link href="res/css/style.css" rel="stylesheet">

<!-- Latest compiled and minified JavaScript -->
<script src="res/js/bootstrap.min.js"></script>
<title>Empleados de <c:out value="${departamento.departmentName}"></c:out></title>
</head>
<body>
<div class="container">
      <div class="header clearfix">
        <nav>
          <ul class="nav nav-pills pull-right">
           	<li role="presentation" ><a href="index.html">Inicio</a></li>
            <li role="presentation" class="active"><a href="departamentos">Mostrar otro departamento</a></li>
            
          </ul>
        </nav>
        <h3 class="text-muted">Segunda Evaluación CAM</h3>
      </div>

</div>
     <div class="container">
     	<div class="jumbotron">
      		<h3>Empleados de <c:out value="${departamento.departmentName}"></c:out></h3>
      		</div>
      		<% List<EmpleadoDTO> lista = (List<EmpleadoDTO>)request.getAttribute("listaEmpleados");
      		if (lista.isEmpty()){ %>
      		<h5 class="text-muted, text-center">No hay empleados que mostrar</h5> <%}
      		
      		else{%>
      		
      		<table class="table table-condensed">
  	      		<thead>
		  	        <tr>
		  	          <th>Identificador</th>
		  	          <th>Nombre</th>
		  	          <th>Apellido</th>
		  	          <th>e-mail</th>
		  	          <th>Teléfono</th>
		  	          <th>Salario</th>
		  	        </tr>
		  	      </thead>
		  	      <tbody>
		  	          <c:forEach items="${listaEmpleados}" var="empleado" varStatus="i" >
		  	          <tr>
		  	          	<td><c:out value="${empleado.id}"></c:out></td>	
		  	          	<td><strong><c:out value="${empleado.f_name}"></c:out></strong></td>
		  	          	<td><c:out value="${empleado.l_name}"></c:out></td>
		  	          	<td><c:out value="${empleado.email}"></c:out></td>
		  	          	<td><c:out value="${empleado.n_tlf}"></c:out></td>
		  	          	<td><c:out value="${empleado.salary}"></c:out></td>
		  	          </tr>
		  	          </c:forEach>
		  	      </tbody>
		      	</table> 
			<%} %>
			
	</div>
	<div class="container">
		<footer class="footer">
       	 <p>Página web realizada por <a href="http://codigonatura.com" target="_blank"><img src="http://codigonatura.com/wp-content/uploads/2014/10/cn-logo1-e1413870836327.png" width="100px" style="vertical-align:middle;"></a></p>
      	</footer>
    </div>
<script src="res/js/jquery.js"></script>

</body>
</html>