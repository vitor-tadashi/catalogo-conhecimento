package br.com.resource.catalogoconhecimento.bean;


public class FuncionarioBean {
	
	private int idFuncionario;
	private CargoBean cargo;
	private String nomeFuncionario;
	private String telefone;
    private String nomeUser;
	private String email;
	
	
	public FuncionarioBean(){
		
	}
	
	public FuncionarioBean(int idFuncionario, CargoBean cargo, String nomeFuncionario, String telefone, String nomeUser,
			String email) {
		
		
		this.idFuncionario = idFuncionario;
		this.cargo = cargo;
		this.nomeFuncionario = nomeFuncionario;
		this.telefone = telefone;
		this.nomeUser = nomeUser;
		this.email = email;
	}
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	
	
	public CargoBean getCargo() {
		return cargo;
	}

	public void setCargo(CargoBean cargo) {
		this.cargo = cargo;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}
	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getNomeUser() {
		return nomeUser;
	}
	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	


	
	
}
