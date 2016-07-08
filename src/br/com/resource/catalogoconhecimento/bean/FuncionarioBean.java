package br.com.resource.catalogoconhecimento.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "Funcionario")
public class FuncionarioBean {

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "idFuncionario", unique = true, nullable = false)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idCargo")
	private CargoBean cargoBean;

	@Column(name = "nomeFuncionario", nullable = false)
	private String nome;
	
	private String telefone;
	private String nomeUser;
	private String email;
	private char ativo;
	
	@Column(name = "CPF", nullable = false)
	private String cpf;
	
	@Column(name = "RG", nullable = false)
	private String rg;
	
	@Temporal(value = TemporalType.DATE)
	private Date dataNascimento;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "TecnologiaFuncionario", joinColumns = { 
			@JoinColumn(name = "idFuncionario") }, 
			inverseJoinColumns = { @JoinColumn(name = "idTecnologia") })
	private List<TecnologiaBean> listaTecnologia;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "FuncionarioNegocio", joinColumns = { 
			@JoinColumn(name = "idFuncionario") }, 
			inverseJoinColumns = { @JoinColumn(name = "idNegocio") })
	private List<NegocioBean> listaNegocio;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "EquipeFuncionario", joinColumns = { 
			@JoinColumn(name = "idFuncionario") }, 
			inverseJoinColumns = { @JoinColumn(name = "idEquipe") })
	private List<EquipeBean> equipes;
	
//	public FuncionarioBean() {
//		listaTecnologia = new ArrayList<>();
//		listaNegocio = new ArrayList<>();
//	}

	public int getId() {
		return id;
	}

	public void setId(int idFuncionario) {
		this.id = idFuncionario;
	}

	public CargoBean getCargo() {
		return cargoBean;
	}

	public void setCargo(CargoBean cargo) {
		this.cargoBean = cargo;
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

	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}
	
	public CargoBean getCargoBean() {
		return cargoBean;
	}
	
	public void setCargoBean(CargoBean cargoBean) {
		this.cargoBean = cargoBean;
	}

	public List<TecnologiaBean> getListaTecnologia() {
		return listaTecnologia;
	}

	public void setListaTecnologia(List<TecnologiaBean> listaTecnologia) {
		this.listaTecnologia = listaTecnologia;
	}
	
	public List<NegocioBean> getListaNegocio() {
		return listaNegocio;
	}

	public void setListaNegocio(List<NegocioBean> listaNegocio) {
		this.listaNegocio = listaNegocio;
	}

	public List<EquipeBean> getEquipes() {
		return equipes;
	}

	public void setEquipes(List<EquipeBean> equipes) {
		this.equipes = equipes;
	}

	

}
