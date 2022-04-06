package entidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import utils.Utilidades;
import validaciones.Validaciones;

//Examen 10, ejercicio 0
public class Responsable {

	private static final DateTimeFormatter LocalTime = null;
	// Examen 10, ejercicio 2

	private long idResponsable;
	private String telefonoProf;
	private LocalTime horarioIni;
	private LocalTime horarioFin;
	private Responsable[] responsable;

	// public importarRepresentantes();
	private DatosPersona persona;

	// constructor por defecto
	public Responsable() {

	}

	// constructor con todos los datos

	public Responsable(long idResponsable, String telefonoProf, LocalTime horarioIni, LocalTime horarioFin) {
		this.idResponsable = idResponsable;
		this.telefonoProf = telefonoProf;
		this.horarioIni = horarioIni;
		this.horarioFin = horarioFin;

	}

	// metodos getters y setters
	
	public long getIdResponsable() {
		return idResponsable;
	}

	public Responsable[] getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable[] responsable) {
		this.responsable = responsable;
	}

	public void setIdResponsable(long idResponsable) {
		this.idResponsable = idResponsable;
	}

	public String getTelefonoProf() {
		return telefonoProf;
	}

	public void setTelefonoProf(String telefonoProf) {
		this.telefonoProf = telefonoProf;
	}

	public LocalTime getHorarioIni() {
		return horarioIni;
	}

	public void setHorarioIni(LocalTime horarioIni) {
		this.horarioIni = horarioIni;
	}

	public LocalTime getHorarioFin() {
		return horarioFin;
	}

	public void setHorarioFin(LocalTime horarioFin) {
		this.horarioFin = horarioFin;
	}

	@Override
	public String toString() {
		return "Responsable [idResponsable=" + idResponsable + ", telefonoProf=" + telefonoProf + ", horarioIni="
				+ horarioIni + ", horarioFin=" + horarioFin + "]";
	}

	// (long idResponsable, String telefonoProf, LocalTime horarioIni, LocalTime
	// horarioFin)
	public static Responsable newResponsable() {
		Responsable ret = null;
		long idResponsable = -1;
		String telefonoProf = null;
		LocalTime horarioIni;
		LocalTime horarioFin;
		int elecc = -1;
		DatosPersona dp = null;
		Scanner in;
		boolean valido = false;
		do {
			System.out.println("Introduzca el id del nuevo responsable:");
			in = new Scanner(System.in);
			idResponsable = in.nextInt();
			if (idResponsable > 0)
				valido = true;
			else
				System.out.println("Valor incorrecto para el identificador.");
		} while (!valido);

		valido = false;
		do {
			System.out.println("Introduzca el nombre del nuevo patrocinador:");
			valido = Validaciones.validarTelefono(telefonoProf);
			if (!valido)
				System.out.println("ERROR: El número de teléfono introducido no es válido.");
		} while (!valido);

		valido = false;
		do {
			System.out
					.println("Introduzca el horario de inicio del nuevo responsable con el siguiente formato:HH:mm:ss");
			horarioIni = Utilidades.leerHora();
			valido = Validaciones.horarioIni();
			if (!valido)
				System.out.println("ERROR: El horario de inicio introducido para el nuevo responsable no es válido.");
		} while (!valido);

		valido = false;
		do {
			System.out.println("Introduzca el horario de fin del nuevo responsable con el siguiente formato:HH:mm:ss");
			horarioFin = Utilidades.leerHora();
			valido = Validaciones.horarioFin();
		} while (!valido);

		valido = false;

		System.out.println("Introduzca ahora los datos personales:");
		in = new Scanner(System.in);
		dp = DatosPersona.nuevaPersona();

		ret = new Responsable(idResponsable, telefonoProf, horarioIni, horarioFin);
		return ret;

	}

	// Examen 10, ejercicio 4
	/**
	 * Función que devuelve una cadena de caracteres con la siguiente estructura
	 * <<idResponsable> + ’.’ + <nombre> + ’(’ + <NIFNIE> + ’)’ + “horario de: ” + 
	 *	<horaIni(HH:mm)> + “ a ” + <horaFin(HH:mm)> + ” tfno: ” + <telefonoProf> 
	 * Cada campo se separa mediante el caracter '|'
	 * 
	 * @return
	 */
	public String data() {
		return "" + this.getIdResponsable() + '.' + persona.getNombre() + '(' + persona.getNifnie() + ')' + "horario de: " + this.getHorarioIni().format(LocalTime) + "a" + this.horarioFin.format(LocalTime) + "tfno:" + persona.getTelefono();
//		
	}
	
	//Examen 10, ejercicio 7
	
	private static void exportar(Responsable[] responsable) {
		String path = "responsables.txt";
		File fichero = new File(path);
		FileWriter escritor = null;
		PrintWriter buffer = null;
		try {
			try {
				escritor = new FileWriter(fichero, false);
				buffer = new PrintWriter(escritor);
				for (Responsable r : getResponsable()) {
					buffer.println(r.data());
				}
			} finally {
				if (buffer != null) {
					buffer.close();
				}
				if (escritor != null) {
					escritor.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException" + e.getResponsable());
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException" + e.getResponsable());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception" + e.getResponsable());
		}
	}
}
