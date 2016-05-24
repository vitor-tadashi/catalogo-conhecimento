package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.dao.NegocioDAO;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;

public class NegocioBusiness {

	// Inserir
	public void inserir(NegocioBean negocio) throws ClassNotFoundException, SQLException, TamanhoCampoException, NomeRepetidoException  {

		NegocioDAO negociodao = new NegocioDAO();
		NegocioBean negocioClone = negociodao.listarPorNome(negocio.getAreaAtuacao());
		
		if(negocio.getAreaAtuacao().length() > 50){
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50");
		}else if(!(negocioClone.getAreaAtuacao().equals(null))){			
			throw new NomeRepetidoException("Este nome já consta na base de dados");
		}else{
		negociodao.inserir(negocio);
		
		}
	}

	// Deletar
	public void deletar(int id) throws ClassNotFoundException, SQLException {
		
			NegocioDAO negociodao = new NegocioDAO();
			negociodao.deletar(id);
			

		
	}

	// Atualizar
	public void atualizar(NegocioBean negocio) throws ClassNotFoundException, SQLException, TamanhoCampoException {
		

			NegocioDAO negocioDao =  new NegocioDAO();
			if(negocio.getAreaAtuacao().length() > 50){
				throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50");
			}else{			
				negocioDao.atualizar(negocio);
			}
				
		
		
	}

	// Listar
	public List<NegocioBean> listar() throws ClassNotFoundException, SQLException {


			NegocioDAO negocio = new NegocioDAO();
			return negocio.listar();

	}

	// ListarporID
	public NegocioBean listarPorId(int id) throws ClassNotFoundException, SQLException {

			NegocioDAO negociodao = new NegocioDAO();
			return negociodao.listarPorId(id);
		
	}

	// ListarporNome
	public NegocioBean listarPorNome(String nome) throws ClassNotFoundException, SQLException {

			NegocioDAO negociodao = new NegocioDAO();
			return negociodao.listarPorNome(nome);
	
	}
}
