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
		NegocioBean negocioDesativado = this.obterNomeDesativado(negocio);
		NegocioBean negocioClone = this.obterPorNome(negocio.getAreaAtuacao());


		if(negocio.getAreaAtuacao().length() > 50){
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50)");
		}else if(negocioDesativado != null){
				this.reativar(negocio);
		}else if(negocioClone != null){			
			throw new NomeRepetidoException("Este nome já consta na base de dados");
		}else{
			negociodao.inserir(negocio);

		}
	}

	public NegocioBean obterNomeDesativado(NegocioBean negocio) throws ClassNotFoundException, SQLException{

		return new NegocioDAO().obterNomeDesativado(negocio);
	}
	
	public void reativar(NegocioBean negocio) throws ClassNotFoundException, SQLException{
		new NegocioDAO().reativar(negocio);
	}

	// Deletar
	public void deletar(int id) throws ClassNotFoundException, SQLException {

		NegocioDAO negociodao = new NegocioDAO();
		negociodao.deletar(id);

	}

	// Atualizar
	public void atualizar(NegocioBean negocio) throws ClassNotFoundException, SQLException, TamanhoCampoException, NomeRepetidoException {
		NegocioDAO negocioDao =  new NegocioDAO();
		NegocioBean negocioClone = this.obterPorNome(negocio.getAreaAtuacao());

		if(negocio.getAreaAtuacao().length() > 50){
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50");
		}else if(negocioClone != null && negocioClone.getId() != negocio.getId()){
			throw new NomeRepetidoException("Este nome já exite na base de dados");
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


	public NegocioBean obterPorNome(String nome) throws ClassNotFoundException, SQLException {

		NegocioDAO negociodao = new NegocioDAO();
		return negociodao.obterPorNome(nome);

	}
}
