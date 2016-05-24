package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.dao.TecnologiaDAO;

public class TecnologiaBusiness {

	// CRIA
	public void inserir(TecnologiaBean tecnologia) throws ClassNotFoundException, SQLException {
		
			TecnologiaDAO tecnologiaDao = new TecnologiaDAO();

			tecnologiaDao.inserir(tecnologia);
	
	}

	// LISTA
	public List<TecnologiaBean> listar() throws ClassNotFoundException, SQLException {
		
			TecnologiaDAO tecnologia = new TecnologiaDAO();
			return tecnologia.listar();
		
	}

	// LISTA POR ID
	public TecnologiaBean obterPorId(int idTecnologia) throws ClassNotFoundException, SQLException {
		
			TecnologiaDAO tecnologiaDao = new TecnologiaDAO();
			return tecnologiaDao.obterPorId(idTecnologia);
		
	}
	
	public TecnologiaBean obterPorNome(String nome) throws ClassNotFoundException, SQLException {
		TecnologiaDAO tecnologiaDao = new TecnologiaDAO();
	
			return tecnologiaDao.obterPorNome(nome);
		
	}

	// ATUALIZA
	public boolean atualizar(TecnologiaBean tecnologia) throws ClassNotFoundException, SQLException {
		
			TecnologiaDAO tecnologiaDao;
			tecnologiaDao = new TecnologiaDAO();

			TecnologiaBean tecnologiaAux = tecnologiaDao.obterPorId(tecnologia.getId());

			if (tecnologiaAux != null) {
				tecnologiaDao.atualizar(tecnologia);
				return true;
			} else {
				return false;
			}

		

	}

	// DELETA
	public boolean deletar(int id) throws ClassNotFoundException, SQLException {
		
			TecnologiaDAO tecnologiaDao = new TecnologiaDAO();

			TecnologiaBean tecnologiaAux = this.obterPorId(id);
			if (tecnologiaAux != null) {
				tecnologiaDao.deletar(id);
				return true;
			} else {
				return false;
			}

	
	}

}