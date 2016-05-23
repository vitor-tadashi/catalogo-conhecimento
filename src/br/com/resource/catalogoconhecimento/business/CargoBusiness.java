package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.dao.CargoDAO;

public class CargoBusiness {

	// CRIA
	public void adicionar(CargoBean cargoBean) throws ClassNotFoundException, SQLException {
		try {

			CargoDAO cargoDAO = new CargoDAO();

			cargoDAO.adicionar(cargoBean);

		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	// LISTA
	public List<CargoBean> listar() {
		try {

			CargoDAO cargoDao = new CargoDAO();
			return cargoDao.listar();

		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {
			s.printStackTrace();
			return null;
		}
	}

	// LISTA POR ID
	public CargoBean obterPorId(int id) {
		try {

			CargoDAO cargoDao = new CargoDAO();
			return cargoDao.obterPorId(id);

		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {
			s.printStackTrace();
			return null;
		}
	}
	
	// LISTA POR NOME
		public CargoBean obterPorNome(String nome) {
			try {

				CargoDAO cargoDao = new CargoDAO();
				return cargoDao.obterPorNome(nome);

			} catch (ClassNotFoundException c) {
				c.printStackTrace();
				return null;
			} catch (SQLException s) {
				s.printStackTrace();
				return null;
			}
		}

	// ATUALIZA
	public boolean alterar(CargoBean cargoBean) {
		try {

			CargoDAO cargoDao;
			cargoDao = new CargoDAO();

			CargoBean cargo = cargoDao.obterPorId(cargoBean.getId());

			if (cargo != null) {
				cargoDao.alterar(cargoBean);
				return true;
			} else {
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
	public boolean remover(int id) {

		try {

			CargoDAO cargoDao = new CargoDAO();

			CargoBean cargoBean = this.obterPorId(id);

			if (cargoBean != null) {
				cargoDao.remover(id);
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

