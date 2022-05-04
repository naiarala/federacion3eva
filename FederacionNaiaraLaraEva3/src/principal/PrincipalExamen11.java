package principal;

import java.util.Scanner;

//Examen 11, Ejercicio 1, eval1

public class PrincipalExamen11 {

	private static final String nuevoTiempo = null;

	public static void main(String[] args) {
		String[] Tiempo = new String[10];

		Tiempo[0] = nuevoTiempo;
		Tiempo[1] = nuevoTiempo;
		Tiempo[2] = nuevoTiempo;
		Tiempo[3] = nuevoTiempo;
		Tiempo[4] = nuevoTiempo;
		Tiempo[5] = nuevoTiempo;
		Tiempo[6] = nuevoTiempo;
		Tiempo[7] = nuevoTiempo;
		Tiempo[8] = nuevoTiempo;
		Tiempo[9] = nuevoTiempo;

		// bucleo for mejorado
//		for (String i : Tiempo) {
//			System.out.println(i + "");
//
//		}
		for (int i = 0; i < Tiempo.length; i++)

		{

			System.out.print(Tiempo[i] + " ");

		}
//		Scanner in;
//		boolean valido = false;
//		do {
//			
//			if (Tiempo[i] < 1)
//				valido = true;
//			else
//				System.out.print(Tiempo[i]+" ");
//		} while (!valido);
//		valido = false;
//		do {
//			System.out.println("¿Desea modificar el valor?");
//		tiempo = in.nextLine();
//		valido = Validaciones.validarTiempoTotal(tiempo);
//		if (!valido)
//			System.out.println("El tiempo " + tiempo + " no es válido.");
//		else {
//			System.out.println("¿Es correcto el tiempo elegido:" + tiempo + "?");
//			valido = Utilidades.leerBoolean();
//		}

	}

}
