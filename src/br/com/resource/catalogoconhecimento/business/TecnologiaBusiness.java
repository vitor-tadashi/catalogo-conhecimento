package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.dao.TecnologiaDAO;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.RegistroVinculadoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;

public class TecnologiaBusiness {

	public void adicionar(TecnologiaBean tecnologiaBean) throws ClassNotFoundException, SQLException,
			TamanhoCampoException, NomeRepetidoException, AtributoNuloException {
		TecnologiaDAO tecnologiaDao = new TecnologiaDAO();
		TecnologiaBean tecnologiaDesativada = this.obterNomeDesativado(tecnologiaBean);
		TecnologiaBean tecnologiaClone = this.obterPorNome(tecnologiaBean.getNome());

		if (tecnologiaBean.getNome().equals("")) {
			throw new AtributoNuloException("Por favor, digite um nome válido!");
		} else if (tecnologiaBean.getNome().length() > 50) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50)");
		} else if (tecnologiaDesativada != null) {
			this.reativar(tecnologiaBean);
		} else if (tecnologiaClone != null && tecnologiaClone.getId() != tecnologiaBean.getId()) {
			throw new NomeRepetidoException("Este nome já consta na base de dados");
		} else {
			tecnologiaDao.adicionar(tecnologiaBean);
		}
	}

	public List<TecnologiaBean> listar() throws ClassNotFoundException, SQLException, ConsultaNulaException {
		TecnologiaDAO tecnologiaDao = new TecnologiaDAO();
		List<TecnologiaBean> listaTecnologia = tecnologiaDao.listar();

		if (listaTecnologia == null) {
			throw new ConsultaNulaException("Não há negócios cadastrados");
		} else {
			return listaTecnologia;
		}
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

	public List<FuncionarioBean> obterPorFuncionario(int id) throws ClassNotFoundException, SQLException {
		TecnologiaDAO tecnologiaDao = new TecnologiaDAO();

		return tecnologiaDao.obterPorFuncionario(id);
	}

	public void alterar(TecnologiaBean tecnologiaBean) throws ClassNotFoundException, SQLException,
			TamanhoCampoException, NomeRepetidoException, AtributoNuloException, RegistroVinculadoException {
		TecnologiaDAO tecnologiaDao = new TecnologiaDAO();
		TecnologiaBean tecnologiaDesativada = this.obterNomeDesativado(tecnologiaBean);
		TecnologiaBean tecnologiaClone = tecnologiaDao.obterPorNome(tecnologiaBean.getNome());

		if (tecnologiaBean.getNome().equals("")) {
			throw new AtributoNuloException("Por favor, digite um nome válido!");
		} else if (tecnologiaBean.getNome().length() > 50) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50)");
		} else if (tecnologiaDesativada != null) {
			this.remover(tecnologiaBean.getId());
			this.reativar(tecnologiaDesativada);
		} else if (tecnologiaClone != null && tecnologiaClone.getId() != tecnologiaBean.getId()) {
			throw new NomeRepetidoException("Este nome já exite na base de dados");
		} else {
			tecnologiaDao.alterar(tecnologiaBean);
		}
	}

	public void remover(int id) throws ClassNotFoundException, SQLException, RegistroVinculadoException {
		TecnologiaDAO tecnologiaDao = new TecnologiaDAO();
		List<FuncionarioBean> listaFuncionario = tecnologiaDao.obterPorFuncionario(id);
		List<ProjetoBean> listaProjeto = tecnologiaDao.obterPorProjeto(id);
		
		if (listaFuncionario == null && listaProjeto == null) {
			tecnologiaDao.remover(id);
		} else {
			throw new RegistroVinculadoException("Registro não pode ser removido pois encontra-se vinculado com outro registro");
		}
	}

	public void reativar(TecnologiaBean tecnologia) throws SQLException, ClassNotFoundException {
		TecnologiaDAO tecnologiaDao = new TecnologiaDAO();

		tecnologiaDao.reativar(tecnologia);
	}

}