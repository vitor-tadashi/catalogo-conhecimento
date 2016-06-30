package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private ProjetoDAO projetoDao;

	public ProjetoBusiness() {
		projetoDao = new ProjetoDAO();
	}

	// INSERE NA TABELA PROJETO
	public void adicionar(ProjetoBean projetoBean) throws BusinessException {
		try {

			ProjetoBean projetoClone = projetoDao.obterPorNome(projetoBean);

			if (!validarNome(projetoBean.getNome())) {
				throw new BusinessException("Por favor, digite um nome valido!");
			} else if (projetoClone != null
					&& projetoBean.getCliente().getNome().equals(projetoClone.getCliente().getNome())) {
				throw new NomeRepetidoException("Ja existe um projeto chamado " + projetoClone.getNome() + " no "
						+ projetoClone.getCliente().getNome());
			} else if (projetoBean.getObservacao().length() > 255) {
				throw new TamanhoCampoException("Numero limite de caracteres excedido(max.255)");
			} else {
				projetoDao.adicionar(projetoBean);
			}

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	// CONSULTA NA TABELA PROJETO
	public List<ProjetoBean> listar() throws BusinessException {
		try {
			List<ProjetoBean> listaProjeto = projetoDao.listar();
			if (listaProjeto.isEmpty()) {
				throw new ConsultaNulaException("N�o h� projetos cadastrados!");
			} else {
				return listaProjeto;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	// ATUALIZAR NA TABELA PROJETO
	public void atualizar(ProjetoBean projetoBean) throws BusinessException {
		try {
			ProjetoBean projetoClone = projetoDao.obterPorNome(projetoBean);

			if (projetoBean.getNome().length() > 150) {
				throw new TamanhoCampoException("Numero limite de caracteres excedido(max.150)");
			} else if ((projetoClone != null
					&& projetoBean.getCliente().getNome().equals(projetoClone.getCliente().getNome()))
					&& projetoBean.getId() != projetoClone.getId()) {
				throw new NomeRepetidoException("Ja existe um projeto chamado " + projetoClone.getNome() + " no "
						+ projetoClone.getCliente().getNome());
			} else {
				projetoDao.alterar(projetoBean);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	// LISTA POR ID
	public ProjetoBean obterPorId(int idProjeto) throws BusinessException {
		try {
			return projetoDao.obterPorId(idProjeto);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}

	}

	// DELETA NA TABELA PROJETO
	public void remover(int id) throws BusinessException {
		try {

			if (projetoDao.verificarPorEquipe(id) && projetoDao.verificarPorNegocio(id)
					&& projetoDao.verificarPorTecnologia(id)) {
				projetoDao.remover(id);
			} else {
				throw new RegistroVinculadoException("Registro nao pode ser removido pois possui vinculos");
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public List<ProjetoBean> obterPorTecnologias(String nomeTecnologias) throws BusinessException {
		try {
			List<ProjetoBean> listaProjeto = projetoDao.obterPorTecnologias(nomeTecnologias);

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
			List<ProjetoBean> listaProjeto = projetoDao.obterPorNegocio(nomeNegocio);

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
		return projetoDao.listarPorNomeCliente(nomeCliente);
	}

	public boolean validarNome(String nome) {
		return (nome.matches("[A-Za-z�-�0-9'\\s]{2,150}"));
	}

	public boolean validarObservacao(String observacao) {
		return (observacao.matches("[A-Za-z�-�0-9'@&!*\\s]{2,80}"));
	}
}
