package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.dao.CargoDAO;
import br.com.resource.catalogoconhecimento.dao.TecnologiaDAO;

public class CargoBusiness {

	// CRIA
	public void inserir(CargoBean cargo) throws ClassNotFoundException, SQLException {
		try {

			CargoDAO cargoDAO = new CargoDAO();

			cargoDAO.inserir(cargo);

		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	// LISTA
	public List<CargoBean> listar() {
		try {

			CargoDAO cargo = new CargoDAO();
			return cargo.listar();

		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {
			s.printStackTrace();
			return null;
		}
	}

	// LISTA POR ID
	public CargoBean listarPorId(int idCargo) {
		try {

			CargoDAO cargo = new CargoDAO();
			return cargo.listarPorId(idCargo);

		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {
			s.printStackTrace();
			return null;
		}
	}

	// ATUALIZA
	public boolean atualizar(CargoBean cargo) {
		try {

			CargoDAO cargoDao;
			cargoDao = new CargoDAO();

			CargoBean cargos = cargoDao.listarPorId(cargo.getIdCargo());

			if (cargos == null) {
				return true;
			} else {
				cargoDao.atualizar(cargo);
				return false;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return false;

	}

	// DELETA
	public boolean deletar(int idCargo) {

		try {

			CargoDAO cargoDao = new CargoDAO();

			CargoBean cargo = this.listarPorId(idCargo);

			if (cargo != null) {
				cargoDao.deletar(idCargo);
				return true;
			} else {
				return false;
			}

		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return false;
	}

}

