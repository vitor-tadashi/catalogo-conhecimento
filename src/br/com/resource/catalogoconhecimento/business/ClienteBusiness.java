package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.dao.ClienteDAO;

public class ClienteBusiness {

	public void adicionar(ClienteBean clienteBean) throws ClassNotFoundException, SQLException, Exception {
		if (!validarNome(clienteBean.getNome())) {
			throw new Exception("Nome inválido");
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
			ClienteDAO clienteDao = new ClienteDAO();
			if (!clienteDao.verificarPorCnpj(clienteBean.getCnpj())) {
				clienteDao.adicionar(clienteBean);
			} else {
				throw new Exception("CNPJ já consta na base de dados");
			}
		}
	}

	public List<ClienteBean> listar() throws ClassNotFoundException, SQLException {
		ClienteDAO clienteDao = new ClienteDAO();
		return clienteDao.listar();
	}

	public void alterar(ClienteBean clienteBean) throws ClassNotFoundException, SQLException, Exception {
		ClienteDAO clienteDao = new ClienteDAO();
		ClienteBean cliente = clienteDao.obterPorId(clienteBean.getId());

		if (cliente == null) {
			throw new Exception("Cliente não consta na base de dados");
		} else if (!validarNome(clienteBean.getNome())) {
			throw new Exception("Nome inválido");
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
	}

	public void remover(ClienteBean clienteBean) throws ClassNotFoundException, SQLException {
		ClienteDAO clienteDao = new ClienteDAO();
		ClienteBean cliente = clienteDao.obterPorId(clienteBean.getId());

		if (cliente != null) {
			clienteDao.remover(clienteBean);
		}
	}

	public ClienteBean obterPorId(int idCliente) throws ClassNotFoundException, SQLException {
		ClienteDAO clienteDAO = new ClienteDAO();
		return clienteDAO.obterPorId(idCliente);
	}

	public boolean validarNome(String nome) {
		return (nome.matches("[A-Za-zÀ-ú0-9+'?\\-?\\s]+") && nome.length() <= 150);
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
