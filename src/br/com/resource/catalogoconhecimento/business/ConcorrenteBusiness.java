package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.dao.ConcorrenteDAO;

public class ConcorrenteBusiness {

	public List<ConcorrenteBean> listar() throws SQLException, ClassNotFoundException {
		try {
			ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
			return concorrenteDao.listar();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		}
	}
	
	public List<ConcorrenteClienteBean> obterPorId(int idConcorrente) throws SQLException, ClassNotFoundException {
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

	public void inserir(ConcorrenteBean concorrente) throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();

		concorrenteDao.inserir(concorrente);
	}

	public boolean atualizar(ConcorrenteBean concorrente) throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDAO = new ConcorrenteDAO();
		
		try {
			List<ConcorrenteClienteBean> concorrentes = this.obterPorId(concorrente.getId());

			if (!concorrentes.isEmpty()) {
				concorrenteDAO.atualizar(concorrente);
				return true;
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

	public boolean deletar(int idConcorrente) throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();

		ConcorrenteBean concorrente = new ConcorrenteBean();
		
		try {
			List<ConcorrenteClienteBean> concorrentes = this.obterPorId(idConcorrente);
			if (!concorrentes.isEmpty()) {
				concorrenteDao.deletar(concorrente);
				return true;
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

}
