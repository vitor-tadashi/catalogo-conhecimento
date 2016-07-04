package br.com.resource.catalogoconhecimento.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
        

@Entity
@Table(name = "perfil")
public class PerfilBean {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name = "idPerfil", unique = true, nullable = false)
	public int id;
	

	 @OneToMany 
     @JoinTable(name="usuarioPerfil",  
               joinColumns={@JoinColumn(name="idPerfil", 
                referencedColumnName="idPerfil")},  
               inverseJoinColumns={@JoinColumn(name="idUsuario", 
                 referencedColumnName="idUsuario")})  
     private List<UsuarioBean> usuario;

	
	
	
	public List<UsuarioBean> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<UsuarioBean> usuario) {
		this.usuario = usuario;
	}

	public String tipo;

	@Column(name = "ativo")
	public char ativo;

	public int getId() {
		return id;
	}


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
