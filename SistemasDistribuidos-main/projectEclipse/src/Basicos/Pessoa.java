package Basicos;

public abstract class Pessoa {

	private String nome;
	private String cpf;

	public Pessoa() {

	}

	public Pessoa(String nome, String cpf) {

		setNome(nome);
		setCpf(cpf);

	}

	public String getNome() {

		return nome;

	}

	public void setNome(String nome) {

		if (nome != null) {

			this.nome = nome;

		}

	}

	public String getCpf() {

		return cpf;

	}

	public void setCpf(String cpf) {

		if (cpf != null) {

			this.cpf = cpf;

		}

	}

	@Override
	public boolean equals(Object obj) {

		boolean resultado = false;

		if (obj instanceof Pessoa) {

			Pessoa p = (Pessoa) obj;

			if (p.nome != null && p.cpf != null && this.nome.equalsIgnoreCase(p.nome)
					&& this.cpf.equalsIgnoreCase(p.cpf)) {

				resultado = true;

			}

		}

		return resultado;

	}

	@Override
	public String toString() {

		return " Nome: " + nome + " | CPF: " + cpf;

	}
	
	public String toStringP()
	{
		return nome + " " + cpf + " ";
	}

}