package entidades;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Scanner;

import validaciones.Validaciones;

//Examen 11, Ejercicio 1, eval 1

public class Tiempo implements Serializable, Comparable<Tiempo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Examen 11, Ejercicio 1, eval 2
	@Override
	public int compareTo(Tiempo t) {
		return Integer.compare(this.hora, t.hora);
	}

	private int hora;
	private int minutos;
	private int segundos;
	private int centesimas;

	// constructor por defecto
	public Tiempo() {

	}

	// constructor con todos los argumentos
	public Tiempo(int hora, int minutos, int segundos, int centesimas) {
		this.hora = hora;
		this.minutos = minutos;
		this.segundos = segundos;
		this.centesimas = centesimas;
	}

	// getters y setters

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public int getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}

	public int getCentesimas() {
		return centesimas;
	}

	public void setCentesimas(int centesimas) {
		this.centesimas = centesimas;
	}

	// reimplementacion del metodo toString()
	@Override
	public String toString() {

		return "" + "horas:" + this.getHora() + "minutos" + this.getMinutos() + "segundos" + this.getSegundos()
				+ "centesimas" + this.getCentesimas();

	}

	// metodo nuevoTiempo()
	public static Tiempo nuevoTiempo() {
		Tiempo ret = new Tiempo();
		int hora = 0;
		int minutos = 0;
		int segundos = 0;
		int centesimas = 0;
		Scanner in;
		boolean valido = false;
		do {
			System.out.println("Introduzca la hora:");
			in = new Scanner(System.in);
			hora = in.nextInt();
			if (hora > 0)
				valido = true;
			else
				System.out.println(hora);
		} while (!valido);

		valido = false;
		do {
			System.out.println("Introduzca los minutos:");
			in = new Scanner(System.in);
			minutos = in.nextInt();
			if (minutos > 0)
				valido = true;
			else
				System.out.println(minutos);
		} while (!valido);

		valido = false;
		do {
			System.out.println("Introduzca los segundos:");
			in = new Scanner(System.in);
			segundos = in.nextInt();
			if (segundos > 0)
				valido = true;
			else
				System.out.println(segundos);
		} while (!valido);

		valido = false;
		do {
			System.out.println("Introduzca las centesimas:");
			in = new Scanner(System.in);
			centesimas = in.nextInt();
			if (centesimas > 0)
				valido = true;
			else
				System.out.println(centesimas);
		} while (!valido);
		valido = false;
//		do {
//			System.out.println("Tiempo total");
//			in = new Scanner(System.in);
//			valido=Validaciones.validarTiempoTotal(0);
//			
//		}while (!valido);
//		valido=false;

		return ret;

	}

}
