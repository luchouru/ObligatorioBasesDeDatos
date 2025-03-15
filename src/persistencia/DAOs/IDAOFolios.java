package persistencia.DAOs;

import java.util.List;

import logica.Folio;
import logica.VOs.VoFolio;
import logica.VOs.VoFolioMaxRev;
import persistencia.ConexionBD.IConexion;
import persistencia.excepciones.ErrorPersistencia;

public interface IDAOFolios {

	public boolean member(IConexion con, String nroFolio) throws ErrorPersistencia;

	public void insert(IConexion con, Folio folio) throws ErrorPersistencia;

	public Folio find(IConexion con, String nroFolio) throws ErrorPersistencia;

	public void delete(IConexion con, String nroFolio) throws ErrorPersistencia;

	public List<VoFolio> listarFolios(IConexion con) throws ErrorPersistencia;

	public boolean esVacio(IConexion con) throws ErrorPersistencia;

	public VoFolioMaxRev folioMasRevisado(IConexion con) throws ErrorPersistencia;

}
