package Vendas;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CVInterface extends Remote {
	
	public void cadastrar(Venda v) throws RemoteException;
	
	public void descadastrar(Venda v) throws RemoteException;
	
	public Venda procurar(Venda v) throws RemoteException;
	
	public boolean existe(Venda v) throws RemoteException;
	
	public void remover(Venda v) throws RemoteException;
	
	public RepositorioVendas getRepositorioVendas() throws RemoteException;
	
	public void setRepositorioVendas(RepositorioVendas repositorioVendas) throws RemoteException;
}