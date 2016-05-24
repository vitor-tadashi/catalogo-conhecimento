package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.dao.ClienteDAO;

public class ClienteBusiness {

	// INSERE NA TABELA CLIENTE
	public void adicinar(ClienteBean clienteBean) throws ClassNotFoundException, SQLException {
		
			ClienteDAO clienteDao = new ClienteDAO();

			clienteDao.adicionar(clienteBean);
	
	}

	// CONSULTA NA TABELA CLIENTE
	public List<ClienteBean> listar() throws ClassNotFoundException, SQLException {

		
			ClienteDAO clienteDao = new ClienteDAO();
			return clienteDao.listar();
		
	}

	// ATUALIZA NA TABELA CLIENTE
	public boolean alterar(ClienteBean clienteBean) throws ClassNotFoundException, SQLException {

		ClienteDAO clienteDao = new ClienteDAO();
		
			ClienteBean cliente = clienteDao.obterPorId(clienteBean.getId());
			if (cliente == null) {
				return true;
			} else {
				clienteDao.alterar(clienteBean);
				return false;
			}
		
	}

	// DELETA NA TABELA CLIENTE
	public boolean remover(ClienteBean clienteBean) throws ClassNotFoundException, SQLException {

		
			ClienteDAO clienteDao = new ClienteDAO();
			ClienteBean cliente = clienteDao.obterPorId(clienteBean.getId());
			if (cliente == null) {
				return true;
			} else {
				clienteDao.remover(clienteBean);
				return false;
			}

		
	}

	// LISTA POR ID NA TABELA CLIENTE
	public ClienteBean obterPorId(int idCliente) throws ClassNotFoundException, SQLException {

		
			ClienteDAO clienteDAO = new ClienteDAO();
			return clienteDAO.obterPorId(idCliente);
		
	}

}
