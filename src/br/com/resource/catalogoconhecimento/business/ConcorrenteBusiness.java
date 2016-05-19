package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.dao.ConcorrenteDAO;

public class ConcorrenteBusiness {

	/* método para listar o concorrente */
	public List<ConcorrenteClienteBean> listar() throws SQLException, ClassNotFoundException {
		try {
			ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
			return concorrenteDao.listar();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		}
	}

	/* método para realizar a inserção de concorrente */
	public void inserir(ConcorrenteBean concorrente) throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();

		concorrenteDao.inserir(concorrente);
	}

	/* m�todo para altera��o de concorrente */
	public boolean atualizar(ConcorrenteBean concorrente) throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDAO = new ConcorrenteDAO();
		
		try {
			ConcorrenteBean concorrenteAux = this.listarPorId(concorrente.getIdConcorrente());

			if (concorrenteAux != null) {
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

	/* m�todo para excluir concorrente */
	public boolean deletar(int idConcorrente) throws SQLException, ClassNotFoundException {
		ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();

		try {
			ConcorrenteBean concorrente = this.listarPorId(idConcorrente);
			if (concorrente != null) {
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

	/* m�todo para listar por ID_concorrente */
	public ConcorrenteBean listarPorId(int idConcorrente) throws SQLException, ClassNotFoundException {
		try {
			ConcorrenteDAO concorrente = new ConcorrenteDAO();
			return concorrente.listarporID(idConcorrente);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {
			s.printStackTrace();
			return null;
		}
	}

}
