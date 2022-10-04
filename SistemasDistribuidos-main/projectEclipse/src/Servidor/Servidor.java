package Servidor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import Clientes.ControladorCliente;
import Funcionarios.ControladorFuncionario;
import Produtos.ControladorProduto;
import Vendas.ControladorVenda;

public class Servidor {
	
	public static void main(String[] args) throws Exception {
		
		try {
			
			Registry registry1 = LocateRegistry.createRegistry(1099);
			registry1.rebind("CF", new ControladorFuncionario());
			Registry registry2 = LocateRegistry.createRegistry(1100);
			registry2.rebind("CP", new ControladorProduto());
			Registry registry3 = LocateRegistry.createRegistry(1101);
			registry3.rebind("CC", new ControladorCliente());
			Registry registry4 = LocateRegistry.createRegistry(1102);
			registry4.rebind("CV", new ControladorVenda());
			
		} catch (RemoteException ex) {
			ex.printStackTrace();
			return;
		}
		
		System.out.println("Servidor Iniciado"); 
	}

} 