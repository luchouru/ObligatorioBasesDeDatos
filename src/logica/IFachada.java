package logica;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import logica.VOs.VoFolio;
import logica.VOs.VoFolioMaxRev;
import logica.VOs.VoRevision;
import logica.excepciones.ErrorLogica;
import persistencia.excepciones.ErrorPersistencia;

public interface IFachada extends Remote {
	public void agregarFolio(VoFolio voF) throws RemoteException, ErrorLogica, ErrorPersistencia;

	public void agregarRevision(String codFolio, String desc) throws RemoteException, ErrorLogica, ErrorPersistencia;

	public void borrarFolioRevisiones(String codFolio) throws RemoteException, ErrorLogica, ErrorPersistencia;

	public String darDescripcion(String codFolio, int numRevision)
			throws RemoteException, ErrorLogica, ErrorPersistencia;

	public List<VoFolio> listarFolios() throws RemoteException, ErrorLogica, ErrorPersistencia;

	public List<VoRevision> listarRevisiones(String codFolio) throws RemoteException, ErrorLogica, ErrorPersistencia;

	public VoFolioMaxRev folioMasRevisado() throws RemoteException, ErrorLogica, ErrorPersistencia;
}
