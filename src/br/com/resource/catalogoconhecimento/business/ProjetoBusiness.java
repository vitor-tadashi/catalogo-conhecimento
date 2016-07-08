package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.dao.ClienteDAO;
import br.com.resource.catalogoconhecimento.dao.EquipeDAO;
import br.com.resource.catalogoconhecimento.dao.NegocioDAO;
import br.com.resource.catalogoconhecimento.dao.ProjetoDAO;
import br.com.resource.catalogoconhecimento.dao.TecnologiaDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.RegistroVinculadoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class ProjetoBusiness {

	@Autowired
	private ProjetoDAO projetoDAO;
	
	@Autowired
	private TecnologiaDAO tecnologiaDAO;
	
	@Autowired
	private EquipeDAO equipeDAO;
	
	@Autowired
	private NegocioDAO negocioDAO;
	
	@Autowired
	private ClienteDAO clienteDAO;

	// INSERE NA TABELA PROJETO
	@Transactional
	public void adicionar(ProjetoBean projetoBean) throws BusinessException {
		try {
			validarProjeto(projetoBean, false);
			salvarProjeto(projetoBean);

		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
	}

	private void salvarProjeto(ProjetoBean projetoBean) throws ClassNotFoundException, SQLException {
		tratarProjeto(projetoBean);
		projetoDAO.adicionar(projetoBean);
	}
	
	private void alterarProjeto(ProjetoBean projetoBean) throws ClassNotFoundException, SQLException {
		tratarProjeto(projetoBean);
		projetoDAO.alterar(projetoBean);
	}

	private void tratarProjeto(ProjetoBean projetoBean) throws SQLException, ClassNotFoundException {
        List<TecnologiaBean> tecnologias = new ArrayList<TecnologiaBean>();
        List<NegocioBean> negocios = new ArrayList<NegocioBean>();
        List<EquipeBean> equipes = new ArrayList<EquipeBean>();
        
        
        if (projetoBean.getListaTecnologia() != null) {
        	projetoBean.getListaTecnologia().removeIf(p -> p == null || (p != null && p.getId() <= 0));
        	for (TecnologiaBean tecnologia : projetoBean.getListaTecnologia()) {
                tecnologias.add(tecnologiaDAO.obterPorId(tecnologia.getId()));
            }
        }
        
        if (projetoBean.getListaNegocio() != null) {
        	projetoBean.getListaNegocio().removeIf(p -> p == null || (p != null && p.getId() == null));
            for (NegocioBean negocio : projetoBean.getListaNegocio()) {
                negocios.add(negocioDAO.obterPorId(negocio.getId()));
            }
        }
        
        if (projetoBean.getListaEquipe() != null) {
        	for (EquipeBean equipe : projetoBean.getListaEquipe()) {
            	equipes.add(equipeDAO.obterPorId(equipe.getId()));
            }
        }

        projetoBean.setListaTecnologia(tecnologias);
        projetoBean.setListaNegocio(negocios);
        projetoBean.setListaEquipe(equipes);
        projetoBean.setCliente(clienteDAO.obterPorId(projetoBean.getCliente().getId()));
	}

	private void validarProjeto(ProjetoBean projetoBean, Boolean alteracao) throws SQLException, ClassNotFoundException, BusinessException,
			NomeRepetidoException, TamanhoCampoException {
		ProjetoBean projetoClone = projetoDAO.obterPorNome(projetoBean.getNome());

		if (!validarNome(projetoBean.getNome())) {
			throw new BusinessException("Por favor, digite um nome valido!");
		} else if (projetoClone != null
				&& (!alteracao || projetoBean.getId() != projetoClone.getId())) {
			throw new NomeRepetidoException("Ja existe um projeto chamado " + projetoClone.getNome() + " no "
					+ projetoClone.getCliente().getNome());
		} else if (projetoBean.getObservacao().length() > 255) {
			throw new TamanhoCampoException("Numero limite de caracteres excedido(max.255)");
		}
	}

	// CONSULTA NA TABELA PROJETO
	public List<ProjetoBean> listar() throws BusinessException {
		try {
			List<ProjetoBean> listaProjeto = projetoDAO.listar();
			if (listaProjeto.isEmpty()) {
				throw new ConsultaNulaException("Não há projetos cadastrados!");
			} else {
				return listaProjeto;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	// ATUALIZAR NA TABELA PROJETO
	@Transactional
	public void atualizar(ProjetoBean projetoBean) throws BusinessException {
		try {
			validarProjeto(projetoBean, true);
			alterarProjeto(projetoBean);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
	}

	// LISTA POR ID
	public ProjetoBean obterPorId(int idProjeto) throws BusinessException {
		try {
			return projetoDAO.obterPorId(idProjeto);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}

	}

	// DELETA NA TABELA PROJETO
	@Transactional
	public void remover(int id) throws BusinessException {
		try {

			if (projetoDAO.verificarPorEquipe(id) && projetoDAO.verificarPorNegocio(id)
					&& projetoDAO.verificarPorTecnologia(id)) {
				projetoDAO.remover(id);
			} else {
				throw new RegistroVinculadoException("Registro nao pode ser removido pois possui vinculos");
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public List<ProjetoBean> obterPorTecnologias(String nomeTecnologias) throws BusinessException {
		try {
			List<ProjetoBean> listaProjeto = projetoDAO.obterPorTecnologias(nomeTecnologias);

			if (listaProjeto == null) {
				throw new ConsultaNulaException("Nao ha projetos cadastrados!");
			} else {
				return listaProjeto;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public List<ProjetoBean> obterPorNegocio(String nomeNegocio) throws BusinessException {

		try {
			List<ProjetoBean> listaProjeto = projetoDAO.obterPorNegocio(nomeNegocio);

			if (listaProjeto == null) {
				throw new ConsultaNulaException("Nao ha projetos cadastrados!");
			} else {
				return listaProjeto;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public List<ProjetoBean> listarPorNomeCliente(String nomeCliente) throws ClassNotFoundException, SQLException {
		return projetoDAO.listarPorNomeCliente(nomeCliente);
	}

	public boolean validarNome(String nome) {
		return (nome.matches("[A-Za-z�-�0-9'\\s]{2,150}"));
	}

	public boolean validarObservacao(String observacao) {
		return (observacao.matches("[A-Za-z�-�0-9'@&!*\\s]{2,80}"));
	}
}
