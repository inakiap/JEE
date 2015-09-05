<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>    
<%@ page import="dao.DepartmentDAO, dto.Departments, java.util.Iterator, java.util.List" %>

<%! Iterator<Departments> it = null;
	Departments d = null;
	List <Departments> list = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="res/css/bootstrap.min.css">
<title>Empleados</title>
<script src="res/js/mijs.js"></script>
<link href="res/css/style.css" rel="stylesheet">
</head>

<body>
<div class="container">
      <div class="header clearfix">
<!--         <nav> -->
<!--           <ul class="nav nav-pills pull-right"> -->
<!--             <li role="presentation" class="active"><a href="index.html" >Volver</a></li> -->
            
<!--           </ul> -->
<!--         </nav> -->
        <h3 class="text-muted">Mostrando empleados con AJAX</h3>
      </div>

</div>
      <div class="container">
		<div id="departamentos">
			<select name="dep_id" id="deps" onchange="elegirDep();">
				<%	
					list = (List <Departments>)request.getAttribute("listadeps");
					it = list.iterator();
					while (it.hasNext()) {
						d = it.next();
						%><option value="<%=d.getDepartmentId()%>"><%=d.getDepartmentName()%></option>
						<%} %>
			</select>
			
		</div>

		<div id="divtabla"></div>
		
		<div id="detalle"></div>
	  </div>
<div class="container">
	<footer class="footer">
    	<p>Página web realizada por <a href="http://codigonatura.com" target="_blank"><img src="http://codigonatura.com/wp-content/uploads/2014/10/cn-logo1-e1413870836327.png" width="100px" style="vertical-align:middle;"></a></p>
    </footer>
</div>	
	
</body>
</html>

