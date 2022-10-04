package Clientes;

import java.io.Serializable;
import java.util.ArrayList;

public class RepositorioClientes implements Serializable {

	private static final long serialVersionUID = 1324200405309612664L;
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	private int tamanho;

	public RepositorioClientes() {
		System.out.println(this.clientes);
	}

	public void cadastrarCliente(Cliente c) {
		this.clientes.add(c);
		tamanho = this.clientes.size();
	}

	private int procurarIndice(Cliente c) {
		int i = 0;
		boolean achou = false;
		while ((!achou) && (i < tamanho)) {
			if (c.equals(this.clientes.get(i))) {
				achou = true;
			} else {
				i++;
			}
		}
		return i;
	}

	public Cliente procurar(Cliente c) {
		int i = this.procurarIndice(c);
		Cliente resultado = null;
		if (i != this.clientes.size()) {
			resultado = c;

		}
		return resultado;
	}

	public boolean existe(Cliente c) {
		boolean existe = false;
		int indice = this.procurarIndice(c);
		if (indice != tamanho) {
			existe = true;
			System.out.println("A conta existe");
		} else {
			System.out.println("A conta n�o existe");
		}
		return existe;
	}

	public void remover(Cliente c) {
		int i = this.procurarIndice(c);
		if (i != tamanho) {
			this.clientes.remove(i);
			System.out.println("Cliente removido.");
		} else {
			System.out.println("Cliente n�o encontrado. Portanto, n�o foi removido.");
		}
		tamanho = this.clientes.size();
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public String toString() {
		return "Clientes: " + clientes;
	}

}
