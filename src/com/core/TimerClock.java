package com.core;
/**
 * Esta classe serve apenas para mostrar a que tempo o programa est� sendo
 * executado.
 */
public class TimerClock {

	static double timer = System.currentTimeMillis();
	double decimos = 0;
	int segundos = 0;
	int minutos = 0;
	int horas = 0;

	public void time() {
		
		if (System.currentTimeMillis() - timer >= 100) {
			//System.currentTimeMillis() Retorna a hora atual em milissegundos
			if(decimos >= 10) {
				segundos++;
			}
			if (segundos == 60) {
				minutos ++;
				segundos = 0;
			}
			if ( minutos == 60) {
				horas ++;
				minutos = 0;
			}
			decimos ++;
			System.out.println("tempo corrido: " + horas + " :" + minutos + " :" + segundos);
			timer = System.currentTimeMillis();
			
		}
	}
	
	public double contarDecimosDeSegundos(double num) {
		
		if (System.currentTimeMillis() - timer >= 100) {
			
			if(decimos == num ) {
				decimos=0;
			}
			if (segundos == 60) {
				minutos ++;
				segundos = 0;
			}
			if ( minutos == 60) {
				horas ++;
				minutos = 0;
			}
			decimos+=0.1;
			timer = System.currentTimeMillis();
		}
		return decimos;
	}
	
	public void setDecimos(double num) {
		this.decimos = num;
	}

	public int contarSegundos(int MaxSeconds) {
		
		if (System.currentTimeMillis() - timer >= 1000) {
			//System.currentTimeMillis() Retorna a hora atual em milissegundos
			if (segundos == MaxSeconds) {
				segundos = 0;
			}
			if ( minutos == 60) {
				horas ++;
				minutos = 0;
			}
			segundos++;
			timer = System.currentTimeMillis();
		}
		return segundos;
	}
	
	public int getSegundos() {
		
		return segundos;
	}
	
	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}
	
	public double getDecimos() {
		return decimos;
	}

}
