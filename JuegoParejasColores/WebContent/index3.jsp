<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript">

var xmlHttp = new XMLHttpRequest();

function llamadaRest()
{
	xmlHttp.onreadystatechange = procesarEvento;
	xmlHttp.open('GET', 'tiempofinal', true); //indicamos como vamos a enviar los datos, en este caso con el metodo GET al archivo meses.php?num= el valor que le indiquemos en el textbox
	xmlHttp.setRequestHeader('Accept', 'application/json');
	xmlHttp.send(null);
	
}

function procesarEvento()
{
   
  if(xmlHttp.readyState==4) //ya hemos recibido respuesta del servidor
  {
      if(xmlHttp.status==200)// y el código de la cabecera http es bueno
          {
    	  	muestraPersona(xmlHttp.responseText);
          }
      else
      {
          alert("Ha ocurrido un error"+ xmlHttp.status +":"+ xmlHttp.statusText);
      }
  }

}

function muestraPersona(texto)
{
	alert("He recibido " + texto);
	var objetoJson = JSON.parse(xmlHttp.responseText);
	document.getElementById("nombre").value = objetoJson.nombre;
	document.getElementById("edad").value = objetoJson.edad;
	document.getElementById("descripcion").value = objetoJson.descripcion;
	
	
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Bienvenido</title>
</head>
<body>

	<label for="nombre">Nombre</label><input type = "text" name = "nombre" id="nombre"/>
	<label for="edad">Edad</label><input type = "text" name = "edad" id="edad"/>
	<label for="descripcion">Descripción</label><textarea name= "descripcion" rows="8" cols="8" id="descripcion"></textarea>
<input type="button" onclick="llamadaRest();" value="Trae Persona"></input>
</body>
</html>