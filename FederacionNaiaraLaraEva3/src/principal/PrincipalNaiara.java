package principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entidades.DatosPersona;
import utils.ConexBD;
import utils.Datos;

public class PrincipalNaiara {
	// Examen 9, ejercicio 3

	public static void main(String[] args) {
		System.out.println("INICIO");
		Connection conex = null;
		Statement consulta = null;
		ResultSet resultado = null;
		try {

//			insertarpersonas();

			conex = ConexBD.establecerConexion();
			String consultaStr = "SELECT * FROM personas";
			if (conex == null)
				conex = ConexBD.getCon();
			consulta = conex.createStatement();
			resultado = consulta.executeQuery(consultaStr);
			while (resultado.next()) {
				int id = resultado.getInt(1);
				String nombre = resultado.getString(2);
				String telefono = resultado.getString(3);
				String nifnie = resultado.getString(4);

			}
		} catch (SQLException e) {
			System.out.println("Se ha producido una Excepcion:" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				System.out.println("Cerrando recursos...");
				if (resultado != null)
					resultado.close();
				if (consulta != null)
					consulta.close();
				if (conex != null)
					conex.close();
			} catch (SQLException e) {
				System.out.println("Se ha producido una Excepcion:" + e.getMessage());
				e.printStackTrace();
			}
		}
		System.out.println("FIN");
	}

	public static void insertarpersonas() {

		Connection conex = ConexBD.establecerConexion();
		String consultaInsertStr1 = "insert into datospersonas(id, nombre, telefono, fechanac, nifnie) values (?,?,?,?,?)";
		try {

			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr1);
			for (DatosPersona dp : Datos.PERSONAS) {
				pstmt.setLong(1, dp.getId());
				pstmt.setString(2, dp.getNombre());
				pstmt.setString(3, dp.getTelefono());
				java.sql.Date fechaSQL = new java.sql.Date(0); // .getFechaNac();
				pstmt.setDate(4, fechaSQL);
				pstmt.setString(5, "");
				int resultadoInsercion = pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		}

	}

}
