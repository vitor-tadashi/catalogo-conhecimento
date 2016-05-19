package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.dao.TecnologiaDAO;

public class TecnologiaBusiness {

	// CRIA
	public void inserir(TecnologiaBean tecnologia) throws ClassNotFoundException, SQLException {
		try {
			TecnologiaDAO tecnologiaDao = new TecnologiaDAO();

			tecnologiaDao.inserir(tecnologia);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	// LISTA
	public List<TecnologiaBean> listar() {
		try {
			TecnologiaDAO tecnologia = new TecnologiaDAO();
			return tecnologia.listar();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {
			s.printStackTrace();
			return null;
		}
	}

	// LISTA POR ID
	public TecnologiaBean obterPorId(int idTecnologia) {
		try {
			TecnologiaDAO tecnologiaDao = new TecnologiaDAO();
			return tecnologiaDao.obterPorId(idTecnologia);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {
			s.printStackTrace();
			return null;
		}
	}

	// ATUALIZA
	public boolean atualizar(TecnologiaBean tecnologia) {
		try {
			TecnologiaDAO tecnologiaDao;
			tecnologiaDao = new TecnologiaDAO();

			TecnologiaBean tecnologiaAux = tecnologiaDao.obterPorId(tecnologia.getIdTecnologia());

			if (tecnologiaAux != null) {
				tecnologiaDao.atualizar(tecnologia);
				return true;
			} else {
				return false;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException s) {
			s.printStackTrace();
			return false;
		}

	}

	// DELETA
	public boolean deletar(int id) {
		try {
			TecnologiaDAO tecnologiaDao = new TecnologiaDAO();

			TecnologiaBean tecnologiaAux = this.obterPorId(id);
			if (tecnologiaAux != null) {
				tecnologiaDao.deletar(id);
				return true;
			} else {
				return false;
			}

		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return false;
		} catch (SQLException s) {
			s.printStackTrace();
			return false;
		}
	}

}