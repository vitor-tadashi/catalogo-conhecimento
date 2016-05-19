package br.com.resource.catalogoconhecimento.bean;

public class ProjetoBean {
	private int idProjeto;
	private EquipeBean equipe;
	private ClienteBean cliente;
	private String nomeProjeto;
	private String observacao;

	public ProjetoBean() {

	}

	
	public ProjetoBean(int idProjeto, ClienteBean cliente,EquipeBean equipe, String nomeProjeto, String observacao) {
		this.idProjeto = idProjeto;
		this.equipe = equipe;
		this.cliente = cliente;
		this.nomeProjeto = nomeProjeto;
		this.observacao = observacao;
	}


	public int getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(int idProjeto) {
		this.idProjeto = idProjeto;
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


	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}



}
