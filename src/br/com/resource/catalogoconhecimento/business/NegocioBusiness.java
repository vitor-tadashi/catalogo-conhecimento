package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.dao.NegocioDAO;
import br.com.resource.catalogoconhecimento.dao.TecnologiaDAO;

public class NegocioBusiness {
	
	// ********************************************************************************************************
		// M�TODO PARA CHAMAR A CLASSE NEGOCIODAO PARA INSERIR NA BASE
		// ********************************************************************************************************
		public void inserir(NegocioBean negocio) throws ClassNotFoundException, SQLException {

			NegocioDAO negociodao = new NegocioDAO();
			negociodao.inserir(negocio);

		}

		// ********************************************************************************************************
		// M�TODO PARA CHAMAR A CLASSE NEGOCIODAO PARA DELETAR NA BASE
		// ********************************************************************************************************
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

	

		// ********************************************************************************************************
		// M�TODO PARA CHAMAR A CLASSE NEGOCIODAO PARA ATUALIZA NA BASE
		// ********************************************************************************************************
		public void atualizar(NegocioBean negocio) {

			NegocioDAO negocioDao = new NegocioDAO();

			try {
				negocioDao.atualizar(negocio);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// ********************************************************************************************************
		// M�TODO PARA CHAMAR A CLASSE NEGOCIODAO PARA LISTAR NA BASE
		// ********************************************************************************************************

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

		// ********************************************************************************************************
		// M�TODO PARA CHAMAR A CLASSE NEGOCIODAO PARA LISTAR POR ID NA BASE
		// ********************************************************************************************************

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
