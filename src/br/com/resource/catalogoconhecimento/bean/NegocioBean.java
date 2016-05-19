package br.com.resource.catalogoconhecimento.bean;

public class NegocioBean {
	private int idNegocio;
	private String areaAtuacao;
	
	public NegocioBean(){
		
	}

	public NegocioBean(int idNegocio, String areaAtuacao) {
		this.setIdNegocio(idNegocio);
		this.setAreaAtuacao(areaAtuacao);
	}
	
	public int getIdNegocio() {
		return idNegocio;
	}


	public void setIdNegocio(int idNegocio) {
		this.idNegocio = idNegocio;
	}


	public String getAreaAtuacao() {
		return areaAtuacao;
	}


	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
	
	
	
	
	

}
