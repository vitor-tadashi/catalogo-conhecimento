package br.com.resource.catalogoconhecimento.bean;

import java.util.ArrayList;
import java.util.List;

public class ProjetoBean {
	
	private int id;
	private ClienteBean cliente;
	private String nome;
	private String observacao;
	private List<NegocioBean> listaNegocio;
	private List<TecnologiaBean>listaTecnologia;
	private List<EquipeBean> listaEquipe;

	public ProjetoBean() {
		listaNegocio = new ArrayList<>();
		listaTecnologia = new ArrayList<>();
		listaEquipe = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int idProjeto) {
		this.id = idProjeto;
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

	public List<EquipeBean> getListaEquipe() {
		return listaEquipe;
	}

	public void setListaEquipe(List<EquipeBean> listaEquipe) {
		this.listaEquipe = listaEquipe;
	}
	
	
 

}
