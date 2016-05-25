package br.com.resource.catalogoconhecimento.bean;

import java.util.List;

public class FuncionarioBean {

	private int id;
	private CargoBean cargo;
	private String nome;
	private String telefone;
	private String nomeUser;
	private String email;
	private List<TecnologiaBean> tecnologias;




	public FuncionarioBean() {

	}

	public FuncionarioBean(int idFuncionario, CargoBean cargo, String nomeFuncionario, String telefone, String nomeUser,
			String email) {

		this.id = idFuncionario;
		this.cargo = cargo;
		this.nome = nomeFuncionario;
		this.telefone = telefone;
		this.nomeUser = nomeUser;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int idFuncionario) {
		this.id = idFuncionario;
	}

	public CargoBean getCargo() {
		return cargo;
	}

	public void setCargo(CargoBean cargo) {
		this.cargo = cargo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nomeFuncionario) {
		this.nome = nomeFuncionario;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNomeUser() {
		return nomeUser;
	}

	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the tecnologias
	 */
	public List<TecnologiaBean> getTecnologias() {
		return tecnologias;
	}

	/**
	 * @param tecnologias the tecnologias to set
	 */
	public void setTecnologias(List<TecnologiaBean> tecnologias) {
		this.tecnologias = tecnologias;
	}
	
	
}
