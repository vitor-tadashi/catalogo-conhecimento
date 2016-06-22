package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.dao.ConcorrenteDAO;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class ConcorrenteBusiness {

	@Autowired
	private ConcorrenteDAO concorrenteDao;

	@Transactional
	public void adicionar(ConcorrenteBean concorrenteBean) throws BusinessException {
		try {
			concorrenteDao.adicionar(concorrenteBean);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void adicionarConcorrenteCliente(ConcorrenteClienteBean concorrenteClienteBean)
			throws ClassNotFoundException, SQLException, AtributoNuloException {
		if (concorrenteClienteBean.getCliente() == null) {
			throw new AtributoNuloException("Cliente Inválido!");
		} else if (concorrenteClienteBean.getConcorrente() == null) {
			throw new AtributoNuloException("Concorrente Inválido!");
		} else {
			concorrenteDao.adicionarConcorrenteCliente(concorrenteClienteBean);
		}
	}

	@Transactional
	public List<ConcorrenteBean> listar() throws BusinessException {
		try {
			List<ConcorrenteBean> listaConcorrente = concorrenteDao.listar();

			if (listaConcorrente.isEmpty()) {
				throw new ConsultaNulaException("Não há concorrentes cadastrados.");
			} else {
				return listaConcorrente;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public List<ConcorrenteClienteBean> listarPorCliente(int idCliente)
			throws ClassNotFoundException, SQLException, ConsultaNulaException {
		List<ConcorrenteClienteBean> listaConcorrenteCliente = concorrenteDao.listarPorCliente(idCliente);
		return listaConcorrenteCliente;
	}

	@Transactional
	public List<ConcorrenteClienteBean> listarPorConcorrente(int idConcorrente)
			throws SQLException, ClassNotFoundException, ConsultaNulaException {
		List<ConcorrenteClienteBean> listaConcorrenteCliente = concorrenteDao.listarPorConcorrente(idConcorrente);

		return listaConcorrenteCliente;
	}

	@Transactional
	public List<ConcorrenteClienteBean> listarPorNomeCliente(String nomeCliente)
			throws ClassNotFoundException, SQLException, BusinessException {
		return concorrenteDao.listarPorNomeCliente(nomeCliente);
	}

	@Transactional
	public ConcorrenteBean obterPorId(int idConcorrente) throws BusinessException {
		try {
			return concorrenteDao.obterPorId(idConcorrente);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public ConcorrenteBean obterPorNome(String nomeConcorrente) throws BusinessException {
		try {
			return concorrenteDao.obterPorNome(nomeConcorrente);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void alterar(ConcorrenteBean concorrenteBean) throws BusinessException {
		try {
			ConcorrenteBean concorrenteClone = this.obterPorNome(concorrenteBean.getNome());
			if (concorrenteBean.getNome().equals("")) {
				throw new AtributoNuloException("Por favor, digite um nome válido!");
			} else if (!validarNome(concorrenteBean.getNome())) {
				throw new AtributoNuloException("Por favor, digite um nome válido!");
			} else if (concorrenteBean.getNome().length() > 50) {
				throw new TamanhoCampoException("Número limite de caracteres excedido(máx.50)");
			} else if (concorrenteBean.getDescricao().length() > 255) {
				throw new TamanhoCampoException("Descrição: Número limite de caracteres excedido(máx.255)");
			} else if (concorrenteClone != null && concorrenteClone.getId() != concorrenteBean.getId()) {
				throw new NomeRepetidoException("Este nome já está cadastrado!");
			} else {
				ConcorrenteDAO concorrenteDao = new ConcorrenteDAO();
				concorrenteDao.alterar(concorrenteBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void remover(ConcorrenteBean concorrenteBean)  throws BusinessException {
		try {
//			if (!concorrenteDao.verificarPorCliente(idConcorrente)) {
				concorrenteDao.remover(concorrenteBean);
//			} else {
//				throw new RegistroVinculadoException("Registro não pode ser removido pois possui vínculos.");
//			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}
	
	@Transactional
	public void removerConcorrenteCliente(int idCliente, int idConcorrente)
			throws ClassNotFoundException, SQLException {
		concorrenteDao.removerConcorrenteCliente(idCliente, idConcorrente);
	}

	@Transactional
	public boolean validarNome(String nome) {
		return (nome.matches("[A-Za-zÀ-ú0-9+'\\-\\s]{2,100}"));
	}

}
