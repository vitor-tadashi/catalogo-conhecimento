package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.dao.ConcorrenteClienteDAO;

public class ConcorrenteClienteBusiness {

	/* m�todo para realizar a inser��o de concorrente */
	public void inserir(ConcorrenteClienteBean concClienteBean) throws SQLException, ClassNotFoundException {

		ConcorrenteClienteDAO concorrenclienteDAO = new ConcorrenteClienteDAO();

		concorrenclienteDAO.inserir(concClienteBean);

	}

	/* m�todo para altera��o de concorrente */
	public void atualizar(ConcorrenteClienteBean altconcorrentecliente) throws SQLException, ClassNotFoundException {

		ConcorrenteClienteDAO concorrenteclienteDao = new ConcorrenteClienteDAO();

			concorrenteclienteDao.atualizar(altconcorrentecliente);
		
	}

	/* m�todo para excluir concorrente */
	public void deletar(ConcorrenteClienteBean concorrenteclientes) throws SQLException, ClassNotFoundException {

		ConcorrenteClienteDAO concorrenteclienteDao = new ConcorrenteClienteDAO();

		concorrenteclienteDao.deletar(concorrenteclientes);

	}

	public List<ConcorrenteClienteBean> listar() throws ClassNotFoundException {

	
			ConcorrenteClienteDAO concorrenteClienteDao = new ConcorrenteClienteDAO();

			return concorrenteClienteDao.listar();

		
	}

}
