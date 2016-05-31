package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.dao.ConcorrenteDAO;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;

public class ConcorrenteBusiness {

	public void adicionar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException {
		try {
			ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
			concorrenteDao.adicionar(concorrenteBean);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	public List<ConcorrenteBean> listar() throws SQLException, ClassNotFoundException, ConsultaNulaException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		List<ConcorrenteBean> listaConcorrente = concorrenteDao.listar();

		if (listaConcorrente.isEmpty()) {
			throw new ConsultaNulaException("Não há tecnologias cadastradas");
		} else {
			return listaConcorrente;
		}
	}

	// ESSE AQUI
	public List<ConcorrenteClienteBean> listarConcorrenteCliente(int idConcorrente)
			throws SQLException, ClassNotFoundException {
		try {
			ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
			return concorrenteDao.listarConcorrenteCliente(idConcorrente);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {
			s.printStackTrace();
			return null;
		}
	}

	public ConcorrenteBean obterPorId(int idConcorrente) throws SQLException, ClassNotFoundException {
		try {
			ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
			return concorrenteDao.obterPorId(idConcorrente);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {
			s.printStackTrace();
			return null;
		}
	}

	public List<ConcorrenteClienteBean> obterPorCliente(int idCliente) {
		try {
			ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
			return concorrenteDao.obterPorCliente(idCliente);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {
			s.printStackTrace();
			return null;
		}
	}

	public boolean atualizar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDAO = new ConcorrenteDAO();
		try {
			ConcorrenteBean concorrenteAux = this.obterPorId(concorrenteBean.getId());
			if (concorrenteAux != null) {
				concorrenteDAO.atualizar(concorrenteBean);
				return true;
				/*
				 * List<ConcorrenteClienteBean> listaConcorrentes =
				 * this.obterPorId(concorrenteBean.getId()); if
				 * (!listaConcorrentes.isEmpty()) {
				 * concorrenteDAO.alterar(concorrenteBean); return true;
				 */
			} else {
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void remover(int idConcorrente) throws ClassNotFoundException, SQLException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		try {
			concorrenteDao.remover(idConcorrente);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
