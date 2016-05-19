package br.com.resource.catalogoconhecimento.bean;

public class ConcorrenteBean {
	
	private int idConcorrente;
	private String nomeConcorrente;
    private String descricao;
    
    public ConcorrenteBean(){
    }
	
	public int getIdConcorrente() {
		return idConcorrente;
	}
	
	public void setIdConcorrente(int idConcorrente) {
		this.idConcorrente = idConcorrente;
	}
	
	public String getNomeConcorrente() {
		return nomeConcorrente;
	}
	
	public void setNomeConcorrente(String nomeConcorrente) {
		this.nomeConcorrente = nomeConcorrente;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setdescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
