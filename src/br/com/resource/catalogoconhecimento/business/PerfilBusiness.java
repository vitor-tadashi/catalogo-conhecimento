package br.com.resource.catalogoconhecimento.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.resource.catalogoconhecimento.bean.PerfilBean;
import br.com.resource.catalogoconhecimento.dao.PerfilDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

public class PerfilBusiness {

	@Autowired
	private PerfilDAO perfilDAO;

	@Transactional
	public void adicionar(PerfilBean perfilBean) throws BusinessException {

		try {
			PerfilBean perfilIgual = (PerfilBean) perfilDAO.obterPorTipo(perfilBean.getTipo().trim());
			if (perfilIgual != null && perfilIgual.getId() != perfilBean.getId()) {
				throw new NomeRepetidoException("Este tipo já consta na base de dados");
			} else if (perfilBean.getTipo().length() > 30) {
				throw new TamanhoCampoException("Número limite de caracteres excedido (max 30)");
			} else {
				perfilDAO.adicionar(perfilBean);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public List<PerfilBean> listar() throws BusinessException {
		try {
			List<PerfilBean> listaPerfil = perfilDAO.listar();

			if (listaPerfil.isEmpty()) {
				throw new ConsultaNulaException("Não há perfis cadastrados");
			} else {
				return listaPerfil;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void alterar(PerfilBean perfil) throws BusinessException {
		try {

			PerfilBean perfilIgual = (PerfilBean) perfilDAO.obterPorTipo(perfil.getTipo().trim());

			if (perfilIgual != null && perfilIgual.getId() != perfil.getId()) {
				throw new NomeRepetidoException("Este tipo já consta na base de dados");
			} else if (perfil.getTipo().length() > 30) {
				throw new TamanhoCampoException("Número limite de caracteres excedido (max 30)");
			} else {
				perfilDAO.alterar(perfil);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public PerfilBean obterPorId(int id) throws BusinessException {
		try {
			return perfilDAO.obterPorId(id);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void remover(PerfilBean perfil) throws BusinessException {
		try {
			// if (equipeDAO.verificarPorFuncionarios(equipe)) {
			perfilDAO.remover(perfil);
			// } else {
			// throw new RegistroVinculadoException(
			// "Essa Equipe n�o pode ser removida, pois possui vínculos com
			// Funcion�rios");
			// }
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

}