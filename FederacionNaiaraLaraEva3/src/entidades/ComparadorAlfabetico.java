package entidades;

import java.util.Comparator;
import java.util.Collections;
import java.util.LinkedList;

import java.util.Iterator;

import utils.Datos;

public class ComparadorAlfabetico implements Comparator<DatosPersona> {

	// Examen 9, ejercicio 1, apartado B

	@Override
	public int compare(DatosPersona dp1, DatosPersona dp2) {
		return dp1.getNombre().compareTo(dp2.getNombre());
	}

	public void personasalfabetico() {
		LinkedList<DatosPersona> ret = new LinkedList<DatosPersona>();
		for (DatosPersona dp : Datos.PERSONAS) {
			ret.add(dp);
		}
		Collections.sort(ret, new ComparadorAlfabetico());
		System.out.println("La lista ordenada de todas las personas es:");
		Iterator<DatosPersona> it = ret.iterator();
		int i = 1;
		while (it.hasNext()) {
			System.out.println(i + ": " + ((DatosPersona) it.next()).toString() + " ");
			i++;
		}
	
	}
}
