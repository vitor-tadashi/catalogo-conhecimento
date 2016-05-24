package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.dao.CargoDAO;

public class CargoBusiness {

	// CRIA
	public void adicionar(CargoBean cargoBean) throws ClassNotFoundException, SQLException {
		

			CargoDAO cargoDAO = new CargoDAO();

			cargoDAO.adicionar(cargoBean);

	
	}

	// LISTA
	public List<CargoBean> listar() throws ClassNotFoundException, SQLException {
		

			CargoDAO cargoDao = new CargoDAO();
			return cargoDao.listar();

			
		
	}

	// LISTA POR ID
	public CargoBean obterPorId(int id) throws ClassNotFoundException, SQLException {
		

			CargoDAO cargoDao = new CargoDAO();
			return cargoDao.obterPorId(id);

		
	}
	
	// LISTA POR NOME
		public CargoBean obterPorNome(String nome) throws ClassNotFoundException, SQLException {
			

				CargoDAO cargoDao = new CargoDAO();
				return cargoDao.obterPorNome(nome);

			
		}

	// ATUALIZA
	public boolean alterar(CargoBean cargoBean) throws ClassNotFoundException, SQLException {
		

			CargoDAO cargoDao;
			cargoDao = new CargoDAO();

			CargoBean cargo = cargoDao.obterPorId(cargoBean.getId());

			if (cargo != null) {
				cargoDao.alterar(cargoBean);
				return true;
			} else {
				return false;
			}

		

	}

	// DELETA
	public boolean remover(int id) throws ClassNotFoundException, SQLException {

		

			CargoDAO cargoDao = new CargoDAO();

			CargoBean cargoBean = this.obterPorId(id);

			if (cargoBean != null) {
				cargoDao.remover(id);
				return true;
			} else {
				return false;
			}

		
	}

}

