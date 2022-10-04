package Funcionarios;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CFInterface extends Remote {
//s
	public void cadastrar(Funcionario f) throws RemoteException;

	public void descadastrar(Funcionario f) throws RemoteException;

	public Funcionario procurar(Funcionario f) throws RemoteException;

	public Funcionario procurarSenha(Funcionario f) throws RemoteException;

	public boolean existe(Funcionario f) throws RemoteException;

	public boolean existeLoginSenha(Funcionario f) throws RemoteException;

	public void remover(Funcionario f) throws RemoteException;

	public RepositorioFuncionarios getRepositorioFuncionario() throws RemoteException;

	public void setRepositorioFuncionario(RepositorioFuncionarios repositorioFuncionario) throws RemoteException;

	public int getTamanho() throws RemoteException;

	public void setTamanho(int tamanho) throws RemoteException;
	
	public void atualiza() throws RemoteException;
}