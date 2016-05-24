package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.dao.ConcorrenteDAO;

public class ConcorrenteBusiness {

	public List<ConcorrenteBean> listar() throws SQLException, ClassNotFoundException {
		
			ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
			return concorrenteDao.listar();
		
	}

	public List<ConcorrenteClienteBean> obterPorId(int id) throws SQLException, ClassNotFoundException {
		
			ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
			return concorrenteDao.obterPorId(id);
		
	}

	public List<ConcorrenteClienteBean> obterPorCliente(int id) throws ClassNotFoundException, SQLException {
		
			ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
			return concorrenteDao.obterPorCliente(id);
		
	}

	public void adicionar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
		concorrenteDao.adicionar(concorrenteBean);
	}

	public boolean alterar(ConcorrenteBean concorrenteBean) throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDAO = new ConcorrenteDAO();

		
			List<ConcorrenteClienteBean> concorrentes = this.obterPorId(concorrenteBean.getId());

			if (!concorrentes.isEmpty()) {
				concorrenteDAO.alterar(concorrenteBean);
				return true;
			} else {
				return false;
			}
		
	}

	public boolean remover(int id) throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();

		ConcorrenteBean concorrenteBean = new ConcorrenteBean();

		
			List<ConcorrenteClienteBean> concorrentes = this.obterPorId(id);
			if (!concorrentes.isEmpty()) {
				concorrenteDao.remover(concorrenteBean);
				return true;
			} else {
				return false;
			}

		
	}
}
