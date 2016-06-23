package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.dao.ProjetoNegocioDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class ProjetoNegocioBusiness {

	@Autowired
	private ProjetoNegocioDAO projetoNegocioDAO;

	public int adicionar(ProjetoBean projeto, List<NegocioBean> negocios) throws BusinessException {
		try {
			int linhasAfetadas = 0;
			linhasAfetadas = projetoNegocioDAO.inserir(projeto, negocios);
			return linhasAfetadas;

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public List<NegocioBean> listar(ProjetoBean projeto) throws ClassNotFoundException, SQLException {

		return projetoNegocioDAO.listar(projeto);
	}

	public void atualizar(ProjetoBean projeto, List<NegocioBean> negocios) throws BusinessException {
		try {
			projetoNegocioDAO.atualizar(projeto, negocios);

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}
}
