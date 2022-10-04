package Basicos;

import java.io.Serializable;
import java.util.ArrayList;
import Clientes.Cliente;
import Produtos.Produto;

public class Carrinho implements Serializable {
	
	private static final long serialVersionUID = -6233260944452945068L;
	private Cliente cliente;
	private ArrayList<Produto> produtos;
	private double valorTotal;

	public Carrinho(Cliente cliente, ArrayList<Produto> produtos) {

		this.cliente = cliente;
		this.produtos = produtos;

	}

	public Cliente getCliente() {

		return cliente;

	} 

	public void setCliente(Cliente cliente) {

		this.cliente = cliente;

	}

	public double getValorTotal() {

		return valorTotal;

	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public double gerarValorTotal() {

		double valorTotal = 0;
		for (int i = 0; i < produtos.size(); i++) {
			valorTotal = valorTotal + produtos.get(i).getPrecoFinal();
		}
		return valorTotal;
	}

	public ArrayList<Produto> getProdutos() {

		return produtos;

	}

	public void adicionarItem(Produto p) {

		produtos.add(p);

	}

	public double calcularTotal() {

		double resultado = 0;

		for (Produto p : produtos) {

			resultado += p.getPreco();

		}

		return resultado;

	}

	public String toString() {

		return "Carrinho: [ Cliente: " + this.getCliente() + "| Produtos: " + this.getProdutos() + "| Valor: "
				+ this.getValorTotal();

	}
}