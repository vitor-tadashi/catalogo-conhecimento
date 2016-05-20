package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.dao.FuncionarioTecnologiaDAO;

public class FuncionarioTecnologiaBusiness {
	
	FuncionarioTecnologiaDAO funcionarioTecnologiaDAO;
	
	public FuncionarioTecnologiaBusiness() {
		
		try{
			this.funcionarioTecnologiaDAO = new FuncionarioTecnologiaDAO();
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException e){
			System.out.println(e);			
		}
	}
	
	public int inserir(FuncionarioBean funcionario, List<TecnologiaBean> tecnologias){
		int linhasAfetadas=0;
		
		try{
			linhasAfetadas = funcionarioTecnologiaDAO.inserir(funcionario, tecnologias);
		}catch(SQLException e){
			System.out.println(e);			
		}
		
		return linhasAfetadas;
		
	}
	
	public List<TecnologiaBean> listar(FuncionarioBean funcionario){
		try{
			return funcionarioTecnologiaDAO.listar(funcionario);
		}catch(ClassNotFoundException | SQLException e){
			System.out.println(e);
		}
		
		return null;
		
	}
	

}
