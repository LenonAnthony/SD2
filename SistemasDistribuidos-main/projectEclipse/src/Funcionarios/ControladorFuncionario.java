package Funcionarios;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ControladorFuncionario extends UnicastRemoteObject implements CFInterface {

	private static final long serialVersionUID = -5662800212687063957L;
	private RepositorioFuncionarios repositorioFuncionario;
	private int tamanho;

//s
	public ControladorFuncionario() throws RemoteException {
		this.repositorioFuncionario = new RepositorioFuncionarios();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("funcionarios.txt"));
			for (String line; (line = br.readLine()) != null;) {
				String[] splited = line.split("\s+");
				String nome = splited[0];
				String cpf = splited[1];
				String tipo = splited[2];
				String login = splited[3];
				String senha = splited[4];
				Boolean logado = Boolean.parseBoolean(splited[5]);
				Funcionario f = new Funcionario(nome, cpf, tipo, login, senha);
				repositorioFuncionario.cadastrarFuncionario(f);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cadastrar(Funcionario f) {
		if (f != null) {
			if (!this.repositorioFuncionario.existe(f)) {
				this.repositorioFuncionario.cadastrarFuncionario(f);
				PrintStream ps;
				try {
					ps = new PrintStream("funcionarios.txt");
					for (int i = 0; i < repositorioFuncionario.getTamanho(); i++) {
						ps.println(repositorioFuncionario.getFuncionarios().get(i).toStringF());
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

	public void descadastrar(Funcionario f) {
		this.repositorioFuncionario.procurarIndice(f);
		if (f != null) {
			this.repositorioFuncionario.remover(f);
			tamanho = tamanho - 1;
			;
		} else {

		}

	}

	public Funcionario procurar(Funcionario f) {
		return this.repositorioFuncionario.procurarFuncionario(f);
	}

	public Funcionario procurarSenha(Funcionario f) {
		return this.repositorioFuncionario.procurarLoginSenha(f);
	}

	public boolean existe(Funcionario f) {
		return this.repositorioFuncionario.existe(f);
	}

	public boolean existeLoginSenha(Funcionario f) {
		return this.repositorioFuncionario.existeLoginSenha(f);
	}

	public void remover(Funcionario f) {
		this.repositorioFuncionario.remover(f);
	}

	public RepositorioFuncionarios getRepositorioFuncionario() {
		return repositorioFuncionario;
	}

	public void setRepositorioFuncionario(RepositorioFuncionarios repositorioFuncionario) {
		this.repositorioFuncionario = repositorioFuncionario;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	
	public void atualiza() {
		PrintStream ps;
		try {
			ps = new PrintStream("funcionarios.txt");
			for (int i = 0; i < this.repositorioFuncionario.getTamanho(); i++) {
				ps.println(this.repositorioFuncionario.getFuncionarios().get(i).toStringF());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return repositorioFuncionario.toString();
	}

}
