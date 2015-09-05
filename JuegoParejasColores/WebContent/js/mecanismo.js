
var celdasSelect = 0;
var idCeldaSelect;
var parejaCeldaSelect;
var contador = 0;

var nombre;
var temp;

var xmlHttp = new XMLHttpRequest();

function llamadaRest()
{
	xmlHttp.onreadystatechange = procesarEvento;
	xmlHttp.open('GET', 'tiempofinal?tiempo='+temp+'&nombre='+nombre , true);
	xmlHttp.setRequestHeader('Accept', 'application/json');
	xmlHttp.send(null);
	
}
function procesarEvento()
{
   
  if(xmlHttp.readyState==4) //ya hemos recibido respuesta del servidor
  {
      if(xmlHttp.status==200)// y el código de la cabecera http es bueno
          {
    	  	mostrarListado(xmlHttp.responseText);
          }
      else
      {
          alert("Ha ocurrido un error"+ xmlHttp.status +":"+ xmlHttp.statusText);
      }
  }

}

function mostrarListado(texto)
{
	var listado = JSON.parse(xmlHttp.responseText);
	
	listado.sort(function(b,a){return a.tiempo-b.tiempo});
	
	var resultado ='';
	
	for (var i = 0; i<listado.length; i++){
		resultado = listado[i].nombre +' ha tardado '+ listado[i].tiempo +'\n'+ resultado;
	}
	
	alert(resultado);
	
}

function iniciarJuego(){
	
	var tablero = document.getElementById("marco");
	tablero.style.visibility = "visible";
	
	var divBoton = document.getElementById("divBoton");
	divBoton.setAttribute("style","display:none")
	
	var botonReinicio = document.getElementById("reinicio");
	botonReinicio.style.visibility = "visible";	
	
	var reloj = document.getElementById("reloj");
	reloj.style.visibility = "visible";
	
	colorearCeldas();
	empezar();
}

function colorAleatorio(){
    var letters = '0123456789ABCDEF'.split('');
    var color = '#';
    for (var i = 0; i < 6; i++ ) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

function colorearCeldas(){
	
	var celda1 = document.getElementById("1");
	var celda2 = document.getElementById("2");
	var celda3 = document.getElementById("3");
	var celda4 = document.getElementById("4");
	var celda5 = document.getElementById("5");
	var celda6 = document.getElementById("6");
	var celda7 = document.getElementById("7");
	var celda8 = document.getElementById("8");
	var celda9 = document.getElementById("9");
	var celda10 = document.getElementById("10");
	var celda11 = document.getElementById("11");
	var celda12 = document.getElementById("12");
	var celda13 = document.getElementById("13");
	var celda14 = document.getElementById("14");
	var celda15 = document.getElementById("15");
	var celda16 = document.getElementById("16");
	var celda17 = document.getElementById("17");
	var celda18 = document.getElementById("18");
	var celda19 = document.getElementById("19");
	var celda20 = document.getElementById("20");
	
	var celdas = new Array (celda1,celda2,celda3,celda4,celda5,celda6,celda7,celda8,celda9,celda10,
			celda11,celda12,celda13,celda14,celda15,celda16,celda17,celda18,celda19,celda20);
	
	for (var i = 0; i < 10; i++) {
		var aleatorio1 = Math.floor(Math.random() * (celdas.length-1));
		var par1 = celdas[aleatorio1];
		celdas.splice(aleatorio1, 1);
		var aleatorio2 = Math.floor(Math.random() * (celdas.length-1));
		var par2 = celdas[aleatorio2];
		celdas.splice(aleatorio2, 1);
		
		var color = colorAleatorio() ;
		
		par1.style.backgroundColor = color;
		var id1 = par1.getAttribute("id");
		par1.setAttribute("onclick", "compararCelda(" + i + "," + id1 + ");");
		par2.style.backgroundColor = color;
		var id2 = par2.getAttribute("id");
		par2.setAttribute("onclick", "compararCelda(" + i + "," + id2 + ");");
		
	}
	
}

function compararCelda (pareja, id){
	var resultado = document.getElementById("tiempoFin");
	if (celdasSelect == 0){
		var celdaSelect1 = document.getElementById(id);
		celdaSelect1.style.opacity = "0.7"; 
		idCeldaSelect = id;
		parejaCeldaSelect = pareja;
		celdasSelect++;
	}
	else if (celdasSelect == 1){
		if (parejaCeldaSelect == pareja && idCeldaSelect != id) {
			var celdaSelect1 = document.getElementById(idCeldaSelect);
			celdaSelect1.style.opacity = "0";
			celdaSelect1.removeAttribute("onclick");
			
			var celdaSelect2 = document.getElementById(id);
			celdaSelect2.style.opacity = "0";
			celdaSelect2.removeAttribute("onclick");
			
			celdasSelect = 0;
			idCeldaSelect = -1;
			parejaCeldaSelect = -1;
			contador++;

			if (contador == 10) {
				clearInterval(cronometro);
				var reloj = document.getElementById("reloj");
				reloj.className = "blink_me";
				setInterval(blinker, 1000);
				$("#marco").remove();
				document.getElementById("botonFin").style.visibility = "visible";
				$("#reinicio").remove();
				nombre = prompt('&iquest;C&oacute;mo te llamas?','');
				llamadaRest();
			}
		}
		else{
			celdasSelect = 0;
			var celdaSelect1 = document.getElementById(idCeldaSelect);
			celdaSelect1.style.opacity = "1";
			
			idCeldaSelect = -1;
			parejaCeldaSelect = -1;
		}
		
	}
	
}

function empezar() { //función del temporizador
	   emp = new Date();
	   cronometro = setInterval(tiempo, 10);
}
	


function tiempo (){
	var visor = document.getElementById("reloj");
    actual=new Date(); //fecha a cada instante
    //tiempo del crono (cro) = fecha instante (actual) - fecha inicial (emp)
	 cro=actual- emp; //milisegundos transcurridos.
	 cr=new Date(); //pasamos el num. de milisegundos a objeto fecha.
	 cr.setTime(cro); 
	    //obtener los distintos formatos de la fecha:
	 cs=cr.getMilliseconds(); //milisegundos 
	 cs=cs/10; //paso a centésimas de segundo.
	 cs=Math.round(cs); //redondear las centésimas
	 sg=cr.getSeconds(); //segundos 
	 mn=cr.getMinutes(); //minutos 
	 ho=cr.getHours()-1; //horas 
	    //poner siempre 2 cifras en los números		 
	 if (cs<10) {cs="0"+cs;} 
	 if (sg<10) {sg="0"+sg;} 
	 if (mn<10) {mn="0"+mn;} 
	    //llevar resultado al visor.		 
	 temp = ho+" "+mn+" "+sg+" "+cs;
	 visor.innerHTML= temp;
	
}

function blinker() {
    $('.blink_me').fadeOut(500);
    $('.blink_me').fadeIn(500);
}
