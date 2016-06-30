package br.com.resource.catalogoconhecimento.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Cliente")
public class ClienteBean {

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "idCliente", unique = true, nullable = false)
	private int id;
	
	@Column(name = "nomeCliente")
	private String nome;
	
	private String logradouro;
	
	@Column(name = "CEP")
	private String cep;
	
	private String numero;
	
	@Column(name = "CNPJ")
	private String cnpj;
	
	private String email;
	
	private char ativo;
	
	@OneToMany(mappedBy = "cliente", targetEntity = ProjetoBean.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ProjetoBean> listaProjetos;
	
	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	@Transient
	private List<ConcorrenteClienteBean> listaConcorrentes;

	public List<ConcorrenteClienteBean> getListaConcorrentes() {
		return listaConcorrentes;
	}

	public void setListaConcorrentes(List<ConcorrenteClienteBean> listaConcorrentes) {
		this.listaConcorrentes = listaConcorrentes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ProjetoBean> getListaProjetos() {
		return listaProjetos;
	}

	public void setListaProjetos(List<ProjetoBean> listaProjetos) {
		this.listaProjetos = listaProjetos;
	}
	
	
}
