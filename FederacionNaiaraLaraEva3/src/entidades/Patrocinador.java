package entidades;

import java.util.Comparator;
import java.util.Scanner;

import utils.Utilidades;
import validaciones.Validaciones;

//Examen 10, ejercicio 0
public class Patrocinador implements Comparator<Patrocinador>{
	
	//Examen 10, ejercicio 5
	@Override
	public int compare(Patrocinador o1, Patrocinador o2) {
		return o1.getIdPatrocinador().compareTo(o2.getIdPatrocinador());
	}
	public int compareTo(Patrocinador o2) {
		
		if (this.getDotacion()<(o2.getDotacion()))
			return -1;
		else
		
		if (this.getDotacion()>(o2.getDotacion()))
			return 1;
		else {
			return this.getIdPatrocinador().compareTo(o2.getIdPatrocinador());
		}
	}


	// Examen 10 ,ejercicio 2

	private long idPatrocinador;
	private String nombrePatrocinador;
	private String web;
	private double dotacion;

	public void patrocinador(String cadena) {
		this.idPatrocinador = idPatrocinador;
		this.nombrePatrocinador = nombrePatrocinador;
		this.web = web;
		this.dotacion=dotacion;
	}

//Examen 10, ejercicio 3 apartado b
	private static Patrocinador newmostrarBasico() {
		Patrocinador ret = null;
		Scanner in;
		boolean valido = false;
		String cadena;
		do {
			System.out.println("Introduzca el id del patrocinador:");
			in = new Scanner(System.in);
			cadena = in.nextLine();
			valido = Validaciones.validarId(cadena);
		} while (!valido);
		valido = false;
		do {
			System.out.println("Introduzca el nombre del nuevo patrocinador:");
			valido = Validaciones.validarNombre(cadena);
			if (!valido)
				System.out.println("ERROR: El nombre introducido no es válido.");
		} while (!valido);

		valido = false;

		ret = new Patrocinador();
		return ret;
	}

	private static Patrocinador newmostrarCompleto() {
		Patrocinador ret = null;
		Scanner in;
		boolean valido = false;
		String cadena;
		do {
			System.out.println("Introduzca el id del patrocinador:");
			in = new Scanner(System.in);
			cadena = in.nextLine();
			valido = Validaciones.validarId(cadena);
		} while (!valido);
		valido = false;
		do {
			System.out.println("Introduzca el nombre del nuevo patrocinador:");
			valido = Validaciones.validarNombre(cadena);
			if (!valido)
				System.out.println("ERROR: El nombre introducido no es válido.");
		} while (!valido);
		valido = false;
		do {
			System.out.println("Introduzca la dotacion del nuevo Patrocinador");
			valido = Validaciones.Dotacion(cadena);
		} while (!valido);

		valido = false;

		ret = new Patrocinador();
		return ret;
	}

	// constructor por defecto
	public Patrocinador() {

	}

	// constructor campos obligatorios
	public Patrocinador(long idPatrocinador, String nombrePatrocinador, String web, double dotacion) {
		this.idPatrocinador = idPatrocinador;
		this.nombrePatrocinador = nombrePatrocinador;
		this.web = web;
		this.dotacion = dotacion;
	}

	// metodos getters y setters
	public long getIdPatrocinador() {
		return idPatrocinador;
	}

	public void setIdPatrocinador(long idPatrocinador) {
		this.idPatrocinador = idPatrocinador;
	}

	public String getNombrePatrocinador() {
		return nombrePatrocinador;
	}

	public void setNombrePatrocinador(String nombrePatrocinador) {
		this.nombrePatrocinador = nombrePatrocinador;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public double getDotacion() {
		return dotacion;
	}

	public void setDotacion(double dotacion) {
		this.dotacion = dotacion;
	}

	@Override
	public String toString() {
		return "Patrocinador [idPatrocinador=" + idPatrocinador + ", nombrePatrocinador=" + nombrePatrocinador
				+ ", web=" + web + ", dotacion=" + dotacion + "]";
	}

	// (long idPatrocinador, String nombrePatrocinador, String web, double dotacion)

	public static Patrocinador newPatrocinador() {
		Patrocinador ret = null;
		long idPatrocinador = -1;
		String nombre = null;
		String web = null;
		double dotacion = 0.0;
		int elecc = -1;
		DatosPersona dp = null;
		Scanner in;
		boolean valido = false;
		do {
			System.out.println("Introduzca el id del nuevo patrocinador:");
			in = new Scanner(System.in);
			idPatrocinador = in.nextInt();
			if (idPatrocinador > 0)
				valido = true;
			else
				System.out.println("Valor incorrecto para el identificador.");
		} while (!valido);

		valido = false;
		do {
			System.out.println("Introduzca el nombre del nuevo patrocinador:");
			valido = Validaciones.validarNombre(nombre);
			if (!valido)
				System.out.println("ERROR: El nombre introducido no es válido.");
		} while (!valido);

		valido = false;
		do {
			System.out.println("Introduzca la web del nuevo patrocinador:");
			valido = Validaciones.validarWeb(web);
			if (!valido)
				System.out.println("ERROR: La url introducida para la web no es válida.");
		} while (!valido);

		valido = false;
		do {
			System.out.println("Introduzca la dotacion del nuevo Patrocinador");
			dotacion = Utilidades.leerDouble();
			valido = Validaciones.Dotacion(dotacion);
		} while (!valido);

		valido = false;

		System.out.println("Introduzca ahora los datos personales:");
		in = new Scanner(System.in);
		dp = DatosPersona.nuevaPersona();

		ret = new Patrocinador(idPatrocinador, nombre, web, dotacion);
		return ret;

	}
	public String mostrarBasico() {
		String ret = "";
		ret += this.id + ". " + this.nombre + (!this.web.equals("") ? " " + web : " ");
		return ret;
	}



}
