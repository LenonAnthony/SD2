package Vendas;

import java.io.Serializable;

import Basicos.Carrinho;
import Funcionarios.Funcionario;

public class Venda implements Serializable {
	
	private static final long serialVersionUID = -1468436429614414965L;
	private Carrinho carrinho;
	private Funcionario funcionario;
	private String datahora;
	private boolean aprovado;
		
	public Venda(Carrinho carrinho, Funcionario funcionario, String datahora, boolean aprovado) {
		
		this.carrinho = carrinho;
		this.funcionario = funcionario;
		this.datahora = datahora;
		this.aprovado = aprovado;

	}

	public Funcionario getFuncionario() {

		return funcionario;

	}

	public void setFuncionario(Funcionario funcionario) {

		this.funcionario = funcionario;

	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public String getDatahora() {

		return datahora;

	}

	public void setDatahora(String datahora) {

		this.datahora = datahora;

	}

	public boolean isAprovado() {

		return aprovado;

	}

	public void setAprovado(boolean aprovado) {

		this.aprovado = aprovado;

	}

	// FALTANDO ESSE M�TODO.
	public void finalizaVenda(Venda v) {
		v.setAprovado(true);

	}

	@Override
	public boolean equals(Object obj) {

		boolean resultado = false;

		if (obj instanceof Venda) {

			Venda v = (Venda) obj;

			if (this.funcionario.equals(v.funcionario) && this.datahora.equals(v.datahora)
					&& this.aprovado == v.aprovado) {

				resultado = true;

			}

		}

		return resultado;
 
	}

	@Override 
	public String toString() {

		return "Venda: " + this.carrinho.getProdutos().toString() + " | Valor Final:"
				+ this.getCarrinho().getValorTotal() + " | Funcionario Encarregado: " + this.getFuncionario().getNome();

	}
	
	public String toStringV() {
	
		String aux = "";
		
		for(int i = 0; i< this.carrinho.getProdutos().size(); i++) {
			
			aux += carrinho.getProdutos().get(i).getNome();
			aux += " ";
			aux += carrinho.getProdutos().get(i).getQuantidade();
			aux += " ";
			aux += carrinho.getProdutos().get(i).getPreco();
			aux += " ";
			
		}
		
		System.out.println(aux);
		
        return carrinho.getCliente().getNome() + " " + carrinho.getCliente().getCpf() +  " " 
        + funcionario.getNome() + " " + funcionario.getCpf() + " " + datahora + " " + carrinho.gerarValorTotal() + 
        " " + aprovado + " " + aux;

    }

}