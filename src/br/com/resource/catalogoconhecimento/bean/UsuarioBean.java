package br.com.resource.catalogoconhecimento.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Usuario")
public class UsuarioBean {

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "idUsuario", unique = true, nullable = false)
	public int idUsuario;
	
	@Column(name ="idPerfil")
	public int id;
	
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

	public int getIdPerfil() {
		return id;
	}

	public void setIdPerfil(int idPerfil) {
		this.id = idPerfil;
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
