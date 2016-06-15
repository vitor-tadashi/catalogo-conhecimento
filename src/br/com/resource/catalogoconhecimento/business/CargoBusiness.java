package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.dao.CargoDAO;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.CaracteresEspeciaisException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.RegistroVinculadoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class CargoBusiness {

	public void adicionar(CargoBean cargoBean) throws BusinessException {
		try {
			CargoDAO cargoDao = new CargoDAO();
			CargoBean cargoDesativada = this.obterNomeDesativado(cargoBean);
			CargoBean cargoClone = this.obterPorNome(cargoBean.getNome());

			if (!validarNome(cargoBean.getNome())) {
				throw new AtributoNuloException("Por favor, digite um nome válido!");
			} else if (cargoBean.getNome().length() > 80) {
				throw new TamanhoCampoException("Número de caracteres excedido(máx. 80)");
			} else if (cargoDesativada != null) {
				this.reativar(cargoBean);
			} else if (cargoClone != null && cargoClone.getId() != cargoBean.getId()) {
				throw new NomeRepetidoException("Este nome já consta na base de dados");
			} else {
				cargoDao.adicionar(cargoBean);
			}

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public List<CargoBean> listar() throws BusinessException {
		try {

			CargoDAO cargoDao = new CargoDAO();
			List<CargoBean> listaCargo = cargoDao.listar();

			if (listaCargo.isEmpty()) {
				throw new ConsultaNulaException("Não há cargos cadastrados");
			} else {
				return listaCargo;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public CargoBean obterPorId(int id) throws BusinessException {
		try {
			CargoDAO cargoDao = new CargoDAO();

			return cargoDao.obterPorId(id);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public CargoBean obterPorNome(String nome) throws ClassNotFoundException, SQLException {
		CargoDAO cargoDao = new CargoDAO();

		return cargoDao.obterPorNome(nome);
	}

	public CargoBean obterNomeDesativado(CargoBean cargoBean) throws ClassNotFoundException, SQLException {
		CargoDAO cargoDao = new CargoDAO();

		return cargoDao.obterNomeDesativado(cargoBean);
	}

	public void alterar(CargoBean cargoBean) throws BusinessException {
		try {
			CargoDAO cargoDao = new CargoDAO();
			CargoBean cargoClone = cargoDao.obterPorNome(cargoBean.getNome());

			if (cargoBean.getNome().equals("")) {
				throw new AtributoNuloException("Por favor, digite um nome v�lido!");
			} else if (cargoBean.getNome().length() > 80) {
				throw new TamanhoCampoException("N�mero limite de caracteres excedido(m�x.80)");
			} else if (cargoClone != null && cargoClone.getId() != cargoBean.getId()) {
				throw new NomeRepetidoException("Este nome j� exite na base de dados");
			} else if (!validarNome(cargoBean.getNome())) {
				throw new CaracteresEspeciaisException("Por favor, digite um nome sem caracteres especiais");
			} else {
				cargoDao.alterar(cargoBean);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public void remover(int id) throws BusinessException {
		try{
		
		CargoDAO cargoDao = new CargoDAO();

		if (cargoDao.verificarPorFuncionario(id)) {
			cargoDao.remover(id);
		} else {
			throw new RegistroVinculadoException("Registro n�o pode ser removido pois possui v�nculos");
		}
		}catch(Exception e){
			throw ExceptionUtil.handleException(e);
		}
	}

	public void reativar(CargoBean cargoBean) throws SQLException, ClassNotFoundException {
		CargoDAO cargoDao = new CargoDAO();

		cargoDao.reativar(cargoBean);
	}

	public boolean validarNome(String nome) {
		return (nome.matches("[A-Za-z�-�0-9\\s]{2,80}"));

	}

}
