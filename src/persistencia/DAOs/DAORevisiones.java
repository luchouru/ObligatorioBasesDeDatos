package persistencia.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.Revision;
import logica.VOs.VoRevision;
import persistencia.ConexionBD.Conexion;
import persistencia.ConexionBD.IConexion;
import persistencia.consultas.Consultas;
import persistencia.excepciones.ErrorPersistencia;

public class DAORevisiones implements IDAORevisiones {

	private String codigoFolio;
	private Consultas cons = new Consultas();

	public DAORevisiones(String codigoFolio) {
		super();
		this.codigoFolio = codigoFolio;
	}

	@Override
	public void insback(IConexion con, Revision rev) throws ErrorPersistencia {
		String query = this.cons.insertarRevision();

		try {
			PreparedStatement st1 = ((Conexion) con).getConnection().prepareStatement(query);
			st1.setInt(1, rev.getNumero());
			st1.setString(2, codigoFolio);
			st1.setString(3, rev.getDescripcion());

			st1.executeUpdate();
			st1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ErrorPersistencia("Error en la conexión al servidor");
		}
	}

	@Override
	public int largo(IConexion con) throws ErrorPersistencia {
		int cant = 0;
		String query = this.cons.cantRevisionesDeFolio();

		try {

			PreparedStatement st1 = ((Conexion) con).getConnection().prepareStatement(query);
			st1.setString(1, codigoFolio);
			ResultSet rs = st1.executeQuery();

			if (rs.next()) {

				cant = rs.getInt(1);
			}

			rs.close();
			st1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ErrorPersistencia("Error en la conexión al servidor");
		}

		return cant;
	}

	@Override
	public Revision kesimo(IConexion con, int numRev) throws ErrorPersistencia {
		Revision revision = null;
		int posTemp = 0;
		String query = this.cons.listarRevisiones();

		try {
			PreparedStatement st1 = ((Conexion) con).getConnection().prepareStatement(query);
			st1.setString(1, codigoFolio);

			ResultSet rs = st1.executeQuery();

			while (rs.next() && posTemp <= numRev) {
				if (posTemp == numRev) {
					revision = new Revision(rs.getInt(1), rs.getString(2));
				} else {
					posTemp++;
				}
			}

			rs.close();
			st1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ErrorPersistencia("Error en la conexión al servidor");
		}

		return revision;
	}

	@Override
	public List<VoRevision> listarRevisiones(IConexion con) throws ErrorPersistencia {
		List<VoRevision> revisiones = new ArrayList<VoRevision>();
		String query = this.cons.listarRevisiones();

		try {
			PreparedStatement st1 = ((Conexion) con).getConnection().prepareStatement(query);
			st1.setString(1, codigoFolio);

			ResultSet rs = st1.executeQuery();

			while (rs.next()) {
				revisiones.add(new VoRevision(rs.getInt(1), rs.getString(3), rs.getString(2)));
			}

			rs.close();
			st1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ErrorPersistencia("Error en la conexión al servidor");
		}

		return revisiones;
	}

	@Override
	public void borrarRevisiones(IConexion con) throws ErrorPersistencia {
		String query = this.cons.eliminaRevisionesDeFolio();

		try {
			PreparedStatement st1 = ((Conexion) con).getConnection().prepareStatement(query);
			st1.setString(1, codigoFolio);

			st1.executeUpdate();
			st1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ErrorPersistencia("Error en la conexión al servidor");
		}

	}

}
