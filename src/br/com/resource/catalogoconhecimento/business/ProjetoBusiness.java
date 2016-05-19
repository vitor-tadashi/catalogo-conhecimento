package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.dao.ProjetoDAO;

public class ProjetoBusiness {

	// INSERE NA TABELA PROJETO
	public void inserir(ProjetoBean projetoBean) throws ClassNotFoundException, SQLException {
		try {

			ProjetoDAO projetoDAO = new ProjetoDAO();

			projetoDAO.inserir(projetoBean);

		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		
		
	}

	// CONSULTA NA TABELA PROJETO
	public List<ProjetoBean> listar() throws ClassNotFoundException, SQLException {
		try {
			ProjetoDAO projeto = new ProjetoDAO();
			return projeto.consultar();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {
			s.printStackTrace();
			return null;
		}
	}

	// ATUALIZAR NA TABELA PROJETO
	public boolean atualizar(ProjetoBean projeto) throws ClassNotFoundException, SQLException {
		try {

			ProjetoDAO projetodao;
			projetodao = new ProjetoDAO();

			ProjetoBean projetoAux = projetodao.listarPorId(projeto.getIdProjeto());

			if (projetoAux == null) {
				return true;
			} else {
				projetodao.atualizar(projeto);
				return false;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return false;
	}

	// LISTA POR ID
	public ProjetoBean listarPorId(int idProjeto) throws ClassNotFoundException, SQLException {
		try {
			ProjetoDAO projeto = new ProjetoDAO();
			return projeto.listarPorId(idProjeto);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {
			s.printStackTrace();
			return null;
		}

	}

	// DELETA NA TABELA PROJETO
	public boolean deletar(ProjetoBean idProjeto) throws ClassNotFoundException, SQLException {

		try {

			ProjetoDAO projetodao = new ProjetoDAO();
			projetodao.deletar(idProjeto);
			
			

			/*if (projetoAux == null) {
				return true;
			} else {
				projetodao.deleta(projetoAux);
				return false;
			}*/

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		}

		return false;

	}
}
