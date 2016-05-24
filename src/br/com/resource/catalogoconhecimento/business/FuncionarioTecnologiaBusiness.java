package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.dao.FuncionarioTecnologiaDAO;

public class FuncionarioTecnologiaBusiness {
	
	FuncionarioTecnologiaDAO funcionarioTecnologiaDAO;
	
	public FuncionarioTecnologiaBusiness() throws ClassNotFoundException, SQLException {
	
			this.funcionarioTecnologiaDAO = new FuncionarioTecnologiaDAO();
	
	}
	
	public int inserir(FuncionarioBean funcionario, List<TecnologiaBean> tecnologias) throws SQLException{
		int linhasAfetadas=0;
		
		
	linhasAfetadas = funcionarioTecnologiaDAO.inserir(funcionario, tecnologias);
	
		
		return linhasAfetadas;
		
	}
	
	public List<TecnologiaBean> listar(FuncionarioBean funcionario) throws ClassNotFoundException, SQLException{
		
			return funcionarioTecnologiaDAO.listar(funcionario);
	
		
	}
	

}
