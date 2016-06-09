package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.dao.ProjetoDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.RegistroVinculadoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class ProjetoBusiness {
	
	private ProjetoDAO projetoDao;
	
	public ProjetoBusiness() {
		projetoDao = new ProjetoDAO();
	}

	// INSERE NA TABELA PROJETO
	public void inserir(ProjetoBean projetoBean)
			throws Exception {
		ProjetoBean projetoClone = projetoDao.obterPorNome(projetoBean);

		if (!validarNome(projetoBean.getNome())) {
			throw new BusinessException("Por favor, digite um nome válido!");
		} else if (projetoClone != null
				&& projetoBean.getCliente().getNome().equals(projetoClone.getCliente().getNome())) {
			throw new NomeRepetidoException("Já existe um projeto chamado " + projetoClone.getNome() + " no "
					+ projetoClone.getCliente().getNome());
		}
		else if(projetoBean.getObservacao().length() > 255){
			throw new TamanhoCampoException("Número limite de caracteres excedido(máx.255)");
		}else{
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
	public void deletar(int id) throws ClassNotFoundException, SQLException, RegistroVinculadoException {
		
		
		if(projetoDao.verificarPorEquipe(id)&& projetoDao.verificarPorNegocio(id)&& projetoDao.verificarPorTecnologia(id)){
			projetoDao.deletar(id);	
		}else{
			throw new RegistroVinculadoException("Registro não pode ser removido pois possui vínculos");
		}
		
		
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
			throws BusinessException {
		
		try{
		List<ProjetoBean> listaProjeto = projetoDao.obterPorNegocio(nomeNegocio);
		
		if (listaProjeto == null) {
			throw new ConsultaNulaException("Não há projetos cadastrados!");
		} else {
			return listaProjeto;
		}
		}catch(Exception e){
			throw ExceptionUtil.handleException(e);
		}
	}
	
	public List<ProjetoBean> listarPorNomeCliente(String nomeCliente) throws ClassNotFoundException, SQLException {
		return projetoDao.listarPorNomeCliente(nomeCliente);
	}
	
	public boolean validarNome(String nome){
		return(nome.matches("[A-Za-zÀ-ú0-9'\\s]{2,150}"));
	}
	
	public boolean validarObservacao(String observacao){
		return(observacao.matches("[A-Za-zÀ-ú0-9'@&!*\\s]{2,80}"));
	}

}
