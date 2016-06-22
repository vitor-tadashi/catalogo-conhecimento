package br.com.resource.catalogoconhecimento.bean;

import java.util.List;

public class ClienteBean {

	private int id;
	private String nome;
	private String logradouro;
	private String cep;
	private String numero;
	private String cnpj;
	private String email;
	private List<ConcorrenteClienteBean> listaConcorrentes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ConcorrenteClienteBean> getListaConcorrentes() {
		return listaConcorrentes;
	}

	public void setListaConcorrentes(List<ConcorrenteClienteBean> listaConcorrentes) {
		this.listaConcorrentes = listaConcorrentes;
	}

}
