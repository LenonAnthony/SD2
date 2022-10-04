package Produtos;

import java.io.Serializable;
import java.util.ArrayList;

public class RepositorioProdutos implements Serializable {

	private static final long serialVersionUID = -7693241962439731138L;
	ArrayList<Produto> produtos = new ArrayList<>();
	private int tamanho;

	public RepositorioProdutos() {
		System.out.println(this.produtos);
	}

	public void cadastrarProduto(Produto p) {
		this.produtos.add(p);
		tamanho = this.produtos.size();
	}

	private int procurarIndice(Produto p) {
		int i = 0;
		boolean achou = false;
		while ((!achou) && (i < tamanho)) {
			if (p.equals(this.produtos.get(i))) {
				achou = true;
			} else {
				i++;
			}
		}
		return i;
	}

	public Produto procurar(Produto p) {
		int i = this.procurarIndice(p);
		Produto resultado = null;
		if (i != this.produtos.size()) {
			resultado = p;

		}
		return resultado;
	}

	public boolean existe(Produto p) {
		boolean existe = false;
		int indice = this.procurarIndice(p);
		if (indice != tamanho) {
			existe = true;
			System.out.println("A conta existe");
		} else {
			System.out.println("A conta n�o existe");
		}
		return existe;
	}

	public void remover(Produto p) {
		int i = this.procurarIndice(p);
		if (i != tamanho) {
			this.produtos.remove(i);
			System.out.println("Cliente removido.");
		} else {
			System.out.println("Cliente n�o encontrado. Portanto, n�o foi removido.");
		}
		tamanho = this.produtos.size();
	}
	
	public Produto pegaPeloNome(String nome) {

		for (int i = 0; i < this.produtos.size(); i++) {
			if (this.produtos.get(i).getNome().equals(nome)) {
				return this.produtos.get(i);
			}
		}

		return null;

	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public String toString() {
		return "Produtos: " + produtos;
	}

}
