package Vendas;

import java.io.Serializable;
import java.util.ArrayList;

public class RepositorioVendas implements Serializable {
	
	private static final long serialVersionUID = 7221116099443829541L;
	ArrayList<Venda> vendas = new ArrayList<>();
	private int tamanho;
		
	public RepositorioVendas(){	
	
	}
	
	public void adicionarVenda(Venda v) {
		this.vendas.add(v);
		tamanho = this.vendas.size();
	}

	private int procurarIndice(Venda v) {
		int i = 0;
		boolean achou = false;
		while ((!achou) && (i < tamanho)) {
			if (v.equals(this.vendas.get(i))) {
				achou = true;
			} else {
				i++;
			}
		}
		return i;
	}

	
	public Venda procurar(Venda v) {
		int i = this.procurarIndice(v);
		Venda resultado = null;
		if (i != this.vendas.size()) {
			resultado = v;
		}
		return resultado;
	}

	public boolean existe(Venda v) {
		boolean existe = false;
		int indice = this.procurarIndice(v);
		if (indice != tamanho) {
			existe = true;
			System.out.println("A conta existe");
		} else {
			System.out.println("A conta n�o existe");
		}
		return existe;
	}

	public void remover(Venda v) {
		int i = this.procurarIndice(v);
		if (i != tamanho) {
			this.vendas.remove(i);
			System.out.println("Cliente removido.");
		} else {
			System.out.println("Cliente n�o encontrado. Portanto, n�o foi removido.");
		}
		tamanho = this.vendas.size();
	}

	public String toString() {
		return "Vendas: "+vendas;
	}

	public ArrayList<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(ArrayList<Venda> vendas) {
		this.vendas = vendas;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

}