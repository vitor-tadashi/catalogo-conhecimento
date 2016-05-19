package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.dao.ProjetoNegocioDAO;

public class ProjetoNegocioBusiness {
	
	ProjetoNegocioDAO projetoNegocioDAO;

	public ProjetoNegocioBusiness() {
		
		try {
			this.projetoNegocioDAO = new ProjetoNegocioDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int insere(ProjetoBean projeto, List<NegocioBean>negocios){
		int linhasAfetadas = 0;
		try {
			linhasAfetadas = projetoNegocioDAO.inserir(projeto, negocios);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return linhasAfetadas;
	}
	
	public List<NegocioBean> listar(ProjetoBean projeto){
		try {
			return projetoNegocioDAO.listar(projeto);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

}
