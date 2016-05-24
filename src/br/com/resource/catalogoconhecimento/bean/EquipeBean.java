package br.com.resource.catalogoconhecimento.bean;

public class EquipeBean {

	private int id;
	private String observacao;
	private String nome;

	public EquipeBean() {

	}

	public EquipeBean(int id, String observacao, String nome) {
		super();

		this.id = id;
		this.observacao = observacao;
		this.nome = nome;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int idEquipe) {
		this.id = idEquipe;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
