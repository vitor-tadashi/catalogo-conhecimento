package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.scripting.bsh.BshScriptUtils;
import org.springframework.stereotype.Component;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.dao.FuncionarioNegocioDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class FuncionarioNegocioBusiness {
	
	FuncionarioNegocioDAO funcionarioNegocioDao;
	
	public FuncionarioNegocioBusiness() throws ClassNotFoundException, SQLException{
		this.funcionarioNegocioDao = new FuncionarioNegocioDAO();
	}
	
	public int adicionar(FuncionarioBean funcionario, List<NegocioBean> negocios) throws BusinessException{
		try{
			int linhasAfetadas = 0;
			linhasAfetadas = funcionarioNegocioDao.adicionar(funcionario, negocios);
			return linhasAfetadas;
		}catch(Exception e){
			throw ExceptionUtil.handleException(e);
		}
	}
	
	public List<NegocioBean> listar(FuncionarioBean funcionario) throws ClassNotFoundException, SQLException{
		return funcionarioNegocioDao.listar(funcionario);
	}
	
	public List<NegocioBean> joinFuncionarioNegocio(int idFuncionario) throws ClassNotFoundException, SQLException {
		return funcionarioNegocioDao.joinFuncionarioNegocio(idFuncionario);
	}
	
	public void atualizar(FuncionarioBean funcionarioBean, List<NegocioBean> listaNegocio) throws BusinessException {
		try{
			funcionarioNegocioDao.atualizar(funcionarioBean, listaNegocio);
		}catch(Exception e){
			throw ExceptionUtil.handleException(e);
		}
		

	}


}
