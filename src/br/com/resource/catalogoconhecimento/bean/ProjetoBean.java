package br.com.resource.catalogoconhecimento.bean;

import java.util.ArrayList;
import java.util.List;

public class ProjetoBean {
	private int id;
	private EquipeBean equipe;
	private ClienteBean cliente;
	private String nome;
	private String observacao;
	private List<NegocioBean> listaNegocio;
	private List<TecnologiaBean>listaTecnologia;

	public ProjetoBean() {
		listaNegocio = new ArrayList<>();
		listaTecnologia = new ArrayList<>();
	}

	public ProjetoBean(int idProjeto, ClienteBean cliente, EquipeBean equipe, String nomeProjeto, String observacao) {
		this.id = idProjeto;
		this.equipe = equipe;
		this.cliente = cliente;
		this.nome = nomeProjeto;
		this.observacao = observacao;
		listaNegocio = new ArrayList<>();
		listaTecnologia = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int idProjeto) {
		this.id = idProjeto;
	}

	public EquipeBean getEquipe() {
		return equipe;
	}

	public void setEquipe(EquipeBean equipe) {
		this.equipe = equipe;
	}

	public ClienteBean getCliente() {
		return cliente;
	}

	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nomeProjeto) {
		this.nome = nomeProjeto;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<NegocioBean> getListaNegocio() {
		return listaNegocio;
	}

	public void setListaNegocio(List<NegocioBean> listaNegocio) {
		this.listaNegocio = listaNegocio;
	}

	public List<TecnologiaBean> getListaTecnologia() {
		return listaTecnologia;
	}

	public void setListaTecnologia(List<TecnologiaBean> listaProjeto) {
		this.listaTecnologia = listaProjeto;
	}
	
	
 

}
