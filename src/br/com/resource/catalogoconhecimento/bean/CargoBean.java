package br.com.resource.catalogoconhecimento.bean;

public class CargoBean {

	private int idCargo;
	private String nomeCargo;

	public CargoBean(){
		
	}

	public CargoBean(int idCargo, String nomeCargo) {
		super();
		this.idCargo = idCargo;
		this.nomeCargo = nomeCargo;
	}

	public int getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

}
