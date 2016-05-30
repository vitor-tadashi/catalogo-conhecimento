package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.dao.ProjetoDAO;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;

public class ProjetoBusiness {

	// INSERE NA TABELA PROJETO
	public void inserir(ProjetoBean projetoBean) throws ClassNotFoundException, SQLException, TamanhoCampoException, NomeRepetidoException {


		ProjetoDAO projetoDAO = new ProjetoDAO();
		ProjetoBean projetoDesativado = projetoDAO.obterPorNome(projetoBean);
		ProjetoBean projetoClone = projetoDAO.obterPorNome(projetoBean);

		if(projetoBean.getNome().length() > 150){
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.150)");
		}else if(projetoDesativado != null && projetoBean.getCliente().getNome().equals(projetoClone.getCliente().getNome())){
				projetoDAO.reativar(projetoBean);
		}else if(projetoClone != null && projetoBean.getCliente().getNome().equals(projetoClone.getCliente().getNome())){
			throw new NomeRepetidoException("Já existe um projeto chamado " + projetoClone.getNome() + "no "+projetoClone.getCliente().getNome());
		}else{
			projetoDAO.inserir(projetoBean);
		}





	}

	// CONSULTA NA TABELA PROJETO
	public List<ProjetoBean> listar() throws ClassNotFoundException, SQLException, ConsultaNulaException {

		ProjetoDAO projeto = new ProjetoDAO();
		List<ProjetoBean>listaProjeto = projeto.listar();
		 if(listaProjeto == null){
			 throw new ConsultaNulaException("Não há projetos cadastrados!");
		 }else{
			 return listaProjeto; 
		 }

	}

	// ATUALIZAR NA TABELA PROJETO
	public void atualizar(ProjetoBean projetoBean) throws ClassNotFoundException, SQLException, TamanhoCampoException, NomeRepetidoException {

		ProjetoDAO projetoDAO = new ProjetoDAO();
	
		ProjetoBean projetoClone = projetoDAO.obterPorNome(projetoBean);

		if(projetoBean.getNome().length() > 150){
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.150)");
		}else if((projetoClone != null && projetoBean.getCliente().getNome().equals(projetoClone.getCliente().getNome())) && projetoBean.getId() == projetoClone.getId()){
			throw new NomeRepetidoException("Já existe um projeto chamado " + projetoClone.getNome() + "no "+projetoClone.getCliente().getNome());
		}else{
			projetoDAO.atualizar(projetoBean);
		}

	}

	// LISTA POR ID
	public ProjetoBean obterPorId(int idProjeto) throws ClassNotFoundException, SQLException {

		ProjetoDAO projeto = new ProjetoDAO();
		return projeto.obterPorId(idProjeto);


	}

	// DELETA NA TABELA PROJETO
	public void deletar(ProjetoBean projeto) throws ClassNotFoundException, SQLException {



		ProjetoDAO projetodao = new ProjetoDAO();
		projetodao.deletar(projeto);




	}
}
