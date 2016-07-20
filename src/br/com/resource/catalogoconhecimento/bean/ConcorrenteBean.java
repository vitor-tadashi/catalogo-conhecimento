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

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Concorrente")
public class ConcorrenteBean {

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "idConcorrente", unique = true, nullable = false)
	private int id;

	@Column(name = "nomeConcorrente")
	private String nome;

	private String descricao;

	private char ativo;

	@OneToMany(mappedBy = "concorrente", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<ConcorrenteClienteBean> listaClientes;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	public List<ConcorrenteClienteBean> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<ConcorrenteClienteBean> listaClientes) {
		this.listaClientes = listaClientes;
	}

}
