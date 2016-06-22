package br.com.resource.catalogoconhecimento.bean;

import java.util.List;

public class ConcorrenteBean {

	private int id;
	private String nome;
	private String descricao;
	private List<ConcorrenteClienteBean> listaClientes;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ConcorrenteClienteBean> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<ConcorrenteClienteBean> listaClientes) {
		this.listaClientes = listaClientes;
	}

}
