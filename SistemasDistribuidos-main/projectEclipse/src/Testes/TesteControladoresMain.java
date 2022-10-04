package Testes;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Basicos.Carrinho;
import Clientes.Cliente;
import Clientes.ControladorCliente;
import Funcionarios.ControladorFuncionario;
import Funcionarios.Funcionario;
import Produtos.ControladorProduto;
import Produtos.Produto;
import Vendas.ControladorVenda;
import Vendas.Venda;

public class TesteControladoresMain {
	
	
	
	public static void main(String[] args) throws RemoteException {
		String datahora = LocalDateTime.now().toString();
		boolean aprovado = true;
		Cliente c1 = new Cliente("Lenon", "88899933300", "Olinda", 0); 
		Cliente c2 = new Cliente("Joao", "88899933400", "Olinda", 0);
		Cliente c3 = new Cliente("Lenon", "88899933300", "Olinda", 0);
		Produto p1 = new Produto("X-Burger", "descricao", 1, 10, true);
		Produto p2 = new Produto("X-Burger2", "descricao", 2, 12, true);
		Funcionario f1 = new Funcionario("Chagas", "000", "Funcionario", "user", "123");

		ControladorCliente cc = new ControladorCliente();
		ControladorProduto cp = new ControladorProduto();
		ControladorVenda cv = new ControladorVenda();
		ControladorFuncionario cf = new ControladorFuncionario();
		cf.cadastrar(f1);  
		//System.out.println(cf);
		
		ArrayList<Produto> produtos = new ArrayList<>();
		produtos.add(p1);
		produtos.add(p2);
		Carrinho car1 = new Carrinho(c1, produtos);

		
		
		Venda v1 = new Venda(car1, f1, datahora, aprovado);
		Venda v2 = new Venda(car1, f1, datahora, aprovado);

		cv.cadastrar(v1);

		//System.out.println(cv);

		cc.cadastrar(c1);
		cc.cadastrar(c2);
		cc.cadastrar(c3);
		cp.cadastrar(p1);
		cp.cadastrar(p2);
		//System.out.println(cp);
		
		
		System.out.println(v1.toStringV());
		
		
	}
}
