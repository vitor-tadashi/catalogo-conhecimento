package br.com.resource.catalogoconhecimento.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Usuario")
public class UsuarioBean {

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "idUsuario", unique = true, nullable = false)
	public int idUsuario;
	

	@Cascade(CascadeType.ALL) 
    @OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idPerfil" ,insertable=true, updatable=true)
	private PerfilBean perfilBean;
	
	public PerfilBean getPerfilBean() {
		return perfilBean;
	}

	public void setPerfilBean(PerfilBean perfilBean) {
		this.perfilBean = perfilBean;
	}

	
	@Column(name ="nomeUsuario")
	public String nome;
	
	@Column(name ="loginUsuario")
	public String login;
	
	@Column(name ="senhaUsuario")
	public String senha;
	
	@Column(name = "ativo")
	public char ativo;

	
	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	public int getId() {
		return idUsuario;
	}

	public void setId(int idUsuario) {
		this.idUsuario = idUsuario;
	}



	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login.trim().toLowerCase();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
