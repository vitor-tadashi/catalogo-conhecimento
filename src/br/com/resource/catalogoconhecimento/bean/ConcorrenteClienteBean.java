package br.com.resource.catalogoconhecimento.bean;

public class ConcorrenteClienteBean {
	private int idConcorrenteCliente;
	private ClienteBean cliente;
	private ConcorrenteBean concorrente;
	private double valorHora;

	public ConcorrenteClienteBean() {
	}

	public int getIdConcorrenteCliente() {
		return idConcorrenteCliente;
	}

	public void setIdConcorrenteCliente(int idConcorrenteCliente) {
		this.idConcorrenteCliente = idConcorrenteCliente;
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

	public double getValorhora() {
		return valorHora;
	}

	public void setValorhora(double valorhora) {
		this.valorHora = valorhora;
	}

}
