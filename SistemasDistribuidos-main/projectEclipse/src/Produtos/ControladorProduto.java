package Produtos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ControladorProduto extends UnicastRemoteObject implements CPInterface {

	private static final long serialVersionUID = -4086474365074661265L;
	private RepositorioProdutos repositorioProdutos;
	private int tamanho;

	public ControladorProduto() throws RemoteException {
		this.repositorioProdutos = new RepositorioProdutos();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("produtos.txt"));
			for (String line; (line = br.readLine()) != null;) {
				String[] splited = line.split("\s+");
				String nome = splited[0];
				String descricao = splited[1];
				int quantidade = Integer.parseInt(splited[2]);
				double preco = Double.parseDouble(splited[3]);
				Boolean estoque = Boolean.parseBoolean(splited[4]);
				Produto p = new Produto(nome, descricao, quantidade, preco, estoque);
				repositorioProdutos.cadastrarProduto(p);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cadastrar(Produto p) {
		if (p != null) {
			if (!this.repositorioProdutos.existe(p)) {
				this.repositorioProdutos.cadastrarProduto(p);
				PrintStream ps;
				try {
					ps = new PrintStream("produtos.txt");
					for (int i = 0; i < repositorioProdutos.getTamanho(); i++) {
						ps.println(repositorioProdutos.getProdutos().get(i).toStringP());
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

	public void descadastrar(Produto p) {
		this.repositorioProdutos.procurar(p);
		if (p != null) {
			this.repositorioProdutos.remover(p);
			tamanho = tamanho - 1;
		} else {

		}

	}

	public Produto pegaPeloNome(String p) {
		return this.repositorioProdutos.pegaPeloNome(p);
	}

	public Produto procurar(Produto p) {
		return this.repositorioProdutos.procurar(p);
	}

	public boolean existe(Produto p) {
		return this.repositorioProdutos.existe(p);
	}

	public void remover(Produto p) {
		this.repositorioProdutos.remover(p);
	}

	public RepositorioProdutos getRepositorioProdutos() {
		return repositorioProdutos;
	}

	public void setRepositorioProdutos(RepositorioProdutos repositorioProdutos) {
		this.repositorioProdutos = repositorioProdutos;
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
			ps = new PrintStream("produtos.txt");
			for (int i = 0; i < this.repositorioProdutos.getTamanho(); i++) {
				ps.println(this.repositorioProdutos.getProdutos().get(i).toStringP());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return repositorioProdutos.toString();
	}

}
