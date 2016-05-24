package br.com.resource.catalogoconhecimento.bean;

public class NegocioBean {
	
	private int id;
	private String areaAtuacao;

	public NegocioBean() {

	}

	public NegocioBean(int idNegocio, String areaAtuacao) {
		this.id = idNegocio;
		this.areaAtuacao = areaAtuacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int idNegocio) {
		this.id = idNegocio;
	}

	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
}
