package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.dao.TecnologiaDAO;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.RegistroVinculadoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;

public class TecnologiaBusiness {
	
	private TecnologiaDAO tecnologiaDao;
	
	public TecnologiaBusiness() {
		tecnologiaDao = new TecnologiaDAO();
	}

	public void adicionar(TecnologiaBean tecnologiaBean) throws ClassNotFoundException, SQLException,
			TamanhoCampoException, NomeRepetidoException, AtributoNuloException {
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
		List<TecnologiaBean> listaTecnologia = tecnologiaDao.listar();

		if (listaTecnologia.isEmpty()) {
			throw new ConsultaNulaException("Não há tecnologias cadastradas");
		} else {
			return listaTecnologia;
		}
	}
	
	public List<TecnologiaBean> listarPorProjeto(ProjetoBean projetoBean) throws ClassNotFoundException, SQLException, ConsultaNulaException {
		List<TecnologiaBean> listaTecnologia = tecnologiaDao.listarPorProjeto(projetoBean);

		if (listaTecnologia.isEmpty()) {
			throw new ConsultaNulaException("Não há tecnologias cadastradas");
		} else {
			return listaTecnologia;
		}
	}

	public TecnologiaBean obterPorId(int id) throws ClassNotFoundException, SQLException {
		return tecnologiaDao.obterPorId(id);
	}

	public TecnologiaBean obterPorNome(String nome) throws ClassNotFoundException, SQLException {
		return tecnologiaDao.obterPorNome(nome);
	}

	public TecnologiaBean obterNomeDesativado(TecnologiaBean tecnologiaBean)
			throws SQLException, ClassNotFoundException {
		return tecnologiaDao.obterDesativado(tecnologiaBean);
	}

	public List<TecnologiaBean> obterPorFuncionario(int idFuncionario) throws SQLException, ClassNotFoundException {
		return tecnologiaDao.listarPorFuncionario(idFuncionario);
	}

	public void alterar(TecnologiaBean tecnologiaBean) throws ClassNotFoundException, SQLException,
			TamanhoCampoException, NomeRepetidoException, AtributoNuloException, RegistroVinculadoException {
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
		if (tecnologiaDao.verificarPorFuncionario(id) && tecnologiaDao.verificarPorProjeto(id)) {
			tecnologiaDao.remover(id);
		} else {
			throw new RegistroVinculadoException("Registro não pode ser removido pois possui vínculos");
		}
	}

	public void reativar(TecnologiaBean tecnologia) throws SQLException, ClassNotFoundException {
		tecnologiaDao.reativar(tecnologia);
	}
	
}