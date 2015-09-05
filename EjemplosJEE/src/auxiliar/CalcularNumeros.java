package auxiliar;

import java.util.Random;

public class CalcularNumeros {
	
	/**
	 * Método que genera un número entre 1 y 100 aleatoriamente
	 * @return un número entero entre 1 y 100
	 */
	public static int generarNumeroAleatorio (){
		Random r = new Random();
		int num = r.nextInt(100)+1;
		return num;
	}

	public static int calcularNumeroMedio (int n1, int n2){
		int num = 0;
		num = (n1 + n2)/2;
		return num;
	}
	
	public static int generarNumeroAleatorio2 (int max, int min){
		Random r = new Random();
		int num = r.nextInt((max-min)+1)+1;
		return num;
	}
}
