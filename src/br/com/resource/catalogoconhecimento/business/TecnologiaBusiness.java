package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.dao.TecnologiaDAO;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;

public class TecnologiaBusiness {

	public void adicionar(TecnologiaBean tecnologiaBean)
			throws ClassNotFoundException, SQLException, TamanhoCampoException, NomeRepetidoException {
		TecnologiaDAO tecnologiaDao = new TecnologiaDAO();
		TecnologiaBean tecnologiaDesativada = this.obterNomeDesativado(tecnologiaBean);
		TecnologiaBean tecnologiaClone = this.obterPorNome(tecnologiaBean.getNome());

		if (tecnologiaBean.getNome().length() > 50) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50)");
		} else if (tecnologiaDesativada != null) {
			tecnologiaDao.reativar(tecnologiaBean);
		} else if (tecnologiaClone != null) {
			throw new NomeRepetidoException("Este nome já consta na base de dados");
		} else {
			tecnologiaDao.adicionar(tecnologiaBean);
		}
	}

	public List<TecnologiaBean> listar() throws ClassNotFoundException, SQLException {
		TecnologiaDAO tecnologiaDao = new TecnologiaDAO();

		return tecnologiaDao.listar();
	}

	public TecnologiaBean obterPorId(int id) throws ClassNotFoundException, SQLException {
		TecnologiaDAO tecnologiaDao = new TecnologiaDAO();

		return tecnologiaDao.obterPorId(id);
	}

	public TecnologiaBean obterPorNome(String nome) throws ClassNotFoundException, SQLException {
		TecnologiaDAO tecnologiaDao = new TecnologiaDAO();

		return tecnologiaDao.obterPorNome(nome);
	}
	
	public TecnologiaBean obterNomeDesativado(TecnologiaBean tecnologiaBean)
			throws SQLException, ClassNotFoundException {
		TecnologiaDAO tecnologiaDao = new TecnologiaDAO();
		
		return tecnologiaDao.obterNomeDesativado(tecnologiaBean);
	}

	public void alterar(TecnologiaBean tecnologiaBean)
			throws ClassNotFoundException, SQLException, TamanhoCampoException, NomeRepetidoException {
		TecnologiaDAO tecnologiaDao = new TecnologiaDAO();
		TecnologiaBean tecnologiaClone = tecnologiaDao.obterPorNome(tecnologiaBean.getNome());

		if (tecnologiaBean.getNome().length() > 50) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50)");
		} else if (tecnologiaClone != null && tecnologiaClone.getId() != tecnologiaBean.getId()) {
			throw new NomeRepetidoException("Este nome já exite na base de dados");
		} else {
			tecnologiaDao.alterar(tecnologiaBean);
		}
	}

	public void remover(int id) throws ClassNotFoundException, SQLException {
		TecnologiaDAO tecnologiaDao = new TecnologiaDAO();
		
		tecnologiaDao.remover(id);
	}

}