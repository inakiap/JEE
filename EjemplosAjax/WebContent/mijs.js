var xmlHttp = null;

var filas = null;

function iniciaObjetoRequest ()
{
	return new XMLHttpRequest();
}

function elegirDep()
{
	
	var depart = document.getElementById("deps");
	depart = depart.options[depart.selectedIndex].value;
	
	xmlHttp = iniciaObjetoRequest();
	xmlHttp.onreadystatechange = procesarEmpleados;
	xmlHttp.open('GET', 'empleados?num='+depart, true);
	xmlHttp.send(null);
	
}

function procesarEmpleados ()
{
	if (xmlHttp.readyState == 4) // ya hemos recibido respuesta del servidor
	{
		if (xmlHttp.status == 200)// y el código de la cabecera http es bueno
		{
			//alert("He recibido " + xmlHttp.responseText);
			var parser = new DOMParser();
			var xmlDoc = parser.parseFromString(xmlHttp.responseText,
					"application/xml"); //OBTENEMOS EL XML CREADO POR EL SERVLET DELA RESPUESTA.
			//OBTENEMOS LOS ELEMENTOS EMPLEADO DEL XML  (PQ SE COMO SE LLAMA PREVIAMENTE).
			var lista_empleados = xmlDoc.getElementsByTagName("empleado");
			var nelementos = lista_empleados.length; //CREAMOS UNA VARIABLE nELEMENTOS PARA LUEGO ITERAR

			var id = null;
			var nombre = null;
			
			var tablaCreada = document.getElementById("empleados"); //CREAMOS UNA TABLA CON JS.
			
			if(null != tablaCreada){ //SI NO DEVUELVE NADA LA TABLA NO EXISTE Y NO HAY QUE BORRARLA
				tablaCreada.remove()
			}
			
			var divTabla = document.getElementById("divtabla");
			
			var idTitulo = document.createTextNode("Id");
			var nombreTitulo = document.createTextNode("Nombre");
			
			var th1 = document.createElement("TH");
			var th2 = document.createElement("TH");
			
			var cabecera = document.createElement("TR");
			var textoTitulo = document.createTextNode("Empleados");
			var tituloTabla = document.createElement("CAPTION");
			var table = document.createElement("TABLE");
			table.setAttribute("id", "empleados");
			
			th1.appendChild(idTitulo);
			th2.appendChild(nombreTitulo);
			cabecera.appendChild(th1);
			cabecera.appendChild(th2);
			
			table.appendChild(cabecera);
			table.appendChild(tituloTabla);
			
			for (var i = 0; i < nelementos; i++) {
				id = lista_empleados[i].getElementsByTagName("id")[0].childNodes[0].data; 
				nombre = lista_empleados[i].getElementsByTagName("nombre")[0].childNodes[0].data;

				 var tr = document.createElement('TR'); 
				 var td1 = document.createElement('TD');
				 var td2 = document.createElement('TD');

				 tr.setAttribute("onclick", "infoEmpleado("+id+");");
			
				 
				 var idEmple = document.createTextNode(id);
				 var nomEmple = document.createTextNode(nombre);
				 
				 
				 td1.appendChild(idEmple);
				 td2.appendChild(nomEmple);
				 tr.appendChild(td1);
				 tr.appendChild(td2);
				 table.appendChild(tr);
			}
			divTabla.appendChild(table);
		} 
		else {
				alert("Ha ocurrido un error" + xmlHttp.status + ":" + xmlHttp.statusText);
		}
		
	}
}

function infoEmpleado (identificador){
	xmlHttp = iniciaObjetoRequest();
	xmlHttp.onreadystatechange = procesarInfoEmpleados;
	xmlHttp.open('GET', 'detalleEmpleado?empleado=' + identificador, true); 
	xmlHttp.send(null);
}

function procesarInfoEmpleados(){

	if (xmlHttp.readyState == 4) // ya hemos recibido respuesta del servidor
	{
		if (xmlHttp.status == 200)// y el código de la cabecera http es bueno
		{
			var detalle = document.getElementById("detalle");
			var empleado = JSON.parse(xmlHttp.responseText);
			detalle.innerHTML = "<h4>Nombre:  "+empleado.f_name+"  | Apellido: "+ empleado.l_name +"  | Salario: "+ empleado.salary+ " $ </h4>"
		}
		else {
			alert("Ha ocurrido un error" + xmlHttp.status + ":" + xmlHttp.statusText);
		}
	}
	
}