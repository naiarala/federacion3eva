package entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Scanner;

import utils.Utilidades;
import validaciones.Validaciones;

public class Participante implements Serializable, Comparable<Participante> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3245951141710976928L;
	protected long id;
	protected int dorsal; // valor entre 001 y 150
	protected char calle;

	// Examen 11, Ejercicio 2
	protected int Tiempo;
	protected boolean penalizacion; // por defecto valor false
	protected String otros; // no mas de 500 caracteres

	public Participante(long id, int dorsal, char calle) {
		super();
		this.id = id;
		this.dorsal = dorsal;
		this.calle = calle;
	}

	public Participante(long id, int dorsal, char calle, int tiempo, boolean penalizacion, String otros) {
		super();
		this.id = id;
		this.dorsal = dorsal;
		this.calle = calle;
		this.Tiempo = Tiempo;
		this.penalizacion = penalizacion;
		this.otros = otros;
	}

	public Participante() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public char getCalle() {
		return calle;
	}

	public void setCalle(char calle) {
		this.calle = calle;
	}
// getters y setters nuevos

	public int getTiempo() {
		return Tiempo;
	}

	public void setTiempo(int tiempo) {
		Tiempo = tiempo;
	}

	public boolean isPenalizacion() {
		return penalizacion;
	}

	public void setPenalizacion(boolean penalizacion) {
		this.penalizacion = penalizacion;
	}

	public String getOtros() {
		return otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}

	// metodo toString()
	@Override
	public String toString() {
		return "Participante [id=" + id + ", dorsal=" + dorsal + ", calle=" + calle + ", Tiempo=" + Tiempo
				+ ", penalizacion=" + penalizacion + ", otros=" + otros + "]";
	}

	// metodo nuevoParticipante
	public static Participante nuevoParticipante() {
		Participante ret = new Participante();

		long id = 0;
		int dorsal = 1;
		char calle = (Character) null;
		int tiempo = 1;
		boolean penalizacion = false;
		String otros = "";
		DatosPersona dp = null;
		Scanner in = new Scanner(System.in);
		boolean valido = false;
		do {
			System.out.println("Introduzca el id del nuevo participante:");
			in = new Scanner(System.in);
			id = in.nextInt();
			if (id > 0)
				valido = true;
			else
				System.out.println("Valor incorrecto para el identificador.");
		} while (!valido);

		valido = false;
		Participante participante = Participante.nuevoParticipante();
		boolean resp = true;
		HashSet<Participante> participantes = new HashSet<Participante>();
		System.out.println("Introduzca los datos de los participantes (entre 3 y 10)");
		for (int i = 1; resp; i++) {
			System.out.println("Introduzca datos del Participante " + i + ":");
			Participante p = Participante.nuevoParticipante();
			participantes.add(p);
			if (participantes.size() >= 3) {
				System.out.println("Ya tendría el minimo de participantes permitidos");
				if (participantes.size() < 10) {
					System.out.println("¿Desea introducir otro participante?");
					resp = Utilidades.leerBoolean();
				} else
					System.out.println("Ya ha completado el cupo de participantes. No puede añadir más.");
				resp = false;
			}

			ret = new Participante(id, dorsal, calle, tiempo, penalizacion, otros);

			return ret;
		}
		return participante;

	}
//Examen 11, Ejercicio 2, eval 2
	@Override
	public int compareTo(Participante p2) {
//		if (this.getTiempo().isAfter(p2.getTiempo()))
//			return -1;
//		else if (this.getCalle().isBefore(p2.getCalle()))
			return 1;
	}
}
