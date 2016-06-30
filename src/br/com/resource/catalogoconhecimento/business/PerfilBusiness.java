package br.com.resource.catalogoconhecimento.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.resource.catalogoconhecimento.bean.PerfilBean;
import br.com.resource.catalogoconhecimento.dao.PerfilDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class PerfilBusiness {

	@Autowired
	private PerfilDAO perfilDAO;

	@Transactional
	public void adicionar(PerfilBean perfilBean) throws BusinessException {
		try {
			PerfilBean perfiClone = (PerfilBean) perfilDAO.obterPorTipo(perfilBean.getTipo().trim());
			if (perfiClone != null && perfiClone.getId() != perfilBean.getId()) {
				throw new NomeRepetidoException("Este tipo já consta na base de dados");
			} else if (!validarTipo(perfiClone.getTipo())) {
				throw new TamanhoCampoException("Número limite de caracteres excedido (min de 4 e max de 30)");
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
			List<PerfilBean> listaPerfis = perfilDAO.listar();

			if (listaPerfis.isEmpty()) {
				throw new ConsultaNulaException("Não há perfis cadastrados");
			} else {
				return listaPerfis;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void alterar(PerfilBean perfil) throws BusinessException {
		try {
			PerfilBean perfiClone = (PerfilBean) perfilDAO.obterPorTipo(perfil.getTipo().trim());
			if (perfiClone != null && perfiClone.getId() != perfil.getId()) {
				throw new NomeRepetidoException("Este tipo já consta na base de dados");
			} else if (!validarTipo(perfiClone.getTipo())) {
				throw new TamanhoCampoException("Número limite de caracteres excedido (min de 4 e max de 30)");
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
			// "Essa Equipe nï¿½o pode ser removida, pois possui vÃ­nculos com
			// Funcionï¿½rios");
			// }
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public boolean validarTipo(String tipo) {
		return (tipo.matches("[A-Za-zÁ-ú0-9\\s]{4,30}"));
	}

}