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
	public boolean deletar(int id) {
		try {
			NegocioDAO negociodao = new NegocioDAO();

			NegocioBean negocioAux = this.listarPorId(id);
			if (negocioAux != null) {
				negociodao.deletar(id);
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

	// Atualizar
	public boolean atualizar(NegocioBean negocio) {
		try {

			NegocioDAO negocioDao;
			negocioDao = new NegocioDAO();

			NegocioBean negocioAux = negocioDao.listarPorId(negocio.getId());

			if (negocioAux != null) {
				negocioDao.atualizar(negocio);
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

	// Listar
	public List<NegocioBean> listar() throws ClassNotFoundException, SQLException {

		try {

			NegocioDAO negocio = new NegocioDAO();
			return negocio.listar();

		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {
			s.printStackTrace();
			return null;
		}
	}

	// ListarporID
	public NegocioBean listarPorId(int id) throws ClassNotFoundException, SQLException {

		try {
			NegocioDAO negociodao = new NegocioDAO();
			return negociodao.listarPorId(id);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {

			s.printStackTrace();
			return null;
		}
	}

	// ListarporNome
	public NegocioBean listarPorNome(String nome) throws ClassNotFoundException, SQLException {

		try {
			NegocioDAO negociodao = new NegocioDAO();
			return negociodao.listarPorNome(nome);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {

			s.printStackTrace();
			return null;
		}
	}
}
