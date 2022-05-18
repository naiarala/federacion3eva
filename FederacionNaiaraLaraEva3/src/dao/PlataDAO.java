package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import entidades.Oro;
import entidades.Plata;
import utils.ConexBD;

public class PlataDAO implements operacionesCRUD<Plata> {

	Connection conex;

	public PlataDAO(Connection conex) {
		if (this.conex == null)
			this.conex = conex;
	}
	@Override
	public boolean insertarConID(Plata p) {
		boolean ret = false;

		String consultaInsertStr = "insert into plata(idPlata, pureza, idMaxPureza) values (?,?,?)";
		try {
			if (this.conex == null || this.conex.isClosed())
				this.conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			pstmt.setLong(1, p.getId());
			pstmt.setFloat(2, p.getPureza());
			pstmt.setLong(3, Plata.getIdMaxPureza());
			int resultadoInsercion = pstmt.executeUpdate();
			ret = (resultadoInsercion == 1);

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		}

		return ret;
	}

	@Override
	public long insertarSinID(Plata p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Plata buscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Plata> buscarTodos() {
		List<Plata> todos = new ArrayList<>();
		String consultaInsertStr = "select * FROM platas";
		try {
			if (this.conex == null || this.conex.isClosed())
				this.conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				Plata plata;
				long idBD = result.getLong("id");
				long idPlata = result.getLong("id");
				float pureza = result.getFloat("pureza"); 
//				float idMaxPureza = result.getFloat("idMaxPureza");
				plata = new Plata(idPlata, pureza);
				plata.setId(idBD);
				plata.setPureza(pureza);
				plata.setId(idPlata);
//				oro.setIdMaxPureza(idOro);
				todos.add(plata);
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
	public boolean modificar(Plata p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Plata p) {
		// TODO Auto-generated method stub
		return false;
	}


}

