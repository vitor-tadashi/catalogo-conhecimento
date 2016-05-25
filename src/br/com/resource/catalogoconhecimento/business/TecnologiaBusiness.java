package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.dao.TecnologiaDAO;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;

public class TecnologiaBusiness {

	// CRIA
	public void adicionar(TecnologiaBean tecnologia) throws ClassNotFoundException, SQLException, TamanhoCampoException, NomeRepetidoException {
		
			TecnologiaDAO tecnologiaDao = new TecnologiaDAO();
			TecnologiaBean tecnologiaDesativada = tecnologiaDao.obterNomeDesativado(tecnologia);
			TecnologiaBean tecnologiaClone = tecnologiaDao.obterPorNome(tecnologia.getNome());

			if(tecnologia.getNome().length() > 50){
				throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50)");
			}else if(tecnologiaDesativada!= null){			
					tecnologiaDao.reativar(tecnologia);
					
			}else if(tecnologiaClone != null ){
				throw new NomeRepetidoException("Este nome já consta na base de dados");
			}else{
				tecnologiaDao.adicionar(tecnologia);
			
			}
	
	}

	// LISTA
	public List<TecnologiaBean> listar() throws ClassNotFoundException, SQLException {
		
			TecnologiaDAO tecnologia = new TecnologiaDAO();
			return tecnologia.listar();
		
	}

	// LISTA POR ID
	public TecnologiaBean obterPorId(int idTecnologia) throws ClassNotFoundException, SQLException {
		
			TecnologiaDAO tecnologiaDao = new TecnologiaDAO();
			return tecnologiaDao.obterPorId(idTecnologia);
		
	}
	
	public TecnologiaBean obterPorNome(String nome) throws ClassNotFoundException, SQLException {
		TecnologiaDAO tecnologiaDao = new TecnologiaDAO();
	
			return tecnologiaDao.obterPorNome(nome);
		
	}

	// ATUALIZA
	public void alterar(TecnologiaBean tecnologia) throws ClassNotFoundException, SQLException, TamanhoCampoException, NomeRepetidoException {
		TecnologiaDAO tecnologiaDao = new TecnologiaDAO();
		TecnologiaBean tecnologiaClone = tecnologiaDao.obterPorNome(tecnologia.getNome());

		if(tecnologia.getNome().length() > 50){
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50)");
		}else if(tecnologiaClone != null && tecnologiaClone.getId() != tecnologia.getId()){
			throw new NomeRepetidoException("Este nome já exite na base de dados");
		}else{
			tecnologiaDao.alterar(tecnologia);
		
		}
			

	}

	// DELETA
	public void remover(int id) throws ClassNotFoundException, SQLException {
		
			TecnologiaDAO tecnologiaDao = new TecnologiaDAO();
			tecnologiaDao.remover(id);
			

	
	}

}