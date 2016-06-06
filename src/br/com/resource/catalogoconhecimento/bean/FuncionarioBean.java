package br.com.resource.catalogoconhecimento.bean;


import java.util.Date;
import java.util.List;

public class FuncionarioBean {

	private int id;
	private CargoBean cargo;
	private String nome;
	private String telefone;
	private String nomeUser;
	private String email;
	private List<TecnologiaBean> tecnologias;
	private String cpf;
	private String rg;
	private Date dataNascimento;
	private List<NegocioBean> negocios;

	public FuncionarioBean() {
	}

	public FuncionarioBean(int idFuncionario, CargoBean cargo, String nomeFuncionario, String telefone, String nomeUser,
			String email, String cpf, String rg, Date dataNascimento) {

		this.id = idFuncionario;
		this.cargo = cargo;
		this.nome = nomeFuncionario;
		this.telefone = telefone;
		this.nomeUser = nomeUser;
		this.email = email;
		this.cpf = cpf;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
	}


	public int getId() {
		return id;
	}

	public void setId(int idFuncionario) {
		this.id = idFuncionario;
	}

	public CargoBean getCargo() {
		return cargo;
	}

	public void setCargo(CargoBean cargo) {
		this.cargo = cargo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nomeFuncionario) {
		this.nome = nomeFuncionario;
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

	public List<TecnologiaBean> getTecnologias() {
		return tecnologias;
	}

	public void setTecnologias(List<TecnologiaBean> tecnologias) {
		this.tecnologias = tecnologias;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	
	public List<NegocioBean> getNegocios() {
		return negocios;
	}

	public void setNegocios(List<NegocioBean> negocios) {
		this.negocios = negocios;
	}	

	
}
