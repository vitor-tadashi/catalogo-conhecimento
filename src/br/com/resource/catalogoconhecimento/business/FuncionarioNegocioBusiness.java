package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.dao.FuncionarioNegocioDAO;

@Component
public class FuncionarioNegocioBusiness {
	
	FuncionarioNegocioDAO funcionarioNegocioDao;
	
	public FuncionarioNegocioBusiness() throws ClassNotFoundException, SQLException{
		this.funcionarioNegocioDao = new FuncionarioNegocioDAO();
	}
	
	public int adicionar(FuncionarioBean funcionario, List<NegocioBean> negocios) throws SQLException{
		int linhasAfetadas = 0;
		linhasAfetadas = funcionarioNegocioDao.adicionar(funcionario, negocios);
		return linhasAfetadas;
	}
	
	public List<NegocioBean> listar(FuncionarioBean funcionario) throws ClassNotFoundException, SQLException{
		return funcionarioNegocioDao.listar(funcionario);
	}
	
	public List<NegocioBean> joinFuncionarioNegocio(int idFuncionario) throws ClassNotFoundException, SQLException {
		return funcionarioNegocioDao.joinFuncionarioNegocio(idFuncionario);
	}
	
	public void atualizar(FuncionarioBean funcionarioBean, List<NegocioBean> listaNegocio) throws SQLException {
		funcionarioNegocioDao.atualizar(funcionarioBean, listaNegocio);

	}


}
