package br.com.resource.catalogoconhecimento.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.dao.ClienteDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class ClienteBusiness {

	@Autowired
	private ClienteDAO clienteDao;

	/**
	 * -ABS- Adiciona um novo Cliente na tabela 'Cliente' através da ClienteDAO
	 * 
	 * @return void
	 * @param clienteBean
	 * @throws BusinessException
	 */
	@Transactional
	public void adicionar(ClienteBean clienteBean) throws BusinessException {
		try {
			List<ClienteBean> clienteClone = this.obterPorNome(clienteBean.getNome());
			if (!validarNome(clienteBean.getNome())) {
				throw new TamanhoCampoException("Por Favor, digite um nome válido!");
			} else if (!clienteClone.isEmpty()) {
				throw new NomeRepetidoException("Este nome já está cadastrado");
			} else if (!validarEmail(clienteBean.getEmail())) {
				throw new Exception("Email inválido");
			} else if (!validarLogradouro(clienteBean.getLogradouro())) {
				throw new Exception("Logradouro inválido");
			} else if (!validarNumero(clienteBean.getNumero())) {
				throw new Exception("Número inválido");
			} else if (!validarCep(clienteBean.getCep())) {
				throw new Exception("CEP inválido");
			} else if (!validarCnpj(clienteBean.getCnpj())) {
				throw new Exception("CNPJ inválido");
			} else {
				if (!clienteDao.verificarPorCnpj(clienteBean.getCnpj()))
					clienteDao.adicionar(clienteBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
	}

	/**
	 * -JPQL- Retorna uma lista com todos os Clientes ATIVOS da tabela Cliente
	 * através da ClienteDAO
	 * 
	 * @param no
	 *            parameters
	 * @return listaCliente (retorna a lista de clientes)
	 * @throws BusinessException
	 */
	@Transactional
	public List<ClienteBean> listar() throws BusinessException {
		try {
			List<ClienteBean> listaCliente = clienteDao.listar();
			if (listaCliente.isEmpty()) {
				throw new ConsultaNulaException("Não há clientes cadastrados");
			} else {
				return listaCliente;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	/**
	 * -JPQL- Retorna um objeto Cliente ATIVO (buscado através do ID passado
	 * como parâmetro)
	 * 
	 * @param idCliente
	 * @return ClienteBean
	 * @throws BusinessException
	 */
	public ClienteBean obterPorId(int idCliente) throws BusinessException {
		try {
			return clienteDao.obterPorId(idCliente).get(0);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	/**
	 * -JPQL- Retorna um objeto Cliente ATIVO (buscado através do NOME passado
	 * como parâmetro)
	 * 
	 * @param nomeCliente
	 * @return ClienteBean
	 * @throws BusinessException
	 */
	public List<ClienteBean> obterPorNome(String nomeCliente) throws BusinessException {
		try {
			return clienteDao.obterPorNome(nomeCliente);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
	}

	/**
	 * -ABS- Altera informações de um Cliente existente através da ClienteDAO
	 * 
	 * @param clienteBean
	 * @return void
	 * @throws BusinessException
	 */
	@Transactional
	public void alterar(ClienteBean clienteBean) throws BusinessException {
		try {
			List<ClienteBean> clienteClone = this.obterPorNome(clienteBean.getNome());
			if (!validarNome(clienteBean.getNome())) {
				throw new TamanhoCampoException("Por Favor, digite um nome válido!");
			} else if (clienteClone != null && clienteClone.get(0).getId() != clienteBean.getId()) {
				throw new NomeRepetidoException("Este nome já está cadastrado.");
			} else if (!validarEmail(clienteBean.getEmail())) {
				throw new Exception("Email inválido");
			} else if (!validarLogradouro(clienteBean.getLogradouro())) {
				throw new Exception("Logradouro inválido");
			} else if (!validarNumero(clienteBean.getNumero())) {
				throw new Exception("Número inválido");
			} else if (!validarCep(clienteBean.getCep())) {
				throw new Exception("CEP inválido");
			} else if (!validarCnpj(clienteBean.getCnpj())) {
				throw new Exception("CNPJ inválido");
			} else {
				clienteDao.alterar(clienteBean);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	/**
	 * -ABS- Faz a remoção LÓGICA de Cliente (altera status ativo para 'n')
	 * através da ClienteDAO
	 * 
	 * @param clienteBean
	 * @return void
	 * @throws BusinessException
	 */
	public void remover(ClienteBean clienteBean) throws BusinessException {
		try {
			clienteDao.remover(clienteBean);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	/**
	 * Faz a validação do nome com Expressão Regular (RegEx) para não permitir
	 * caracteres especiais e mais de 150 caracteres.
	 * 
	 * @param nome
	 * @return TRUE = nome é válido / FALSE = nome inválido
	 */
	public boolean validarNome(String nome) {
		return (nome.matches("[A-Za-zÀ-ú0-9+'\\-\\s]{2,150}"));
	}

	/**
	 * Faz a validação do e-mail com Expressão Regular (RegEx) para não permitir
	 * a inserção de um e-mail fora do padrão (abc@def.gh) e com mais de 100
	 * caracteres.
	 * 
	 * @param email
	 * @return TRUE = email é válido / FALSE = email inválido
	 */
	public boolean validarEmail(String email) {
		return (email.matches("\\w+@\\w+.\\w+.?\\w+") && email.length() <= 100);
	}

	/**
	 * Faz a validação do logradouro com Expressão Regular (RegEx) para não
	 * permitir caracteres especiais e mais de 100 caracteres.
	 * 
	 * @param logradouro
	 * @return TRUE = logradouro é válido / FALSE = logradouro inválido
	 */
	public boolean validarLogradouro(String logradouro) {
		return (logradouro.matches("[A-Za-zÀ-ú0-9+'?\\-?\\,\\.\\/\\s]+") && logradouro.length() <= 100);
	}

	/**
	 * Faz a validação do numero com Expressão Regular (RegEx) para não permitir
	 * caracteres especiais e mais de 10 caracteres.
	 * 
	 * @param numero
	 * @return TRUE = numero é válido / FALSE = numero inválido
	 */
	public boolean validarNumero(String numero) {
		return (numero.matches("\\d+") && numero.length() <= 10);
	}

	/**
	 * Faz a validação do cep com Expressão Regular (RegEx) para exigir a
	 * entrada de 8 numeros
	 * 
	 * @param cep
	 * @return TRUE = cep é válido / FALSE = cep inválido
	 */
	public boolean validarCep(String cep) {
		return cep.matches("\\d{8}");
	}

	/**
	 * Faz a validação do cnpj com Expressão Regular (RegEx) exigir a entrada de
	 * um CNPJ no padrão 11.111.111/0001-11
	 * 
	 * @param cnpj
	 * @return TRUE = cnpj é válido / FALSE = cnpj inválido
	 */
	public boolean validarCnpj(String cnpj) {
		return cnpj.matches("\\d{2}.?\\d{3}.?\\d{3}/?\\d{4}-?\\d{2}");
	}

}
