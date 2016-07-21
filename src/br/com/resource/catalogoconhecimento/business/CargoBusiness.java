package br.com.resource.catalogoconhecimento.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.dao.CargoDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.CaracteresEspeciaisException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class CargoBusiness {

	@Autowired
	private CargoDAO cargoDAO;

	@Transactional
	public void adicionar(CargoBean cargoBean) throws BusinessException {
		try {
			if (cargoDAO.obterPorNome(cargoBean.getNome().trim()) != null) {
				throw new NomeRepetidoException("Este nome já exite na base de dados");
			} else if (!validarNome(cargoBean.getNome())) {
				throw new CaracteresEspeciaisException("Por favor, digite um nome válido");
			} else {
				cargoDAO.adicionar(cargoBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public List<CargoBean> listar() throws BusinessException {
		try {
			List<CargoBean> listaCargo = cargoDAO.listar();
			if (listaCargo.isEmpty()) {
				throw new ConsultaNulaException("Não há cargos cadastrados");
			} else {
				return listaCargo;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public CargoBean obterPorId(int id) throws BusinessException {
		try {
			return cargoDAO.obterPorId(id);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public CargoBean obterPorNome(String nome) throws BusinessException {
		try {
			return cargoDAO.obterPorNome(nome);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void alterar(CargoBean cargoBean) throws BusinessException {
		try {
			if (cargoDAO.obterPorNome(cargoBean.getNome().trim()) != null) {
				throw new NomeRepetidoException("Este nome já exite na base de dados");
			} else if (!validarNome(cargoBean.getNome())) {
				throw new CaracteresEspeciaisException("Por favor, digite um nome válido");
			} else {
				cargoDAO.alterar(cargoBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void remover(CargoBean cargoBean) throws BusinessException {
		try {
			cargoDAO.remover(cargoBean);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public boolean validarNome(String nome) {
		return (nome.matches("[A-Za-zÀ-ú0-9+'\\-\\s]{2,80}"));
	}

}
