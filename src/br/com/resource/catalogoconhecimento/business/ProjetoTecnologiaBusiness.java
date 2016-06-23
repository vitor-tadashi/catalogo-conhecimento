package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.dao.ProjetoTecnologiaDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class ProjetoTecnologiaBusiness {

	@Autowired
	private ProjetoTecnologiaDAO projetoTecnologiaDAO;

	public int adicionar(ProjetoBean projeto, List<TecnologiaBean> listaTecnologias) throws BusinessException {
		try {
			int linhasAfetadas = 0;
			linhasAfetadas = projetoTecnologiaDAO.inserir(projeto, listaTecnologias);
			return linhasAfetadas;
		} catch (

		Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public List<TecnologiaBean> listar(ProjetoBean projeto) throws BusinessException {
		try {
			return projetoTecnologiaDAO.listar(projeto);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public void atualizar(ProjetoBean projeto, List<TecnologiaBean> listaTecnologia) throws BusinessException {
		try {
			projetoTecnologiaDAO.atualizar(projeto, listaTecnologia);

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public void remover(ProjetoBean projeto) throws SQLException {
		projetoTecnologiaDAO.deletar(projeto);
	}
}
