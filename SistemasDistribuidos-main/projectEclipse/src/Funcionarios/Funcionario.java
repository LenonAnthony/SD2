package Funcionarios;

import java.io.Serializable;
import java.util.Objects;

import Basicos.Pessoa;

public class Funcionario extends Pessoa implements Serializable {
	
	private static final long serialVersionUID = -4937811051786662973L;
	private String nome;
	private String cpf;
	private String login;
	private String senha;
	private String tipo;
	private boolean logado;

	public Funcionario() {
		
	}

	public Funcionario(String nome, String cpf, String tipo, String login, String senha){

		setNome(nome);
		setCpf(cpf);
		setTipo(tipo);
		setLogin(login);
		setSenha(senha);
	}
	
	public Funcionario(String nome, String cpf) {
		
		setNome(nome);
		setCpf(cpf);
		setTipo("");
		setLogin("");
		setSenha("");
		
	}

	public boolean getLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public String getLogin() {

		return login;

	}

	public void setLogin(String login) {

		if (login != null) {

			this.login = login;

		}

	}

	public String getSenha() {

		return senha;

	}

	public void setSenha(String senha) {

		if (senha != null) {

			this.senha = senha;

		}

	}

	public String getTipo() {

		return tipo;

	}

	public void setTipo(String tipo) {

		if (tipo.equalsIgnoreCase("Funcionario") || tipo.equalsIgnoreCase("Gerente")) {

			this.tipo = tipo;

		}

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean verificaAcesso(Object obj) {

		boolean resultado = false;

		if (obj instanceof Funcionario) {

			Funcionario f = (Funcionario) obj;
			// Depois adicionar CPF.
			if (this.login.equals(f.login) && this.senha.equals(f.senha)) {

				resultado = true;
			}
		}

		return resultado;

	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, logado, login, nome, senha, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		return Objects.equals(cpf, other.cpf) && logado == other.logado && Objects.equals(login, other.login)
				&& Objects.equals(nome, other.nome) && Objects.equals(senha, other.senha)
				&& Objects.equals(tipo, other.tipo);
	}

	@Override
	public String toString() {

		return "Nome: " + nome + " | CPF: " + cpf + " | Tipo: " + tipo + " | Login: " + login + " | Senha: " + senha
				+ " | Logado: " + this.getLogado();

	}
	
	public String toStringF() {

        return  nome + " " + cpf + " " + tipo + " " + login + " " + senha+ " " + this.getLogado();

    }

}