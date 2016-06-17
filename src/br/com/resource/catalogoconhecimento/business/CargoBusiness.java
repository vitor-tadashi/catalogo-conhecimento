package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.dao.CargoDAO;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.CaracteresEspeciaisException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class CargoBusiness {

	@Autowired
	private CargoDAO cargoDao;

	@Transactional
	public void adicionar(CargoBean cargoBean) throws BusinessException {
		try {
			cargoDao.adicionar(cargoBean);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}
	
	@Transactional
	public List<CargoBean> listar() throws BusinessException {
		try {
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
	
	@Transactional
	public CargoBean obterPorId(int id) throws BusinessException {
		try {
			return cargoDao.obterPorId(id);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}
	
	@Transactional
	public CargoBean obterPorNome(String nome) throws BusinessException {
		try {
			return cargoDao.obterPorNome(nome);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}
	
	@Transactional
	public CargoBean obterNomeDesativado(CargoBean cargoBean) throws ClassNotFoundException, SQLException {
		return cargoDao.obterNomeDesativado(cargoBean);
	}
	
	@Transactional
	public void alterar(CargoBean cargoBean) throws BusinessException {
		try {
			CargoBean cargoClone = cargoDao.obterPorNome(cargoBean.getNome());

			if (cargoBean.getNome().equals("")) {
				throw new AtributoNuloException("Por favor, digite um nome válido!");
			} else if (cargoBean.getNome().length() > 80) {
				throw new TamanhoCampoException("Número limite de caracteres excedido(máx.80)");
			} else if (cargoClone != null && cargoClone.getId() != cargoBean.getId()) {
				throw new NomeRepetidoException("Este nome já exite na base de dados");
			} else if (!validarNome(cargoBean.getNome())) {
				throw new CaracteresEspeciaisException("Por favor, digite um nome sem caracteres especiais");
			} else {
				cargoDao.alterar(cargoBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
			
		}
	}
	
	@Transactional
	public void remover(CargoBean cargoBean) throws BusinessException {
		try {
//			if (cargoDao.verificarPorFuncionario(cargoBean.getId())) {
				cargoDao.remover(cargoBean);
//			} else {
//				throw new RegistroVinculadoException("Registro n�o pode ser removido pois possui v�nculos");
//			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}
	
//	@Transactional
//	public void reativar(CargoBean cargoBean) throws SQLException, ClassNotFoundException {
//		CargoDAO cargoDao = new CargoDAO();
//
//		cargoDao.reativar(cargoBean);
//	}
	
	@Transactional
	public boolean validarNome(String nome) {
		return (nome.matches("[A-Za-zÀ-ú0-9+'\\-\\s]{2,80}"));
	}

}
