package br.com.resource.catalogoconhecimento.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "ConcorrenteCliente")
public class ConcorrenteClienteBean {


	@Id
	@GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
	@Column(name = "idConcorrenteCliente", unique = true, nullable = false)
	private int id;
	
	private int idCliente;
	
	private int idConcorrente;
	
	@Transient
	private ClienteBean cliente;
	
	@Transient
	private ConcorrenteBean concorrente;
	
	private double valorHora;

	public int getIdConcorrenteCliente() {
		return id;
	}

	public void setIdConcorrenteCliente(int id) {
		this.id = id;
	}

	public double getValorHora() {
		return valorHora;
	}

	public void setValorHora(double valorhora) {
		this.valorHora = valorhora;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdConcorrente() {
		return idConcorrente;
	}

	public void setIdConcorrente(int idConcorrente) {
		this.idConcorrente = idConcorrente;
	}

	public ConcorrenteBean getConcorrente() {
		return concorrente;
	}

	public void setConcorrente(ConcorrenteBean concorrente) {
		this.concorrente = concorrente;
	}
	
	public ClienteBean getCliente() {
		return cliente;
	}

	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}
}
