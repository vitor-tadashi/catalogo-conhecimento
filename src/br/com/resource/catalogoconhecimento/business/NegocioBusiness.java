package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.dao.NegocioDAO;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.RegistroVinculadoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;

public class NegocioBusiness {

	public void inserir(NegocioBean negocioBean) throws ClassNotFoundException, SQLException, 
	TamanhoCampoException, NomeRepetidoException, AtributoNuloException  {
		NegocioDAO negocioDao = new NegocioDAO();
		NegocioBean negocioDesativado = this.obterNomeDesativado(negocioBean);
		NegocioBean negocioClone = this.obterPorNome(negocioBean.getAreaAtuacao());

		if (!validarAreaAtuacao(negocioBean.getAreaAtuacao())) {
			throw new TamanhoCampoException("Por Favor, digite uma área de atuação válida!");
		} else if (negocioDesativado != null) {
				this.reativar(negocioBean);
		} else if (negocioClone != null) {
			throw new NomeRepetidoException("Este nome já exite na base de dados.");
		} else{
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
	
	public NegocioBean obterPorNome(String areaAtuacao) throws ClassNotFoundException, SQLException {
		NegocioDAO negociodao = new NegocioDAO();
		
		return negociodao.obterPorNome(areaAtuacao);
	}

	public NegocioBean obterNomeDesativado(NegocioBean negocioBean) throws ClassNotFoundException, SQLException{
		NegocioDAO negocioDao = new NegocioDAO();
		
		return negocioDao.obterNomeDesativado(negocioBean);
	}
	
	public void alterar(NegocioBean negocioBean) throws ClassNotFoundException, SQLException, TamanhoCampoException, NomeRepetidoException, AtributoNuloException {
		NegocioDAO negocioDao =  new NegocioDAO();
		NegocioBean negocioClone = this.obterPorNome(negocioBean.getAreaAtuacao());
		
		if (!validarAreaAtuacao(negocioBean.getAreaAtuacao())) {
			throw new TamanhoCampoException("Por Favor, digite uma área de atuação válida!");
		} else if (negocioClone != null && negocioClone.getId() != negocioBean.getId()) {
			throw new NomeRepetidoException("Este nome já exite na base de dados");
		} else {
			negocioDao.alterar(negocioBean);
		}
	}
	
	public void remover(int id) throws ClassNotFoundException, SQLException, RegistroVinculadoException {
		NegocioDAO negocioDao = new NegocioDAO();
		if(negocioDao.verificarPorProjeto(id)){
			negocioDao.remover(id);	
		} else {
			throw new RegistroVinculadoException("Registro não pode ser removido pois possui vínculos");
			
			
		}
		
		
	}
	
	public void reativar(NegocioBean negocioBean) throws ClassNotFoundException, SQLException{
		NegocioDAO negocioDao = new NegocioDAO();
		
		negocioDao.reativar(negocioBean);
	}
	
	public boolean validarAreaAtuacao(String areaAtuacao){
		return (areaAtuacao.matches("[A-Za-zÀ-ú0-9'\\s]{1,50}"));
	}

}
