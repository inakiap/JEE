package servlets;

import java.io.IOException;

import dao.Matricula;

public class MaPrueba {

	public static void main(String[] args) throws IOException {
		
		String s2 = "0113JGN";
		String s1 = "1105DBC";
		String url = "http://www.infolaso.com/sistema-actual.html";
		Matricula objmatricu = new Matricula();
		
//		String bruto = ServletMatriculas.extraerBrutoMatriculas(url);
//		String aniosYmatriculas = ServletMatriculas.preProcesarCadenaAniosYmatriculas(bruto);
//		System.out.println(aniosYmatriculas);
//		TreeMap<String, Matricula> colecc = new TreeMap<String, Matricula>();
//		colecc = ServletMatriculas.postProcesoAniosYMatriculas(aniosYmatriculas);
//		System.out.println(colecc.toString());
//		String resultado = ServletMatriculas.fecharMatricula(s2, colecc);
//		System.out.println(resultado);
		
		System.out.println(ServletMatriculas.ordenarMatriculas(s2, s2));
			   
	}

}
