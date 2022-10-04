package Vendas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import Basicos.Carrinho;
import Clientes.Cliente;
import Funcionarios.Funcionario;
import Produtos.Produto;

public class ControladorVenda extends UnicastRemoteObject implements CVInterface {

	private static final long serialVersionUID = -7358390655644495761L;
	private RepositorioVendas repositorioVendas;

	public ControladorVenda() throws RemoteException {
		this.repositorioVendas = new RepositorioVendas();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("vendas.txt"));
			for (String line; (line = br.readLine()) != null;) {
				String[] splited = line.split("\s+");
				String nomeC = splited[0];
				String cpfC = splited[1];
				String nomeF = splited[2];
				String cpfF = splited[3];
				String datahora = splited[4];
				double valorTotal = Double.parseDouble(splited[5]);
				Boolean aprovado = Boolean.parseBoolean(splited[6]);
				ArrayList<Produto> product = new ArrayList<>();
				for (int j = 7; j < splited.length; j++) {
					String nome = (splited[j]);
					j++;
					int quantidade = Integer.parseInt(splited[j]);
					j++;
					double preco = Double.parseDouble(splited[j]);
					Produto p = new Produto(nome, quantidade, preco);
					product.add(p);
				}
				Funcionario f = new Funcionario(nomeF, cpfF);
				Cliente c1 = new Cliente(nomeC, cpfC);
				Carrinho c = new Carrinho(c1, product);
				Venda v = new Venda(c, f, datahora, aprovado);
				repositorioVendas.adicionarVenda(v);
				System.out.println(v.toStringV());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cadastrar(Venda v) {
		if (v != null) {
			if (!this.repositorioVendas.existe(v)) {
				this.repositorioVendas.adicionarVenda(v);
				PrintStream ps;
				try {
					ps = new PrintStream("vendas.txt");
					for (int i = 0; i < repositorioVendas.getTamanho(); i++) {
						ps.println(repositorioVendas.getVendas().get(i).toStringV());
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				System.out.println("Portanto, criado com sucesso!");
			} else {
				System.out.println("Portanto, n�o foi criado!");
			}
		}
	}

	public void descadastrar(Venda v) {
		this.repositorioVendas.procurar(v);
		if (v != null) {
			this.repositorioVendas.remover(v);
		} else {

		}

	}

	public Venda procurar(Venda v) {
		return this.repositorioVendas.procurar(v);
	}

	public boolean existe(Venda v) {
		return this.repositorioVendas.existe(v);
	}

	public void remover(Venda v) {
		this.repositorioVendas.remover(v);
	}

	public RepositorioVendas getRepositorioVendas() {
		return repositorioVendas;
	}

	public void setRepositorioVendas(RepositorioVendas repositorioVendas) {
		this.repositorioVendas = repositorioVendas;
	}

	@Override
	public String toString() {
		return repositorioVendas.toString();
	}
}