package br.com.resource.catalogoconhecimento.bean;

public class ConcorrenteBean {
	
	private int id;
	private String nome;
    private String descricao;
    
    public ConcorrenteBean(){
    }
	
	public int getId() {
		return id;
	}
	
	public void setId(int idConcorrente) {
		this.id = idConcorrente;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nomeConcorrente) {
		this.nome = nomeConcorrente;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setdescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
