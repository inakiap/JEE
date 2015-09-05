package dao;

import java.text.Collator;

public class Matricula implements Comparable<Matricula>{
	
	private String anio;
	private String valorMatricula;
	
	public Matricula(String anio, String valorMatricula) {
		this.anio = anio;
		this.valorMatricula = valorMatricula;
	}
	
	public Matricula(){	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getValorMatricula() {
		return valorMatricula;
	}

	public void setValorMatricula(String valorMatricula) {
		this.valorMatricula = valorMatricula;
	}

	@Override
	public String toString() {
		return "Matricula [anio=" + anio + ", valorMatricula=" + valorMatricula
				+ "]";
	}

	@Override
	public int compareTo(Matricula o) {
		int resultado = 55;
		
		String s1 = this.getValorMatricula();
		String s2 = o.getValorMatricula();
		
		Collator comparador = Collator.getInstance();
		
		resultado = comparador.compare(s1.substring(4), s2.substring(4));
		
		if (resultado == 0){
			int i1 = Integer.parseInt(s1.substring(0,3));
			int i2 = Integer.parseInt(s2.substring(0,3));
			
			if (i1 < i2){ 
				resultado = -1;
			} else{ 
				
				if(i1 > i2){
					
					resultado = 1;
					
				}else{
					
					resultado = 0;
				}
			}
		}
		return resultado;
	}
	
}
