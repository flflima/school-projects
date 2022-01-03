package servidor;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Agenda extends Remote {
	
	public void iniciar() throws RemoteException, IOException;

	public void ordenar() throws RemoteException;

	public void editar() throws RemoteException;

	public void salvar(String nome, String end, String tel,
			String email) throws RemoteException, IOException;

	public void incluir() throws RemoteException;

	public void excluir() throws RemoteException, IOException;

	public String[] atualizar() throws RemoteException;

	public String[] voltar() throws RemoteException;

	public String[] avancar() throws RemoteException;
	
	public boolean cheia() throws RemoteException;

	public boolean vazia() throws RemoteException;
	
	public boolean getIncluir() throws RemoteException;

}
