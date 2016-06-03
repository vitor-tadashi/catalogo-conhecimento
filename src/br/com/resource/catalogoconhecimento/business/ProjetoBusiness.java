package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.dao.ProjetoDAO;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;

public class ProjetoBusiness {
	
	private ProjetoDAO projetoDao;
	
	public ProjetoBusiness() {
		projetoDao = new ProjetoDAO();
	}

	// INSERE NA TABELA PROJETO
	public void inserir(ProjetoBean projetoBean)
			throws ClassNotFoundException, SQLException, TamanhoCampoException, NomeRepetidoException {
		ProjetoBean projetoClone = projetoDao.obterPorNome(projetoBean);

		if (projetoBean.getNome().length() > 150) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.150)");
		} else if (projetoClone != null
				&& projetoBean.getCliente().getNome().equals(projetoClone.getCliente().getNome())) {
			throw new NomeRepetidoException("Já existe um projeto chamado " + projetoClone.getNome() + " no "
					+ projetoClone.getCliente().getNome());
		}
		else{
			projetoDao.inserir(projetoBean);
		}
	}

	// CONSULTA NA TABELA PROJETO
	public List<ProjetoBean> listar() throws ClassNotFoundException, SQLException, ConsultaNulaException {
		List<ProjetoBean> listaProjeto = projetoDao.listar();
		
		if (listaProjeto.isEmpty()) {
			throw new ConsultaNulaException("Não há projetos cadastrados!");
		} else {
			return listaProjeto;
		}

	}

	// ATUALIZAR NA TABELA PROJETO
	public void atualizar(ProjetoBean projetoBean)
			throws ClassNotFoundException, SQLException, TamanhoCampoException, NomeRepetidoException {
		ProjetoBean projetoClone = projetoDao.obterPorNome(projetoBean);

		if (projetoBean.getNome().length() > 150) {
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.150)");
		} else if ((projetoClone != null
				&& projetoBean.getCliente().getNome().equals(projetoClone.getCliente().getNome()))
				&& projetoBean.getId() != projetoClone.getId()) {
			throw new NomeRepetidoException("Já existe um projeto chamado " + projetoClone.getNome() + " no "
					+ projetoClone.getCliente().getNome());
		} else {
			projetoDao.atualizar(projetoBean);
		}

	}

	// LISTA POR ID
	public ProjetoBean obterPorId(int idProjeto) throws ClassNotFoundException, SQLException {
		return projetoDao.obterPorId(idProjeto);

	}

	// DELETA NA TABELA PROJETO
	public void deletar(ProjetoBean projeto) throws ClassNotFoundException, SQLException {
		projetoDao.deletar(projeto);
	}

	public List<ProjetoBean> obterPorTecnologias(String nomeTecnologias)
			throws ConsultaNulaException, ClassNotFoundException, SQLException {
		List<ProjetoBean> listaProjeto = projetoDao.obterPorTecnologias(nomeTecnologias);
		
		if (listaProjeto == null) {
			throw new ConsultaNulaException("Não há projetos cadastrados!");
		} else {
			return listaProjeto;
		}
	}
	public List<ProjetoBean> obterPorNegocio(String nomeNegocio)
			throws ConsultaNulaException, ClassNotFoundException, SQLException {
		List<ProjetoBean> listaProjeto = projetoDao.obterPorNegocio(nomeNegocio);
		
		if (listaProjeto == null) {
			throw new ConsultaNulaException("Não há projetos cadastrados!");
		} else {
			return listaProjeto;
		}
	}
	
	public List<ProjetoBean> listarPorNomeCliente(String nomeCliente) throws ClassNotFoundException, SQLException {
		return projetoDao.listarPorNomeCliente(nomeCliente);
	}

}
