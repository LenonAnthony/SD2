package Clientes;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CCInterface extends Remote{
	public void cadastrar(Cliente c) throws RemoteException;
	
	public void descadastrar(Cliente c) throws RemoteException;
	
	public Cliente procurar(Cliente c) throws RemoteException;
	
	public boolean existe(Cliente c) throws RemoteException;
	
	public void remover(Cliente c) throws RemoteException;
	
	public int getTamanho() throws RemoteException;
	
	public void setTamanho(int tamanho) throws RemoteException;
	
	public RepositorioClientes getRepositorioClientes() throws RemoteException;
	
	public void setRepositorioClientes(RepositorioClientes repositorioClientes) throws RemoteException;
	
	public void atualiza() throws RemoteException;
}