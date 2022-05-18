package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import entidades.Atleta;
import entidades.DatosPersona;
import entidades.Metal;
import entidades.Oro;
import utils.ConexBD;
import utils.Datos;

public class OroDAO implements operacionesCRUD<Oro> {

	Connection conex;

	public OroDAO(Connection conex) {
		if (this.conex == null)
			this.conex = conex;
	}
	@Override
	public boolean insertarConID(Oro o) {
		boolean ret = false;

		String consultaInsertStr = "insert into oro(id, pureza, idMaxPureza) values (?,?,?)";
		try {
			if (this.conex == null || this.conex.isClosed())
				this.conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			pstmt.setLong(1, o.getId());
			pstmt.setFloat(2, o.getPureza());
			pstmt.setLong(3, Oro.getIdMaxPureza());
			int resultadoInsercion = pstmt.executeUpdate();
			ret = (resultadoInsercion == 1);

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		}

		return ret;
	}

	@Override
	public long insertarSinID(Oro o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Oro buscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Oro> buscarTodos() {
		List<Oro> todos = new ArrayList<>();
		String consultaInsertStr = "select * FROM oros";
		try {
			if (this.conex == null || this.conex.isClosed())
				this.conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				Oro oro;
				long idBD = result.getLong("id");
				long idOro = result.getLong("id");
				float pureza = result.getFloat("pureza"); 
//				float idMaxPureza = result.getFloat("idMaxPureza");
				oro = new Oro(idOro, pureza);
				oro.setId(idBD);
				oro.setPureza(pureza);
				oro.setId(idOro);
//				oro.setIdMaxPureza(idOro);
				todos.add(oro);
			}
			if (conex != null)
				conex.close();
		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception:" + e.getMessage());
			e.printStackTrace();
		}
		return todos;
	}

	@Override
	public boolean modificar(Oro o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Oro o) {
		// TODO Auto-generated method stub
		return false;
	}


}
