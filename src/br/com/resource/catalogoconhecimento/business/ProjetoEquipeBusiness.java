package br.com.resource.catalogoconhecimento.business;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.resource.catalogoconhecimento.bean.EquipeBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.dao.ProjetoEquipeDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class ProjetoEquipeBusiness {
	
	
	@Autowired
	private ProjetoEquipeDAO projetoEquipeDAO;

	public int adicionar(ProjetoBean projeto, List<EquipeBean> equipes) throws BusinessException {
		try {
			int linhasAfetadas = 0;

			linhasAfetadas = projetoEquipeDAO.inserir(projeto, equipes);

			return linhasAfetadas;

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public List<EquipeBean> listar(ProjetoBean projeto) throws BusinessException {
		try {
			return projetoEquipeDAO.listar(projeto);

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public void atualizar(ProjetoBean projeto, List<EquipeBean> equipes) throws BusinessException {
		try {
			projetoEquipeDAO.atualizar(projeto, equipes);

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}
}
