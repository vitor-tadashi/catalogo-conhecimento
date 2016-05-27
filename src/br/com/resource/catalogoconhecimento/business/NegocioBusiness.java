package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.dao.NegocioDAO;
import br.com.resource.catalogoconhecimento.dao.TecnologiaDAO;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.RegistroVinculadoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;

public class NegocioBusiness {

	public void adicionar(NegocioBean negocioBean) throws ClassNotFoundException, SQLException, 
	TamanhoCampoException, NomeRepetidoException, AtributoNuloException  {
		NegocioDAO negocioDao = new NegocioDAO();
		NegocioBean negocioDesativado = this.obterNomeDesativado(negocioBean);
		NegocioBean negocioClone = this.obterPorNome(negocioBean.getAreaAtuacao());

		if (negocioBean.getAreaAtuacao().equals("")) {
			throw new AtributoNuloException("Por favor, digite uma área de atuação válida!");
		} else if (negocioBean.getAreaAtuacao().length() > 50) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50)");
		} else if (negocioDesativado != null) {
				this.reativar(negocioBean);
		} else if (negocioClone != null && negocioClone.getId() != negocioBean.getId()) {			
			throw new NomeRepetidoException("Este nome já consta na base de dados");
		} else {
			negocioDao.adicionar(negocioBean);
		}
	}

	public List<NegocioBean> listar() throws ClassNotFoundException, SQLException, ConsultaNulaException {
		NegocioDAO negocioDao = new NegocioDAO();
		List<NegocioBean> listaNegocio = negocioDao.listar();
		
		if(listaNegocio.isEmpty()) {
			throw new ConsultaNulaException("Não há negócios cadastrados");
		}else{
			return listaNegocio;
		}

	}
	
	public NegocioBean obterPorId(int id) throws ClassNotFoundException, SQLException {
		NegocioDAO negociodao = new NegocioDAO();
		
		return negociodao.obterPorId(id);
	}
	
	public NegocioBean obterPorNome(String nome) throws ClassNotFoundException, SQLException {
		NegocioDAO negociodao = new NegocioDAO();
		
		return negociodao.obterPorNome(nome);
	}

	public NegocioBean obterNomeDesativado(NegocioBean negocioBean) throws ClassNotFoundException, SQLException{
		NegocioDAO negocioDao = new NegocioDAO();
		
		return negocioDao.obterNomeDesativado(negocioBean);
	}
	
	public void alterar(NegocioBean negocioBean) throws ClassNotFoundException, SQLException, TamanhoCampoException, NomeRepetidoException, AtributoNuloException {
		NegocioDAO negocioDao =  new NegocioDAO();
		NegocioBean negocioDesativado = this.obterNomeDesativado(negocioBean);
		NegocioBean negocioClone = this.obterPorNome(negocioBean.getAreaAtuacao());
		
		if (negocioBean.getAreaAtuacao().equals("")) {
			throw new AtributoNuloException("Por favor, digite uma área de atuação válida!");
		} else if (negocioBean.getAreaAtuacao().length() > 50) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50");
		} else if (negocioDesativado != null) {
			this.remover(negocioBean.getId());
			this.reativar(negocioDesativado);
		} else if (negocioClone != null && negocioClone.getId() != negocioBean.getId()) {
			throw new NomeRepetidoException("Este nome já exite na base de dados");
		} else {
			negocioDao.alterar(negocioBean);
		}
	}
	
	public void remover(int id) throws ClassNotFoundException, SQLException {
		NegocioDAO negocioDao = new NegocioDAO();
		negocioDao.remover(id);
		
		
		
	}
	
	public void reativar(NegocioBean negocio) throws ClassNotFoundException, SQLException{
		new NegocioDAO().reativar(negocio);
	}

}
