package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.resource.catalogoconhecimento.bean.ConcorrenteBean;
import br.com.resource.catalogoconhecimento.bean.ConcorrenteClienteBean;
import br.com.resource.catalogoconhecimento.dao.ConcorrenteClienteDAO;
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

	@Autowired
	private ConcorrenteClienteDAO concorrenteClienteDAO;

	/**
	 * -ABS- Adiciona um novo Concorrente na tabela 'Concorrente' através da
	 * ConcorrenteDAO
	 * 
	 * @return void
	 * @param concorrenteBean
	 * @throws BusinessException
	 */
	@Transactional
	public void adicionar(ConcorrenteBean concorrenteBean) throws BusinessException {
		try {
			ConcorrenteBean concorrenteClone = this.obterPorNome(concorrenteBean.getNome());
			if (concorrenteBean.getDescricao().equals("")) {
				concorrenteBean.setDescricao("-");
			}
			if (!validarNome(concorrenteBean.getNome())) {
				throw new AtributoNuloException("Por favor, digite um nome válido!");
			} else if (concorrenteBean.getDescricao().length() > 255) {
				throw new TamanhoCampoException("Descrição: Número limite de caracteres excedido(máx.255)");
			} else if (concorrenteClone != null && concorrenteClone.getId() != concorrenteBean.getId()) {
				throw new NomeRepetidoException("Este nome já está cadastrado!");
			} else {
				salvarConcorrente(concorrenteBean);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}



	/**
	 * -SQL- Adiciona um objeto ConcorrenteClienteBean na tabela
	 * 'ConcorrenteCliente' através da ConcorrenteDAO
	 * 
	 * @param concorrenteClienteBean
	 * @return void
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws AtributoNuloException
	 */
	@Transactional
	public void adicionarConcorrenteCliente(ConcorrenteClienteBean concorrenteClienteBean) throws BusinessException {
		try {
			// if (concorrenteClienteBean.getIdCliente() == null) {
			// throw new AtributoNuloException("Cliente inválido");
			// } else if (concorrenteClienteBean.getIdConcorrente() == null) {
			// throw new AtributoNuloException("Concorrente inv�lido");
			// } else {
			concorrenteClienteDAO.adicionar(concorrenteClienteBean);
			// }
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
	}

	/**
	 * -JPQL- Retorna uma lista com todos os Concorrentes ATIVOS da tabela de
	 * Concorrentes através da ConcorrenteDAO
	 * 
	 * @param no
	 *            parameters
	 * @return listaConcorrente (retorna a lista de concorrentes)
	 * @throws BusinessException
	 */
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

	/**
	 * -SQL- Retorna uma lista com todos os Concorrentes relacionados ao Cliente
	 * (se estiver ativo) e o respectivo valor/hora
	 * 
	 * @param idCliente
	 * @return Retorna uma lista contendo objetos ConcorrenteCliente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ConsultaNulaException
	 */
	@Transactional
	public List<ConcorrenteClienteBean> listarPorCliente(int idCliente) throws BusinessException {
		try {
			return concorrenteDao.listarPorCliente(idCliente);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
	}

	/**
	 * -SQL- Retorna uma lista com todos os Clientes relacionados ao Concorrente
	 * (se estiver ativo) e o respectivo valor/hora
	 * 
	 * @param idConcorrente
	 * @return Retorna uma lista contendo objetos ConcorrenteCliente
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ConsultaNulaException
	 */
	@Transactional
	public List<ConcorrenteClienteBean> listarPorConcorrente(int idConcorrente) throws BusinessException {
		try {
			return concorrenteDao.listarPorConcorrente(idConcorrente);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	/**
	 * -JPQL- Retorna um objeto Concorrente ATIVO (buscado através do ID passado
	 * como parâmetro)
	 * 
	 * @param idConcorrente
	 * @return ConcorrenteBean
	 * @throws BusinessException
	 */
	@Transactional
	public ConcorrenteBean obterPorId(int idConcorrente) throws BusinessException {
		try {
			return concorrenteDao.obterPorId(idConcorrente);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	/**
	 * -JPQL- Retorna uma lista de Concorrentes ATIVOS (buscados através do NOME
	 * passado como parâmetro)
	 * 
	 * @param nomeConcorrente
	 * @return List<ConcorrenteBean>
	 * @throws BusinessException
	 */
	@Transactional
	public ConcorrenteBean obterPorNome(String nomeConcorrente) throws BusinessException {
		try {
			return concorrenteDao.obterPorNome(nomeConcorrente);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	/**
	 * -ABS- Altera informações de um Concorrente existente através da
	 * ConcorrenteDAO
	 * 
	 * @param concorrenteBean
	 * @return void
	 * @throws BusinessException
	 */
	@Transactional
	public void alterar(ConcorrenteBean concorrenteBean) throws BusinessException {
		try {
			ConcorrenteBean concorrenteClone = this.obterPorNome(concorrenteBean.getNome());
			if (!validarNome(concorrenteBean.getNome())) {
				throw new AtributoNuloException("Por favor, digite um nome sem caracteres especiais.");
			} else if (concorrenteBean.getDescricao().length() > 255) {
				throw new TamanhoCampoException("Descrição: Número limite de caracteres excedido(máx.255)");
			} else if (concorrenteClone != null && concorrenteClone.getId() != concorrenteBean.getId()) {
				throw new NomeRepetidoException("Este nome já está cadastrado!");
			} else {
				concorrenteDao.alterar(concorrenteBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
	}

	/**
	 * -ABS- Faz a remoção LÓGICA de Concorrente (altera status ativo para 'n')
	 * através da ConcorrenteDAO
	 * 
	 * @param concorrenteBean
	 * @return void
	 * @throws BusinessException
	 */
	@Transactional
	public void remover(ConcorrenteBean concorrenteBean) throws BusinessException {
		try {
			// if (!concorrenteDao.verificarPorCliente(idConcorrente)) {
			concorrenteBean.setAtivo('N');
			concorrenteDao.remover(concorrenteBean);
			// } else {
			// throw new RegistroVinculadoException("Registro não pode ser
			// removido pois possui vínculos.");
			// }
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
	}

	/**
	 * -JPQL- Faz a remoção FÍSICA de um Concorrente relacionado ao Cliente
	 * atual (Remoção de ConcorrenteCliente na tabela ConcorrenteCliente)
	 * 
	 * @param idCliente
	 * @param idConcorrente
	 * @return void
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Transactional
	public void removerEntidadeDaLista(int idConcorrenteCliente) throws BusinessException {
		try {
			concorrenteDao.removerEntidadeDaLista(idConcorrenteCliente);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
	}
	
	/**
	 * -JPQL- Faz a remoção FÍSICA de todos os Concorrentes relacionados ao
	 * Cliente atual na tabela ConcorrenteCliente
	 * 
	 * @param idCliente
	 * @param idConcorrente
	 * @return void
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Transactional
	public void removerConcorrenteCliente(int idCliente) throws BusinessException {
		try {
			concorrenteDao.removerConcorrenteCliente(idCliente);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
	}
	
	/**
	 * -JPQL- Faz a remoção FÍSICA de todos os Concorrentes relacionados ao
	 * Cliente atual na tabela ConcorrenteCliente
	 * 
	 * @param idCliente
	 * @param idConcorrente
	 * @return void
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	@Transactional
	public void removerClienteConcorrente(int idConcorrente) throws BusinessException {
		try {
			concorrenteDao.removerClienteConcorrente(idConcorrente);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
	}
	
	public ConcorrenteClienteBean obterPorConcorrente(int id) throws BusinessException{
		try {
			return concorrenteDao.obterPorConcorrente(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
		
	}

	/**
	 * Faz a validação do nome com Expressão Regular (RegEx) para não permitir
	 * caracteres especiais e mais de 100 caracteres.
	 * 
	 * @param nome
	 * @return TRUE = nome é válido / FALSE = nome inválido
	 */
	@Transactional
	public boolean validarNome(String nome) {
		return (nome.matches("[A-Za-zÀ-ú0-9+'\\-\\s]{2,100}"));
	}
	
	
	private void salvarConcorrente(ConcorrenteBean concorrenteBean) throws BusinessException {
		List<ConcorrenteClienteBean> lista = new ArrayList<>();
		if (concorrenteBean.getListaClientes() != null) {
			for (ConcorrenteClienteBean concorrenteCliente : concorrenteBean.getListaClientes()) {
				concorrenteCliente.setCliente(concorrenteCliente.getCliente());
				concorrenteCliente.setConcorrente(concorrenteBean);
				lista.add(concorrenteCliente);

			}
		}
		concorrenteBean.setListaClientes(lista);
		concorrenteDao.adicionar(concorrenteBean);
		for (ConcorrenteClienteBean concorrenteCliente : lista) {
			this.adicionarConcorrenteCliente(concorrenteCliente);
		}
		
	}

}
