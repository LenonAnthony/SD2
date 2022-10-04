package Clientes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ControladorCliente extends UnicastRemoteObject implements CCInterface {

	private static final long serialVersionUID = -8936092397883043351L;
	private RepositorioClientes repositorioClientes;
	private int tamanho;

	public ControladorCliente() throws RemoteException {
		this.repositorioClientes = new RepositorioClientes();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("Clientes.txt"));
			for (String line; (line = br.readLine()) != null;) {
				String[] splited = line.split("\s+");
				String nome = splited[0];
				String cpf = splited[1];
				String endereco = splited[2];
				Integer pontos = Integer.parseInt(splited[3]);
				Cliente c = new Cliente(nome, cpf, endereco, pontos);
				repositorioClientes.cadastrarCliente(c);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cadastrar(Cliente c) {
		if (c != null) {
			if (!this.repositorioClientes.existe(c)) {
				this.repositorioClientes.cadastrarCliente(c);
				PrintStream ps;
				try {
					ps = new PrintStream("Clientes.txt");
					for (int i = 0; i < repositorioClientes.getTamanho(); i++) {
						ps.println(repositorioClientes.getClientes().get(i).toStringC());
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				System.out.println("Portanto, criado com sucesso!");
				tamanho = tamanho + 1;
			} else {
				System.out.println("Portanto, n�o foi criado!");
			}
		}
	}

	public void descadastrar(Cliente c) {
		this.repositorioClientes.procurar(c);
		if (c != null) {
			this.repositorioClientes.remover(c);
			tamanho = tamanho - 1;
		} else {

		}

	}

	public Cliente procurar(Cliente c) {
		return this.repositorioClientes.procurar(c);
	}

	public boolean existe(Cliente c) {
		return this.repositorioClientes.existe(c);
	}

	public void remover(Cliente c) {
		this.repositorioClientes.remover(c);
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public RepositorioClientes getRepositorioClientes() {
		return repositorioClientes;
	}

	public void setRepositorioClientes(RepositorioClientes repositorioClientes) {
		this.repositorioClientes = repositorioClientes;
	}
	
	public void atualiza() {
		PrintStream ps;
		try {
			ps = new PrintStream("Clientes.txt");
			for (int i = 0; i < this.repositorioClientes.getTamanho(); i++) {
				ps.println(this.repositorioClientes.getClientes().get(i).toStringC());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return repositorioClientes.toString();
	}

}
