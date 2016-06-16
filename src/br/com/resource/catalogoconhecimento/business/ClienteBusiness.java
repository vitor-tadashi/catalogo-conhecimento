package br.com.resource.catalogoconhecimento.business;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.dao.ClienteDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class ClienteBusiness {

	private ClienteDAO clienteDao;

	public ClienteBusiness() {
		clienteDao = new ClienteDAO();
	}

	public void adicionar(ClienteBean clienteBean) throws BusinessException {
		try {
			ClienteBean clienteDesativado = this.obterNomeDesativado(clienteBean);
			ClienteBean ClienteClone = this.obterPorNome(clienteBean.getNome());

			if (!validarNome(clienteBean.getNome())) {
				throw new TamanhoCampoException("Por Favor, digite um nome válido!");
			} else if (clienteDesativado != null) {
				this.reativar(clienteBean);
			} else if (ClienteClone != null) {
				throw new NomeRepetidoException("Este nome já exite na base de dados.");
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
			throw ExceptionUtil.handleException(e);
		}
	}

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

	public void alterar(ClienteBean clienteBean) throws BusinessException {
		try {
			ClienteBean cliente = clienteDao.obterPorId(clienteBean.getId());
			ClienteBean clienteClone = this.obterPorNome(clienteBean.getNome());

			if (cliente == null) {
				throw new Exception("Cliente não consta na base de dados");
			} else if (!validarNome(clienteBean.getNome())) {
				throw new TamanhoCampoException("Por Favor, digite um nome válido!");
			} else if (clienteClone != null && clienteClone.getId() != clienteBean.getId()) {
				throw new NomeRepetidoException("Este nome já exite na base de dados.");
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

	public void remover(int idCliente) throws BusinessException {
		try {
			ClienteBean cliente = clienteDao.obterPorId(idCliente);
			if (cliente != null) {
				clienteDao.remover(idCliente);
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public ClienteBean obterPorId(int idCliente) throws BusinessException {
		try {
			return clienteDao.obterPorId(idCliente);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public ClienteBean obterPorNome(String nome) throws BusinessException {
		try {
			return clienteDao.obterPorNome(nome);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public ClienteBean obterNomeDesativado(ClienteBean clienteBean) throws BusinessException {
		try {
			return clienteDao.obterNomeDesativado(clienteBean);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public void reativar(ClienteBean clienteBean) throws BusinessException {
		try {
			ClienteDAO clienteDao = new ClienteDAO();
			clienteDao.reativar(clienteBean);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public boolean validarNome(String nome) {
		return (nome.matches("[A-Za-zÀ-ú0-9+'\\-\\s]{2,150}"));
	}

	public boolean validarEmail(String email) {
		return (email.matches("\\w+@\\w+.\\w+.?\\w+") && email.length() <= 100);
	}

	public boolean validarLogradouro(String logradouro) {
		return (logradouro.matches("[A-Za-zÀ-ú0-9+'?\\-?\\,\\.\\/\\s]+") && logradouro.length() <= 100);
	}

	public boolean validarNumero(String numero) {
		return (numero.matches("\\d+") && numero.length() <= 10);
	}

	public boolean validarCep(String cep) {
		return cep.matches("\\d{8}");
	}

	public boolean validarCnpj(String cnpj) {
		return cnpj.matches("\\d{2}.?\\d{3}.?\\d{3}/?\\d{4}-?\\d{2}");
	}

}
