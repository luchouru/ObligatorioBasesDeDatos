package persistencia.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logica.Folio;
import logica.VOs.VoFolio;
import logica.VOs.VoFolioMaxRev;
import persistencia.ConexionBD.Conexion;
import persistencia.ConexionBD.IConexion;
import persistencia.consultas.Consultas;
import persistencia.excepciones.ErrorPersistencia;
import persistencia.excepciones.ErroresPersistencia;

public class DAOFolios implements IDAOFolios {

	private Consultas cons = new Consultas();

	public DAOFolios() {
	}

	@Override
	public boolean member(IConexion con, String nroFolio) throws ErrorPersistencia {
		boolean existe = false;
		String query = this.cons.existeFolio();

		try {
			PreparedStatement st1 = ((Conexion) con).getConnection().prepareStatement(query);
			st1.setString(1, nroFolio);

			ResultSet rs = st1.executeQuery();

			if (rs.next()) {
				existe = true;
			}

			rs.close();
			st1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ErrorPersistencia(ErroresPersistencia.ERROR_CONEXION.getMsg());
		}

		return existe;
	}

	@Override
	public void insert(IConexion con, Folio folio) throws ErrorPersistencia {
		String query = this.cons.insertarFolio();

		try {
			PreparedStatement st1 = ((Conexion) con).getConnection().prepareStatement(query);
			st1.setString(1, folio.getCodigo());
			st1.setString(2, folio.getCaratula());
			st1.setInt(3, folio.getPaginas());

			st1.executeUpdate();
			st1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ErrorPersistencia(ErroresPersistencia.ERROR_CONEXION.getMsg());
		}
	}

	@Override
	public Folio find(IConexion con, String nroFolio) throws ErrorPersistencia {
		Folio folio = null;
		String query = this.cons.existeFolio();

		try {
			PreparedStatement st1 = ((Conexion) con).getConnection().prepareStatement(query);
			st1.setString(1, nroFolio);

			ResultSet rs = st1.executeQuery();

			if (rs.next()) {
				folio = new Folio(rs.getString(1), rs.getString(2), rs.getInt(3));
			}

			rs.close();
			st1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ErrorPersistencia(ErroresPersistencia.ERROR_CONEXION.getMsg());
		}

		return folio;
	}

	@Override
	public void delete(IConexion con, String nroFolio) throws ErrorPersistencia {
		String query = this.cons.eliminaFolio();

		try {
			PreparedStatement st1 = ((Conexion) con).getConnection().prepareStatement(query);
			st1.setString(1, nroFolio);

			st1.executeUpdate();
			st1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ErrorPersistencia(ErroresPersistencia.ERROR_CONEXION.getMsg());
		}
	}

	@Override
	public List<VoFolio> listarFolios(IConexion con) throws ErrorPersistencia {
		List<VoFolio> folios = new ArrayList<VoFolio>();
		String query = this.cons.listarFolios();

		try {
			PreparedStatement st1 = ((Conexion) con).getConnection().prepareStatement(query);

			ResultSet rs = st1.executeQuery();

			while (rs.next()) {
				folios.add(new VoFolio(rs.getString(1), rs.getString(2), rs.getInt(3)));
			}

			rs.close();
			st1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ErrorPersistencia(ErroresPersistencia.ERROR_CONEXION.getMsg());
		}

		return folios;
	}

	@Override
	public boolean esVacio(IConexion con) throws ErrorPersistencia {
		return this.listarFolios(con).isEmpty();
	}

	@Override
	public VoFolioMaxRev folioMasRevisado(IConexion con) throws ErrorPersistencia {

		VoFolioMaxRev folioMax = null;
		String query = this.cons.listarFolios();
		int mayorCant = -1;

		try {
			PreparedStatement st1 = ((Conexion) con).getConnection().prepareStatement(query);

			ResultSet rs = st1.executeQuery();

			while (rs.next()) {
				Folio temp = new Folio(rs.getString(1), rs.getString(2), rs.getInt(3));
				int revs = temp.cantidadRevisiones(con);
				if (revs > mayorCant) {
					mayorCant = revs;
					folioMax = new VoFolioMaxRev(temp.getCodigo(), temp.getCaratula(), temp.getPaginas(), revs);
				}
			}

			rs.close();
			st1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ErrorPersistencia(ErroresPersistencia.ERROR_CONEXION.getMsg());
		}

		return folioMax;
	}
}
