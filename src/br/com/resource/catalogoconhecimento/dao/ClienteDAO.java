package br.com.resource.catalogoconhecimento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import br.com.resource.catalogoconhecimento.bean.ClienteBean;
import br.com.resource.catalogoconhecimento.factory.ConnectionFactory;

@Repository
public class ClienteDAO extends GenericDAOImpl<ClienteBean, Integer> {

	/**
	 * -JPQL- Retorna uma lista com todos os Clientes ATIVOS da tabela de
	 * Clientes
	 * 
	 * @param no
	 *            parameters
	 * @return listaCliente (retorna a lista de clientes)
	 */
	public List<ClienteBean> listar() {
		TypedQuery<ClienteBean> query = entityManager.createQuery("SELECT c FROM ClienteBean AS c WHERE c.ativo = 'S'",
				ClienteBean.class);
		List<ClienteBean> listaCliente = query.getResultList();
		return listaCliente;
	}

	/**
	 * -JPQL- Retorna uma lista de Clientes ATIVOS (buscado através do ID
	 * passado como parâmetro)
	 * 
	 * @param idCliente
	 * @return List<ClienteBean>
	 */
	@Override
	public ClienteBean obterPorId(Integer idCliente) {
		TypedQuery<ClienteBean> query = entityManager
				.createQuery("SELECT c FROM ClienteBean AS c WHERE c.id = :id AND c.ativo = 'S'", ClienteBean.class);
		return query.setParameter("id", idCliente).getSingleResult();
	}

	/**
	 * -JPQL- Retorna uma lista de Clientes ATIVOS (buscado através do NOME
	 * passado como parâmetro)
	 * 
	 * @param nomeCliente
	 * @return List<ClienteBean>
	 */
	public ClienteBean obterPorNome(String nomeCliente) throws ClassNotFoundException, SQLException {
		TypedQuery<ClienteBean> query = entityManager.createQuery(
				"SELECT c FROM ClienteBean AS c WHERE c.nome = :nome AND c.ativo = 'S'", ClienteBean.class);
		query.setParameter("nome", nomeCliente);
		List<ClienteBean> clientes = query.getResultList();

		if (clientes.isEmpty()) {
			return null;
		} else {
			return clientes.get(0);
		}
	}

	/**
	 * Verifica se já existe um cliente com o CNPJ informado cadastrado no banco
	 * de dados
	 * 
	 * @param cnpj
	 * @return TRUE = CPNJ já está cadastrado / FALSE = CNPJ não está cadastrado
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean verificarPorCnpj(String cnpj) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionFactory.createConnection();
		String sql = "SELECT * FROM Cliente WHERE cnpj = ? AND ativo = 'S'";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cnpj);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return true;
		} else
			return false;
	}

	@Override
	public ClienteBean adicionar(ClienteBean entity) {
		try {
			entityManager.persist(entity);
			entityManager.flush();
			entityManager.refresh(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
}
