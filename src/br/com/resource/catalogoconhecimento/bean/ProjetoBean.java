package br.com.resource.catalogoconhecimento.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Projeto")
public class ProjetoBean {

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "idProjeto", unique = true, nullable = false)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCiente")
	@Fetch(FetchMode.JOIN)
	private ClienteBean cliente;

	@Column(name = "nomeProjeto", nullable = false)
	private String nome;
	
	@Column(name = "ativo", nullable = false)
	private char ativo;

	@Column(name = "observacao", nullable = false)
	private String observacao;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "ProjetoNegocio", joinColumns = {
			@JoinColumn(name = "idProjeto", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idNegocio", nullable = false, updatable = false) })
	private List<NegocioBean> listaNegocio;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "ProjetoTecnologia", joinColumns = {
			@JoinColumn(name = "idProjeto", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idTecnologia", nullable = false, updatable = false) })
	private List<TecnologiaBean> listaTecnologia;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "ProjetoEquipe", joinColumns = {
			@JoinColumn(name = "idProjeto", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idEquipe", nullable = false, updatable = false) })
	private List<EquipeBean> listaEquipe;

	public int getId() {
		return id;
	}

	public void setId(int idProjeto) {
		this.id = idProjeto;
	}

	public ClienteBean getCliente() {
		return cliente;
	}

	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nomeProjeto) {
		this.nome = nomeProjeto;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<NegocioBean> getListaNegocio() {
		return listaNegocio;
	}

	public void setListaNegocio(List<NegocioBean> listaNegocio) {
		this.listaNegocio = listaNegocio;
	}

	public List<TecnologiaBean> getListaTecnologia() {
		return listaTecnologia;
	}

	public void setListaTecnologia(List<TecnologiaBean> listaProjeto) {
		this.listaTecnologia = listaProjeto;
	}

	public List<EquipeBean> getListaEquipe() {
		return listaEquipe;
	}

	public void setListaEquipe(List<EquipeBean> listaEquipe) {
		this.listaEquipe = listaEquipe;
	}

	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

}
