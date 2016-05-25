package br.com.resource.catalogoconhecimento.bean;

public class EquipeFuncionarioBean {
	

	private int id;
	private EquipeBean equipe;
	private FuncionarioBean funcionario;
	
	
	public EquipeFuncionarioBean(){
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public EquipeBean getEquipe() {
		return equipe;
	}
	public void setEquipe(EquipeBean equipe) {
		this.equipe = equipe;
	}
	public FuncionarioBean getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(FuncionarioBean funcionario) {
		this.funcionario = funcionario;
	}
	
	
	

}
