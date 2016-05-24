package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.dao.ProjetoDAO;

public class ProjetoBusiness {

	// INSERE NA TABELA PROJETO
	public void inserir(ProjetoBean projetoBean) throws ClassNotFoundException, SQLException {
		

			ProjetoDAO projetoDAO = new ProjetoDAO();

			projetoDAO.inserir(projetoBean);

		
		
	}

	// CONSULTA NA TABELA PROJETO
	public List<ProjetoBean> listar() throws ClassNotFoundException, SQLException {
		
			ProjetoDAO projeto = new ProjetoDAO();
			return projeto.listar();
		
	}

	// ATUALIZAR NA TABELA PROJETO
	public void atualizar(ProjetoBean projeto) throws ClassNotFoundException, SQLException {
		

			ProjetoDAO projetoDao;
			projetoDao = new ProjetoDAO();

			projetoDao.atualizar(projeto);

		
		
	}

	// LISTA POR ID
	public ProjetoBean obterPorId(int idProjeto) throws ClassNotFoundException, SQLException {
		
			ProjetoDAO projeto = new ProjetoDAO();
			return projeto.obterPorId(idProjeto);
		

	}

	// DELETA NA TABELA PROJETO
	public void deletar(ProjetoBean projeto) throws ClassNotFoundException, SQLException {

		

			ProjetoDAO projetodao = new ProjetoDAO();
			projetodao.deletar(projeto);

			


	}
}
