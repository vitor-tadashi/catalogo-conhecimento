package br.com.resource.catalogoconhecimento.bean;

public class EquipeBean {
	private int idEquipe;
	private String observacao;
	private String nome;

	

	public EquipeBean() {

	}

	public EquipeBean(int idEquipe, String observacao, String nome) {
		super();
		
		this.idEquipe = idEquipe;
		this.observacao = observacao;
		this.nome = nome;
	}

	

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(int idEquipe) {
		this.idEquipe = idEquipe;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
