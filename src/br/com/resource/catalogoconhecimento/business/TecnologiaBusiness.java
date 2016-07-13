package br.com.resource.catalogoconhecimento.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.dao.TecnologiaDAO;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class TecnologiaBusiness {

	@Autowired
	private TecnologiaDAO tecnologiaDao;

	@Transactional
	public void adicionar(TecnologiaBean tecnologiaBean) throws BusinessException {
		try {
			TecnologiaBean tecnologiaClone = this.obterPorNome(tecnologiaBean.getNome());

			if (tecnologiaBean.getNome().equals("")) {
				throw new AtributoNuloException("Por favor, digite um nome válido!");
			} else if (tecnologiaBean.getNome().length() > 50) {
				throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50)");
			} else if (tecnologiaClone != null && tecnologiaClone.getId() != tecnologiaBean.getId()) {
				throw new NomeRepetidoException("Este nome já consta na base de dados");
			} else {
				tecnologiaDao.adicionar(tecnologiaBean);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public List<TecnologiaBean> listar() throws BusinessException {
		try {
			List<TecnologiaBean> listaTecnologia = tecnologiaDao.listar();
			if (listaTecnologia.isEmpty()) {
				throw new ConsultaNulaException("Não há tecnologias cadastradas");
			} else {
				return listaTecnologia;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public List<TecnologiaBean> listarPorProjeto(ProjetoBean projetoBean) throws BusinessException {
		try {
			List<TecnologiaBean> listaTecnologia = tecnologiaDao.listarPorProjeto(projetoBean);
			if (listaTecnologia.isEmpty()) {
				throw new ConsultaNulaException("Não há tecnologias cadastradas");
			} else {
				return listaTecnologia;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public TecnologiaBean obterPorId(int id) throws BusinessException {
		try {
			return tecnologiaDao.obterPorId(id);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public TecnologiaBean obterPorNome(String nome) throws BusinessException {
		try {
			return tecnologiaDao.obterPorNome(nome);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public List<TecnologiaBean> obterPorFuncionario(int idFuncionario) throws BusinessException {
		try {
			return tecnologiaDao.listarPorFuncionario(idFuncionario);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void alterar(TecnologiaBean tecnologiaBean) throws BusinessException {
		try {
			TecnologiaBean tecnologiaClone = tecnologiaDao.obterPorNome(tecnologiaBean.getNome());

			if (tecnologiaBean.getNome().equals("")) {
				throw new AtributoNuloException("Por favor, digite um nome válido!");
			} else if (tecnologiaBean.getNome().length() > 50) {
				throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50)");
			} else if (tecnologiaClone != null && tecnologiaClone.getId() != tecnologiaBean.getId()) {
				throw new NomeRepetidoException("Este nome já exite na base de dados");
			} else {
				tecnologiaDao.alterar(tecnologiaBean);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);

		}
	}

	@Transactional
	public void remover(TecnologiaBean tecnologiaBean) throws BusinessException {
		try {
			tecnologiaDao.remover(tecnologiaBean);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}
}