package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.dao.ProjetoNegocioDAO;

public class ProjetoNegocioBusiness {
	
	ProjetoNegocioDAO projetoNegocioDAO;

	public ProjetoNegocioBusiness() throws ClassNotFoundException, SQLException {
		
		
			this.projetoNegocioDAO = new ProjetoNegocioDAO();
		
	}
	
	public int insere(ProjetoBean projeto, List<NegocioBean>negocios) throws SQLException{
		int linhasAfetadas = 0;
		
			linhasAfetadas = projetoNegocioDAO.inserir(projeto, negocios);
	
		
		return linhasAfetadas;
	}
	
	public List<NegocioBean> listar(ProjetoBean projeto) throws ClassNotFoundException, SQLException{
		
			return projetoNegocioDAO.listar(projeto);
	
	}
	
	public void atualizar(ProjetoBean projeto, List<NegocioBean> negocios) throws SQLException{
		
			projetoNegocioDAO.atualizar(projeto, negocios);
		
	}

}
