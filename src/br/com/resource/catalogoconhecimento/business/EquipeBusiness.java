package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;
import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.dao.EquipeDAO;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;

public class EquipeBusiness {

	// INSERIR NA BASE

	public void inserir(EquipeBean equipe) throws ClassNotFoundException, SQLException, Exception {

		EquipeDAO equipeDAO = new EquipeDAO();

		EquipeBean oEquipe = equipeDAO.listarPorId(equipe.getId());

		if (oEquipe.toString().trim().equals("")) {
			throw new AtributoNuloException();
		} else {
			equipeDAO.inserir(equipe);
		}

	}

	// DELETAR NA BASE

	public void deletar(EquipeBean idEquipe) throws ClassNotFoundException, SQLException {

		EquipeDAO equipeDAO = new EquipeDAO();
		equipeDAO.deletar(idEquipe);

	}

	// ATUALIZAR NA BASE

	public void atualizar(EquipeBean observacao) throws ClassNotFoundException, SQLException {

		EquipeDAO equipeDAO = new EquipeDAO();
		equipeDAO.atualizar(observacao);

	}

	// LISTAR NA BASE

	public List<EquipeBean> listar() throws ClassNotFoundException, SQLException {

		EquipeDAO equipe = new EquipeDAO();
		return equipe.listar();

	}

	// LISTAR POR ID NA BASE

	public EquipeBean listarPorId(int idEquipe) throws ClassNotFoundException, SQLException {

		EquipeDAO equipe = new EquipeDAO();
		return equipe.listarPorId(idEquipe);

	}
}
