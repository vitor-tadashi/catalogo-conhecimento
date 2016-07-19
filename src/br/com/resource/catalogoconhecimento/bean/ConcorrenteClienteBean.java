package br.com.resource.catalogoconhecimento.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	
	@ManyToOne
	@JoinColumn(name = "idCliente")
	private ClienteBean cliente;
	
	@ManyToOne
	@JoinColumn(name = "idConcorrente")
	private ConcorrenteBean concorrente;
	
	private double valorHora;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValorHora() {
		return valorHora;
	}

	public void setValorHora(double valorhora) {
		this.valorHora = valorhora;
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
