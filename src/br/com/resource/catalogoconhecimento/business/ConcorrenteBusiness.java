package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.dao.ConcorrenteDAO;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;

public class ConcorrenteBusiness {

	public void adicionar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException,
			TamanhoCampoException, NomeRepetidoException, AtributoNuloException {
		if (concorrenteBean.getNome().equals("")) {
			throw new AtributoNuloException("Por favor, digite um nome válido!");
		} else if (concorrenteBean.getNome().length() > 50) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50)");
		} else if (this.obterNomeDesativado(concorrenteBean) != null) {
			this.reativar(concorrenteBean);
		} else if (this.obterPorNome(concorrenteBean.getNome()) != null
				&& this.obterPorNome(concorrenteBean.getNome()).getId() != concorrenteBean.getId()) {
			throw new NomeRepetidoException("Este nome já consta na base de dados");
		} else {
			ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
			concorrenteDao.adicionar(concorrenteBean);
		}
	}

	public List<ConcorrenteBean> listar() throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		return concorrenteDao.listar();
	}

	public List<ConcorrenteClienteBean> listarConcorrenteCliente(int idConcorrente)
			throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		return concorrenteDao.listarConcorrenteCliente(idConcorrente);
	}

	public ConcorrenteBean obterPorId(int idConcorrente) throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		return concorrenteDao.obterPorId(idConcorrente);
	}

	public ConcorrenteBean obterPorNome(String nomeConcorrente) throws ClassNotFoundException, SQLException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		return concorrenteDao.obterPorNome(nomeConcorrente);
	}

	public ConcorrenteBean obterNomeDesativado(ConcorrenteBean concorrenteBean)
			throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		return concorrenteDao.obterNomeDesativado(concorrenteBean);
	}

	public List<ConcorrenteClienteBean> obterPorCliente(int idCliente) throws ClassNotFoundException, SQLException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		return concorrenteDao.obterPorCliente(idCliente);
	}

	public void atualizar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException,
			TamanhoCampoException, NomeRepetidoException, AtributoNuloException {
		ConcorrenteBean concorrenteDesativado = this.obterNomeDesativado(concorrenteBean);
		ConcorrenteBean concorrenteClone = this.obterPorNome(concorrenteBean.getNome());
		if (concorrenteBean.getNome().equals("")) {
			throw new AtributoNuloException("Por favor, digite um nome válido!");
		} else if (concorrenteBean.getNome().length() > 50) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50)");
		} else if (concorrenteDesativado != null) {
			this.reativar(concorrenteBean);
		} else if (concorrenteClone != null && concorrenteClone.getId() != concorrenteBean.getId()) {
			throw new NomeRepetidoException("Este nome já consta na base de dados");
		} else {
			ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
			concorrenteDao.atualizar(concorrenteBean);
		}
	}

	public void remover(int idConcorrente) throws ClassNotFoundException, SQLException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		concorrenteDao.remover(idConcorrente);
	}

	public void reativar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		concorrenteDao.reativar(concorrenteBean);
	}
}
