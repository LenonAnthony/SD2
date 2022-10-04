package Funcionarios;

import java.io.Serializable;
import java.util.ArrayList;

public class RepositorioFuncionarios implements Serializable {
	
	private static final long serialVersionUID = -1919414877885418528L;
	private ArrayList<Funcionario> funcionarios = new ArrayList<>();
	private int tamanho;

	public RepositorioFuncionarios() {
		System.out.println(this.funcionarios);
	}

	public void cadastrarFuncionario(Funcionario f) {
		this.funcionarios.add(f);
		tamanho = this.funcionarios.size();
	}

	public int procurarIndiceLoginSenha(Funcionario f) {
		int i = 0;
		boolean achou = false;
		while ((!achou) && (i < tamanho)) {
			if (f.verificaAcesso(this.funcionarios.get(i))) {
				achou = true;
			} else {
				i++;
			}
		}
		return i;
	}

	public int procurarIndice(Funcionario f) {
		int i = 0;
		boolean achou = false;
		while ((!achou) && (i < tamanho)) {
			if (f.equals(this.funcionarios.get(i))) {
				achou = true;
			} else {
				i++;
			}
		}
		return i;
	}

	public Funcionario procurarLoginSenha(Funcionario f) {
		int i = this.procurarIndiceLoginSenha(f);
		Funcionario resultado = null;
		if (i != this.funcionarios.size()) {
			resultado = f;

		}
		return resultado;
	}

	public Funcionario procurarFuncionario(Funcionario f) {
		int i = this.procurarIndice(f);
		Funcionario resultado = null;
		if (i != this.funcionarios.size()) {
			resultado = f;

		}
		return resultado;
	}

	public boolean existe(Funcionario f) {
		boolean existe = false;
		int indice = this.procurarIndice(f);
		if (indice != tamanho) {
			existe = true;
			System.out.println("A conta existe");
		} else {
			System.out.println("A conta n�o existe");
		}
		return existe;
	}

	public boolean existeLoginSenha(Funcionario f) {
		boolean existe = false;
		int indice = this.procurarIndiceLoginSenha(f);
		if (indice != tamanho) {
			existe = true;
			System.out.println("A conta existe");
		} else {
			System.out.println("A conta n�o existe");
		}
		return existe;
	}

	public void remover(Funcionario f) {
		int i = this.procurarIndice(f);
		if (i != tamanho) {
			this.funcionarios.remove(i);
			System.out.println("Funcionario removido.");
		} else {
			System.out.println("Funcionario n�o encontrado. Portanto, n�o foi removido.");
		}
		tamanho = this.funcionarios.size();
	}
	
	public ArrayList<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public String toString() {
		return "Funcionarios: " + funcionarios;
	}

}