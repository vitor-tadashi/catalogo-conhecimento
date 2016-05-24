package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.dao.NegocioDAO;

public class NegocioBusiness {

	// Inserir
	public void inserir(NegocioBean negocio) throws ClassNotFoundException, SQLException {

		NegocioDAO negociodao = new NegocioDAO();
		negociodao.inserir(negocio);

	}

	// Deletar
	public boolean deletar(int id) throws ClassNotFoundException, SQLException {
		
			NegocioDAO negociodao = new NegocioDAO();

			NegocioBean negocioAux = this.listarPorId(id);
			if (negocioAux != null) {
				negociodao.deletar(id);
				return true;
			} else {
				return false;
			}

		
	}

	// Atualizar
	public boolean atualizar(NegocioBean negocio) throws ClassNotFoundException, SQLException {
		

			NegocioDAO negocioDao;
			negocioDao = new NegocioDAO();

			NegocioBean negocioAux = negocioDao.listarPorId(negocio.getId());

			if (negocioAux != null) {
				negocioDao.atualizar(negocio);
				return true;
			} else {
				return false;
			}

		
	}

	// Listar
	public List<NegocioBean> listar() throws ClassNotFoundException, SQLException {


			NegocioDAO negocio = new NegocioDAO();
			return negocio.listar();

	}

	// ListarporID
	public NegocioBean listarPorId(int id) throws ClassNotFoundException, SQLException {

			NegocioDAO negociodao = new NegocioDAO();
			return negociodao.listarPorId(id);
		
	}

	// ListarporNome
	public NegocioBean listarPorNome(String nome) throws ClassNotFoundException, SQLException {

			NegocioDAO negociodao = new NegocioDAO();
			return negociodao.listarPorNome(nome);
	
	}
}
