package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.dao.ConcorrenteDAO;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;


public class ConcorrenteBusiness {
	
	private ConcorrenteDAO concorrenteDao;
	
	public ConcorrenteBusiness() {
		concorrenteDao = new ConcorrenteDAO();
	}

	public void adicionar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException,
			TamanhoCampoException, NomeRepetidoException, AtributoNuloException {
		ConcorrenteBean concorrenteClone = this.obterPorNome(concorrenteBean.getNome());
		if (concorrenteBean.getNome().equals("")) {
			throw new AtributoNuloException("Por favor, digite um nome válido!");
		} else if (concorrenteBean.getNome().length() > 50) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50)");
		} else if (this.existe(concorrenteBean)) {
			this.reativar(concorrenteBean);
		} else if (concorrenteClone != null && concorrenteClone.getId() != concorrenteBean.getId()) {
			throw new NomeRepetidoException("Este nome já está cadastrado!");
		} else {
			concorrenteDao.adicionar(concorrenteBean);
		}
	}

	public List<ConcorrenteBean> listar() throws SQLException, ClassNotFoundException, ConsultaNulaException {
		List<ConcorrenteBean> listaConcorrente = concorrenteDao.listar();

		if (listaConcorrente.isEmpty()) {
			throw new ConsultaNulaException("Não há concorrentes cadastrados!");
		} else {
			return listaConcorrente;
		}
	}

	public List<ConcorrenteClienteBean> listarConcorrenteCliente(int idConcorrente)
			throws SQLException, ClassNotFoundException {
		return concorrenteDao.listarConcorrenteCliente(idConcorrente);
	}
	
	public List<ConcorrenteClienteBean> listarPorNomeCliente(String nomeCliente) throws ClassNotFoundException, SQLException {
		return concorrenteDao.listarPorNomeCliente(nomeCliente);
	}

	public ConcorrenteBean obterPorId(int idConcorrente) throws SQLException, ClassNotFoundException {
		return concorrenteDao.obterPorId(idConcorrente);
	}

	public ConcorrenteBean obterPorNome(String nomeConcorrente) throws ClassNotFoundException, SQLException {
		return concorrenteDao.obterPorNome(nomeConcorrente);
	}

	public boolean existe(ConcorrenteBean concorrenteBean)
			throws SQLException, ClassNotFoundException {
		return concorrenteDao.existe(concorrenteBean);
	}

	public List<ConcorrenteClienteBean> obterPorCliente(int idCliente) throws ClassNotFoundException, SQLException {
		return concorrenteDao.obterPorCliente(idCliente);
	}

	public void alterar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException,
			TamanhoCampoException, NomeRepetidoException, AtributoNuloException {
		ConcorrenteBean concorrenteClone = this.obterPorNome(concorrenteBean.getNome());
		if (concorrenteBean.getNome().equals("")) {
			throw new AtributoNuloException("Por favor, digite um nome válido!");
		} else if (concorrenteBean.getNome().length() > 50) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50)");
		} else if (this.existe(concorrenteBean)) {
			this.reativar(concorrenteBean);
		} else if (concorrenteClone != null && concorrenteClone.getId() != concorrenteBean.getId()) {
			throw new NomeRepetidoException("Este nome já está cadastrado!");
		} else {
			ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
			concorrenteDao.alterar(concorrenteBean);
		}
	}

	public void remover(int idConcorrente) throws ClassNotFoundException, SQLException {
		concorrenteDao.remover(idConcorrente);
	}

	public void reativar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException {
		concorrenteDao.reativar(concorrenteBean);
	}

}
