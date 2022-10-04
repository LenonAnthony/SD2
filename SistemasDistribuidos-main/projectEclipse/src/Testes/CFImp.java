package Testes;

import java.rmi.Naming;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Basicos.Carrinho;
import Clientes.CCInterface;
import Clientes.Cliente;
import Clientes.ControladorCliente;
import Funcionarios.CFInterface;
import Funcionarios.ControladorFuncionario;
import Funcionarios.Funcionario;
import Produtos.CPInterface;
import Produtos.ControladorProduto;
import Produtos.Produto;
import Vendas.CVInterface;
import Vendas.ControladorVenda;
import Vendas.Venda;

public class CFImp {
	private static CFInterface cf1;
	private static ControladorFuncionario f;
	
	private static CPInterface cp1;
	private static ControladorProduto p;
	
	private static CCInterface cc1;
	private static ControladorCliente c;
	
	private static CVInterface cv1;
	private static ControladorVenda v;
	

	public static void main(String[] args) throws Exception {
		
		
		Cliente c1 = new Cliente("Chagas", "88899933300", "Olinda", 0);
		Cliente c2 = new Cliente("Joao33", "88899933400", "Olinda", 0);
		Cliente c3 = new Cliente("Lenon11", "88899933300", "Olinda", 0);
		
		c = new ControladorCliente();
		
		cc1 = (CCInterface) Naming.lookup("rmi://localhost:1101/CC");
		Naming.rebind("rmi://localhost:1101/CC", c);
		c.cadastrar(c1);
		c.cadastrar(c2);
		c.cadastrar(c3);
		System.out.println(cc1.getRepositorioClientes().getClientes());
		System.out.println(c);   
		
		
		Produto p1 = new Produto("X-Burger33", "descricao", 1, 35, true);
		Produto p2 = new Produto("X-Burger2", "descricao", 2, 44, true);
		Produto p3 = new Produto("Cabra2211", "cabrinha", 3, 30, false);
		
		
		p = new ControladorProduto();
		cp1 = (CPInterface) Naming.lookup("rmi://localhost:1100/CP");
		Naming.rebind("rmi://localhost:1100/CP", p);
		p.cadastrar(p1);
		p.cadastrar(p2);
		p.cadastrar(p3);
		System.out.println(cp1.getRepositorioProdutos().getProdutos().get(0).toStringP());
		
		ArrayList<Produto> produtos = new ArrayList<>();
		produtos.add(p1);
		produtos.add(p2);
		
		Carrinho car1 =  new Carrinho(c3, produtos);
//s
		f = new ControladorFuncionario();
		cf1 = (CFInterface) Naming.lookup("rmi://localhost:1099/CF");
		Funcionario f1 = new Funcionario("Chagas11", "000", "Funcionario", "user", "123");
		Funcionario f2 = new Funcionario("Joao", "0300", "Gerente", "user1", "1234");
		Funcionario f3 = new Funcionario("Joao233", "0300", "Gerente", "user1", "1234");
		Naming.rebind("rmi://localhost:1099/CF", f);
		f.cadastrar(f1);
		f.cadastrar(f3);
		System.out.println(cf1.getRepositorioFuncionario().getFuncionarios());
		
		
		String datahora = LocalDateTime.now().toString();
		
		boolean aprovado = true;
		
		
		v = new ControladorVenda();
		cv1 = (CVInterface) Naming.lookup("rmi://localhost:1102/CV");
		Venda v1 = new Venda(car1, f1, datahora, aprovado);
		Naming.rebind("rmi://localhost:1102/CV", v);
		v.cadastrar(v1);
		System.out.println(cv1.getRepositorioVendas().getVendas());
		
		
	}

}