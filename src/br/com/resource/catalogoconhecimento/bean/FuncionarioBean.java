package br.com.resource.catalogoconhecimento.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FuncionarioBean {

	private int id;
	private CargoBean cargo;
	private String nome;
	private String telefone;
	private String nomeUser;
	private String email;
	private String cpf;
	private String rg;
	private Date dataNascimento;
	private List<TecnologiaBean> listaTecnologia;
	private List<NegocioBean> listaNegocio;

	public FuncionarioBean() {
		listaTecnologia = new ArrayList<>();
		listaNegocio = new ArrayList<>();
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

	public List<TecnologiaBean> getListaTecnologia() {
		return listaTecnologia;
	}

	public void setListaTecnologia(List<TecnologiaBean> listaTecnologia) {
		this.listaTecnologia = listaTecnologia;
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

	
	public List<NegocioBean> getListaNegocio() {
		return listaNegocio;
	}

	public void setListaNegocio(List<NegocioBean> listaNegocio) {
		this.listaNegocio = listaNegocio;
	}	

}
