package Produtos;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CPInterface extends Remote {
	
	public void cadastrar(Produto p) throws RemoteException;
	
	public void descadastrar(Produto p) throws RemoteException;
	
	public Produto pegaPeloNome(String p) throws RemoteException;
	
	public Produto procurar(Produto p) throws RemoteException;
	
	public boolean existe(Produto p) throws RemoteException;
	
	public void remover(Produto p) throws RemoteException;
	
	public RepositorioProdutos getRepositorioProdutos() throws RemoteException;
	
	public void setRepositorioProdutos(RepositorioProdutos repositorioProdutos) throws RemoteException;
	
	public int getTamanho() throws RemoteException;
	
	public void setTamanho(int tamanho) throws RemoteException;
	
	public void atualiza() throws RemoteException;
}