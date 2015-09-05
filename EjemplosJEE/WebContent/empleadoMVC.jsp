<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Empleado MVC</title>
</head>
<body>
<div class="container">
      <div class="header clearfix">
        <nav>
          <ul class="nav nav-pills pull-right">
            <li role="presentation" class="active"><a href="index.html" >Volver</a></li>
          </ul>
        </nav>
        <h3 class="text-muted">Javeando</h3>
      </div>
</div>

   <div class="container">
     <div class="jumbotron">
     		<jsp:useBean id="empleado" type="dominio.EmpleadoDTO" scope="session"></jsp:useBean>
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
		  	        <tr>
		  	          <td><jsp:getProperty property="id" name="empleado" /></td>	
		  	          <td><strong><jsp:getProperty property="f_name" name="empleado" /></strong></td>
		  	          <td><jsp:getProperty property="l_name" name="empleado" /></td>
		  	          <td><jsp:getProperty property="email" name="empleado" /></td>
		  	          <td><jsp:getProperty property="n_tlf" name="empleado" /></td>
		  	          <td><jsp:getProperty property="salary" name="empleado" /></td>
		  	        </tr>
		  	      </tbody>
		      	</table> 
     	
	 </div>
	</div>
	<% session.invalidate(); %>
	 <div class="container">
		<footer class="footer">
       	 <p>Página web realizada por <a href="http://codigonatura.com" target="_blank"><img src="http://codigonatura.com/wp-content/uploads/2014/10/cn-logo1-e1413870836327.png" width="100px" style="vertical-align:middle;"></a></p>
      	</footer>
    </div>
<script src="res/js/jquery.js"></script>
</body>
</html>