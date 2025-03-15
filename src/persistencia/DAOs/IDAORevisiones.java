package persistencia.DAOs;

import java.util.List;

import logica.Revision;
import logica.VOs.VoRevision;
import persistencia.ConexionBD.IConexion;
import persistencia.excepciones.ErrorPersistencia;

public interface IDAORevisiones {

	public void insback(IConexion con, Revision rev) throws ErrorPersistencia;

	public int largo(IConexion con) throws ErrorPersistencia;

	public Revision kesimo(IConexion con, int numRev) throws ErrorPersistencia;

	public List<VoRevision> listarRevisiones(IConexion con) throws ErrorPersistencia;

	public void borrarRevisiones(IConexion con) throws ErrorPersistencia;

}
