package br.com.resource.catalogoconhecimento.business;




import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.dao.EquipeDAO;


public class EquipeBusiness {

	// ********************************************************************************************************
	// M�TODO PARA CHAMAR A CLASSE EQUIPEDAO PARA INSERIR NA BASE
	// ********************************************************************************************************
	public void inserir(EquipeBean equipe) throws ClassNotFoundException, SQLException {

		EquipeDAO equipeDAO = new EquipeDAO();

		equipeDAO.inserir(equipe);

	}

	// ********************************************************************************************************
	// M�TODO PARA CHAMAR A CLASSE EQUIPEDAO PARA DELETAR NA BASE
	// ********************************************************************************************************
	public void deletar(EquipeBean idEquipe) throws ClassNotFoundException, SQLException {

		EquipeDAO equipeDAO = new EquipeDAO();

		try {
			
			equipeDAO.deletar(idEquipe);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
		
	

	// ********************************************************************************************************
	// M�TODO PARA CHAMAR A CLASSE EQUIPEDAO PARA ATUALIZA NA BASE
	// ********************************************************************************************************
	public void atualizar(EquipeBean observacao) {

		EquipeDAO equipeDAO = new EquipeDAO();

		try {
			equipeDAO.atualizar(observacao);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ********************************************************************************************************
	// M�TODO PARA CHAMAR A CLASSE EQUIPEDAO PARA LISTAR NA BASE
	// ********************************************************************************************************

	public List<EquipeBean> listar() throws ClassNotFoundException, SQLException {

		try {

			EquipeDAO equipe = new EquipeDAO();
			return equipe.listar();

		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {
			s.printStackTrace();
			return null;
		}
	}

	// ********************************************************************************************************
	// M�TODO PARA CHAMAR A CLASSE EQUIPEDAO PARA LISTAR POR ID NA BASE
	// ********************************************************************************************************

	public EquipeBean listarPorId(int idEquipe) throws ClassNotFoundException, SQLException {

		try {
			EquipeDAO equipe = new EquipeDAO();
			return equipe.listarPorId(idEquipe);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {

			s.printStackTrace();
			return null;
		}
	}
}
