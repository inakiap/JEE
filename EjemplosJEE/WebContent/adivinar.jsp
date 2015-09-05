<%@page import="auxiliar.CalcularNumeros"%>
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page session="false" %>
<%@ page import="org.apache.logging.log4j.LogManager"%>
<%@ page import="org.apache.logging.log4j.Logger"%> 

<%! 
	int incognita = -1; //declaro mi n�mero a descubrir con un valor inicial imposible para saber que es la primera vez 
	int min = 1;
 	int max = 100;
 	String rutaSalida = "exito.html";
 	private static Logger log = LogManager.getRootLogger();
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
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
<title>HolaMundo JSP</title>
</head>
<body>
<div class="container">
      <div class="header clearfix">
        <nav>
          <ul class="nav nav-pills pull-right">
            <li role="presentation" ><a href="index.html" >Inicio</a></li>
            <li role="presentation" class="active"><a href="adivinar.html" >Adivinar otro n�mero</a></li>
          </ul>
        </nav>
        <h3 class="text-muted">Javeando</h3>
      </div>

</div>
   <div class="container">
     <div class="jumbotron">
      	<h3>En proceso de adivinar el n�mero...</h3>
      	<h3>�Es este t� n�mero?</h3>
      	
      	<% 
      		try{
	      		if(-1 != incognita){
					if (request.getParameter("elegir").equals("igual")){
						response.sendRedirect(rutaSalida);
						incognita = -1; //Encontr� el n�mero y vuelvo a poner la variable con el valor inicial
						log.trace("Encontr� el n�mero y vuelvo a poner la variable con el valor inicial");
					}else{
						if(request.getParameter("elegir").equals("mayor")){
							min = incognita; //igualo el m�nimo a la inc�gita y a continuaci�n calcula la media
							incognita = CalcularNumeros.calcularNumeroMedio(min, max);
							log.trace("El n�mero es mayor " + incognita);
						}else {
							max = incognita; //igualo el m�ximo a la inc�gita y a continuaci�n calcula la media
							incognita = CalcularNumeros.calcularNumeroMedio(min, max);
							log.trace("El n�mero es menor " + incognita);
						}
					}
	      		}else{
	      			incognita = CalcularNumeros.generarNumeroAleatorio(); //la primera vez genero un n�mero aleatorio
	      			log.trace("Al empezar generamos un n�mero aleatorio");
	      		}
      		}catch(NullPointerException e){
      			log.error("Casc� en mis casos/if");
      			e.printStackTrace();
      		}
      	%>
      	<form action="adivinar.jsp" method="get">
			<input type="text" size="3" name="valorIncognita" value="<%=incognita%>">
			<select name="elegir">
				<option value="igual">��S�!!</option>
				<option value="mayor">Es mayor</option>
				<option value="menor">Es menor</option>
			</select>
			<input type="submit" value="Hecho">		
		</form>
					
	 </div>
	</div>
	 <div class="container">
		<footer class="footer">
       	 <p>P�gina web realizada por <a href="http://codigonatura.com" target="_blank"><img src="http://codigonatura.com/wp-content/uploads/2014/10/cn-logo1-e1413870836327.png" width="100px" style="vertical-align:middle;"></a></p>
      	</footer>
    </div>
<script src="res/js/jquery.js"></script>
</body>
</html>