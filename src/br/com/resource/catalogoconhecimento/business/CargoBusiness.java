package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.dao.CargoDAO;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.RegistroVinculadoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;

public class CargoBusiness {

	public void adicionar(CargoBean cargoBean) throws ClassNotFoundException, SQLException, TamanhoCampoException,
			NomeRepetidoException, AtributoNuloException {
		CargoDAO cargoDao = new CargoDAO();
		CargoBean cargoDesativada = this.obterNomeDesativado(cargoBean);
		CargoBean cargoClone = this.obterPorNome(cargoBean.getNome());

		if (!validarNome(cargoBean.getNome())) {
			throw new AtributoNuloException("Por favor, digite um nome válido!");
		} else if (cargoBean.getNome().length() > 80) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.80)");
		} else if (cargoDesativada != null) {
			this.reativar(cargoBean);
		} else if (cargoClone != null && cargoClone.getId() != cargoBean.getId()) {
			throw new NomeRepetidoException("Este nome já consta na base de dados");
		} else {
			cargoDao.adicionar(cargoBean);
		}
	}

	public List<CargoBean> listar() throws ClassNotFoundException, SQLException, ConsultaNulaException {
		CargoDAO cargoDao = new CargoDAO();
		List<CargoBean> listaCargo = cargoDao.listar();

		if (listaCargo.isEmpty()) {
			throw new ConsultaNulaException("Não há cargos cadastrados");
		} else {
			return listaCargo;
		}
	}

	public CargoBean obterPorId(int id) throws ClassNotFoundException, SQLException {
		CargoDAO cargoDao = new CargoDAO();

		return cargoDao.obterPorId(id);
	}

	public CargoBean obterPorNome(String nome) throws ClassNotFoundException, SQLException {
		CargoDAO cargoDao = new CargoDAO();

		return cargoDao.obterPorNome(nome);
	}

	public CargoBean obterNomeDesativado(CargoBean cargoBean) throws ClassNotFoundException, SQLException {
		CargoDAO cargoDao = new CargoDAO();

		return cargoDao.obterNomeDesativado(cargoBean);
	}

	public void alterar(CargoBean cargoBean) throws ClassNotFoundException, SQLException, TamanhoCampoException,
			NomeRepetidoException, AtributoNuloException, RegistroVinculadoException {
		CargoDAO cargoDao = new CargoDAO();
		CargoBean cargoDesativada = this.obterNomeDesativado(cargoBean);
		CargoBean cargoClone = cargoDao.obterPorNome(cargoBean.getNome());

		if (cargoBean.getNome().equals("")) {
			throw new AtributoNuloException("Por favor, digite um nome válido!");
		} else if (cargoBean.getNome().length() > 80) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.80)");
		} else if (cargoDesativada != null) {
			this.remover(cargoBean.getId());
			this.reativar(cargoDesativada);
		} else if (cargoClone != null && cargoClone.getId() != cargoBean.getId()) {
			throw new NomeRepetidoException("Este nome já exite na base de dados");
		} else {
			cargoDao.alterar(cargoBean);
		}
	}

	public void remover(int id) throws ClassNotFoundException, SQLException, RegistroVinculadoException {
		CargoDAO cargoDao = new CargoDAO();
		List<FuncionarioBean> listaFuncionario = cargoDao.obterPorFuncionario(id);

		if (listaFuncionario.isEmpty()) {
			cargoDao.remover(id);
		} else {
			throw new RegistroVinculadoException("Registro não pode ser removido");
		}

		cargoDao.remover(id);
	}

	public void reativar(CargoBean cargoBean) throws SQLException, ClassNotFoundException {
		CargoDAO cargoDao = new CargoDAO();

		cargoDao.reativar(cargoBean);
	}
	
	public boolean validarNome(String nome){
		return(nome.matches("[A-Za-zÀ-ú0-9\\s]{2,80}"));
		
	}
	

}
