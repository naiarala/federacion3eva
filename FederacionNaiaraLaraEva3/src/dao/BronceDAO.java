package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import entidades.Bronce;
import entidades.Plata;
import utils.ConexBD;

public class BronceDAO implements operacionesCRUD<Bronce> {

	Connection conex;

	public BronceDAO(Connection conex) {
		if (this.conex == null)
			this.conex = conex;
	}
	@Override
	public boolean insertarConID(Bronce b) {
		boolean ret = false;

		String consultaInsertStr = "insert into Bronce(idBronce, pureza, idMaxPureza) values (?,?,?)";
		try {
			if (this.conex == null || this.conex.isClosed())
				this.conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			pstmt.setLong(1, b.getId());
			pstmt.setFloat(2, b.getPureza());
			pstmt.setLong(3, Bronce.getIdMaxPureza());
			int resultadoInsercion = pstmt.executeUpdate();
			ret = (resultadoInsercion == 1);

		} catch (SQLException e) {
			System.out.println("Se ha producido una SQLException:" + e.getMessage());
			e.printStackTrace();
		}

		return ret;
	}

	@Override
	public long insertarSinID(Bronce b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Bronce buscarPorID(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Bronce> buscarTodos() {
		List<Bronce> todos = new ArrayList<>();
		String consultaInsertStr = "select * FROM bronces";
		try {
			if (this.conex == null || this.conex.isClosed())
				this.conex = ConexBD.establecerConexion();
			PreparedStatement pstmt = conex.prepareStatement(consultaInsertStr);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				Bronce bronce;
				long idBD = result.getLong("id");
				long idBronce = result.getLong("id");
				float pureza = result.getFloat("pureza"); 
//				float idMaxPureza = result.getFloat("idMaxPureza");
				bronce = new Bronce(idBronce, pureza);
				bronce.setId(idBD);
				bronce.setPureza(pureza);
				bronce.setId(idBronce);
//				oro.setIdMaxPureza(idOro);
				todos.add(bronce);
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
	public boolean modificar(Bronce b) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Bronce b) {
		// TODO Auto-generated method stub
		return false;
	}


}
