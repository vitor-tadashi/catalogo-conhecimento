package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.scripting.bsh.BshScriptUtils;
import org.springframework.stereotype.Component;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.dao.TecnologiaFuncionarioDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;
@Component
public class TecnologiaFuncionarioBusiness {

	TecnologiaFuncionarioDAO tecnologiaFuncionarioDAO;

	public TecnologiaFuncionarioBusiness() throws ClassNotFoundException, SQLException {
		this.tecnologiaFuncionarioDAO = new TecnologiaFuncionarioDAO();
	}

	public int adicionar(FuncionarioBean funcionario, List<TecnologiaBean> tecnologias) throws BusinessException {
		try{
			int linhasAfetadas = 0;
			linhasAfetadas = tecnologiaFuncionarioDAO.adicionar(funcionario, tecnologias);
			return linhasAfetadas;		
		}catch(Exception e){
			throw ExceptionUtil.handleException(e);			
		}
	}

	public List<TecnologiaBean> listar(FuncionarioBean funcionario) throws BusinessException {
		try{
			return tecnologiaFuncionarioDAO.listar(funcionario);
		}catch(Exception e){
			throw ExceptionUtil.handleException(e);			
		}
		
	}

	public List<TecnologiaBean> joinTecnologiaFuncionario(int idFuncionario)
			throws ClassNotFoundException, SQLException {
		TecnologiaFuncionarioDAO tecnologia = new TecnologiaFuncionarioDAO();
		return tecnologia.joinTecnologiaFuncionario(idFuncionario);
	}

	public void atualizar(FuncionarioBean funcionarioBean, List<TecnologiaBean> listaTecnologia) throws BusinessException {
		try{
			tecnologiaFuncionarioDAO.atualizar(funcionarioBean, listaTecnologia);
		}catch(Exception e){
			throw ExceptionUtil.handleException(e);	
		}
		

	}

}
