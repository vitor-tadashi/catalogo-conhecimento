package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.dao.ProjetoEquipeDAO;

public class ProjetoEquipeBusiness {

	ProjetoEquipeDAO projetoEquipeDAO;

	public ProjetoEquipeBusiness() throws ClassNotFoundException, SQLException {
		this.projetoEquipeDAO = new ProjetoEquipeDAO();
	}
	
	public int inserir(ProjetoBean projeto, List<EquipeBean>equipes) throws SQLException{
		int linhasAfetadas = 0;
		
			linhasAfetadas = projetoEquipeDAO.inserir(projeto, equipes);
	
		
		return linhasAfetadas;
	}
	
	public List<EquipeBean> listar(ProjetoBean projeto) throws ClassNotFoundException, SQLException{
		
			return projetoEquipeDAO.listar(projeto);
	
	}
	
	public void atualizar(ProjetoBean projeto, List<EquipeBean> equipes) throws SQLException{
		
		projetoEquipeDAO.atualizar(projeto, equipes);
		
	}

	
	
}
