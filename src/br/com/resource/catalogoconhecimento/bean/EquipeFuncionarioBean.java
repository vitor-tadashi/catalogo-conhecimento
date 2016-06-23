package br.com.resource.catalogoconhecimento.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "EquipeFuncionario")
public class EquipeFuncionarioBean {

	
	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name="idEquipeFuncionario", unique = true, nullable = false)
	private int id;
	
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equipe")
	private int idEquipe;
	
	
	private int idFuncionario;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(int idEquipe) {
		this.idEquipe = idEquipe;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	


}
