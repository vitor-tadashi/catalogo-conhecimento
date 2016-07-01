package br.com.resource.catalogoconhecimento.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Perfil")
public class PerfilBean {

	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "idPerfil", unique = true, nullable = false)
	public int id;
	
	
//	@OneToMany(mappedBy="perfilBean",fetch=FetchType.LAZY)
//	@Cascade(CascadeType.ALL)
//	private List<UsuarioBean> usuario;
//	
//	
//	public List<UsuarioBean> getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(List<UsuarioBean> usuario) {
//		this.usuario = usuario;
//	}

//	@Column(name = "tipo")
	public String tipo;

	@Column(name = "ativo")
	public char ativo;

	public int getId() {
		return id;
	}

//	public void setId(int id) {
//		this.id = id;
//	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

}
