package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.dao.ClienteDAO;

public class ClienteBusiness {

	// INSERE NA TABELA CLIENTE
	public void inserir(ClienteBean clienteBean) throws ClassNotFoundException, SQLException {
		try {
			ClienteDAO clienteDao = new ClienteDAO();

			clienteDao.inserir(clienteBean);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		}
	}

	// CONSULTA NA TABELA CLIENTE
	public List<ClienteBean> listar() throws ClassNotFoundException, SQLException {

		try {
			ClienteDAO clienteDao = new ClienteDAO();
			return clienteDao.listar();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {
			s.printStackTrace();
			return null;
		}
	}

	// ATUALIZA NA TABELA CLIENTE
	public boolean atualizar(ClienteBean clienteBean) throws ClassNotFoundException, SQLException {

		ClienteDAO clienteDao = new ClienteDAO();
		try {
			ClienteBean cliente = clienteDao.obterPorId(clienteBean.getId());
			if (cliente == null) {
				return true;
			} else {
				clienteDao.atualizar(clienteBean);
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return false;
	}

	// DELETA NA TABELA CLIENTE
	public boolean deletar(ClienteBean clienteBean) throws ClassNotFoundException, SQLException {

		try {
			ClienteDAO clienteDao = new ClienteDAO();
			ClienteBean cliente = clienteDao.obterPorId(clienteBean.getId());
			if (cliente == null) {
				return true;
			} else {
				clienteDao.deletar(clienteBean);
				return false;
			}

		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return false;
	}

	// LISTA POR ID NA TABELA CLIENTE
	public ClienteBean listarPorId(int idCliente) throws ClassNotFoundException, SQLException {

		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			return clienteDAO.obterPorId(idCliente);
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return null;
		} catch (SQLException s) {
			s.printStackTrace();
			return null;
		}
	}

}
