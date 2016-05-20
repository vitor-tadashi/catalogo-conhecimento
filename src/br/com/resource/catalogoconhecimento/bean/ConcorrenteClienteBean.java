package br.com.resource.catalogoconhecimento.bean;

public class ConcorrenteClienteBean {
	private int id;
	private ClienteBean cliente;
	private ConcorrenteBean concorrente;
	private double valorHora;

	public ConcorrenteClienteBean() {
	}

	public int getIdConcorrenteCliente() {
		return id;
	}

	public void setIdConcorrenteCliente(int idConcorrenteCliente) {
		this.id = idConcorrenteCliente;
	}

	public ClienteBean getCliente() {
		return cliente;
	}

	public void setCliente(ClienteBean cliente) {
		this.cliente = cliente;
	}

	public ConcorrenteBean getConcorrente() {
		return concorrente;
	}

	public void setConcorrente(ConcorrenteBean concorrente) {
		this.concorrente = concorrente;
	}

	public double getValorHora() {
		return valorHora;
	}

	public void setValorHora(double valorhora) {
		this.valorHora = valorhora;
	}

}
