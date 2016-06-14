package br.com.resource.catalogoconhecimento.business;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.dao.ConcorrenteDAO;
import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.RegistroVinculadoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class ConcorrenteBusiness {

	private ConcorrenteDAO concorrenteDao;

	public ConcorrenteBusiness() {
		concorrenteDao = new ConcorrenteDAO();
	}

	public void adicionar(ConcorrenteBean concorrenteBean) throws BusinessException {
		try {
			ConcorrenteBean concorrenteClone = this.obterPorNome(concorrenteBean.getNome());
			if (concorrenteBean.getDescricao().equals("")) {
				concorrenteBean.setDescricao("-");
			}
			if (!validarNome(concorrenteBean.getNome())) {
				throw new AtributoNuloException("Por favor, digite um nome válido!");
			} else if (concorrenteBean.getNome().length() > 100) {
				throw new TamanhoCampoException("Nome: Número limite de caracteres excedido(máx.50)");
			} else if (concorrenteBean.getDescricao().length() > 255) {
				throw new TamanhoCampoException("Descrição: Número limite de caracteres excedido(máx.255)");
			} else if (concorrenteClone != null && concorrenteClone.getId() != concorrenteBean.getId()) {
				throw new NomeRepetidoException("Este nome já está cadastrado!");
			} else {
				concorrenteDao.adicionar(concorrenteBean);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public void adicionarConcorrenteCliente(ConcorrenteClienteBean concorrenteClienteBean) throws BusinessException {
		try {
			if (concorrenteClienteBean.getCliente() == null) {
				throw new AtributoNuloException("Cliente Inválido!");
			} else if (concorrenteClienteBean.getConcorrente() == null) {
				throw new AtributoNuloException("Concorrente Inválido!");
			} else {
				concorrenteDao.adicionarConcorrenteCliente(concorrenteClienteBean);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public List<ConcorrenteBean> listar() throws BusinessException {
		try {
			List<ConcorrenteBean> listaConcorrente = concorrenteDao.listar();
			if (listaConcorrente.isEmpty()) {
				throw new ConsultaNulaException("Não há concorrentes cadastrados");
			} else {
				return listaConcorrente;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public List<ConcorrenteClienteBean> listarPorConcorrente(int idConcorrente) throws BusinessException {
		try {
			List<ConcorrenteClienteBean> listaConcorrenteCliente = concorrenteDao.listarPorConcorrente(idConcorrente);
			return listaConcorrenteCliente;
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public List<ConcorrenteClienteBean> listarPorNomeCliente(String nomeCliente) throws BusinessException {
		try {
			return concorrenteDao.listarPorNomeCliente(nomeCliente);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public ConcorrenteBean obterPorId(int idConcorrente) throws BusinessException {
		try {
			return concorrenteDao.obterPorId(idConcorrente);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public ConcorrenteBean obterPorNome(String nomeConcorrente) throws BusinessException {
		try {
			return concorrenteDao.obterPorNome(nomeConcorrente);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public boolean existe(ConcorrenteBean concorrenteBean) throws BusinessException {
		try {
			return concorrenteDao.existe(concorrenteBean);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public List<ConcorrenteClienteBean> listarPorCliente(int idCliente) throws BusinessException {
		try {
			List<ConcorrenteClienteBean> listaConcorrenteCliente = concorrenteDao.listarPorCliente(idCliente);
			return listaConcorrenteCliente;
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public void alterar(ConcorrenteBean concorrenteBean) throws BusinessException {
		ConcorrenteBean concorrenteClone = this.obterPorNome(concorrenteBean.getNome());
		try {
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
			throw ExceptionUtil.handleException(e);
		}
	}

	public void remover(int idConcorrente) throws BusinessException {
		try {
			if (!concorrenteDao.verificarPorCliente(idConcorrente)) {
				concorrenteDao.remover(idConcorrente);
			} else {
				throw new RegistroVinculadoException("Registro não pode ser removido pois possui vínculos");
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public void removerConcorrenteCliente(int idCliente, int idConcorrente) throws BusinessException {
		try {
			concorrenteDao.removerConcorrenteCliente(idCliente, idConcorrente);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public boolean validarNome(String nome) {
		return (nome.matches("[A-Za-zÀ-ú0-9+'\\-\\s]{2,100}"));
	}

}
