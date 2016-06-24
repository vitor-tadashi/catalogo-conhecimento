package br.com.resource.catalogoconhecimento.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.resource.catalogoconhecimento.bean.EquipeFuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.dao.EquipeFuncionarioDAO;
import br.com.resource.catalogoconhecimento.dao.FuncionarioDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class EquipeFuncionarioBusiness {
	
	@Autowired
	private EquipeFuncionarioDAO equipeFuncionarioDAO;
	
	@Autowired
	private FuncionarioDAO funcionarioDAO;
	
	@Transactional
	public void inserirPorEquipe(int equipe, FuncionarioBean funcionario) throws BusinessException {
		try {
			FuncionarioBean funcionarioClone = funcionarioDAO.obterPorEquipe(funcionario.getId(), equipe);
			if(funcionarioClone != null){
				throw new NomeRepetidoException();
			}
			EquipeFuncionarioBean equipeFuncionario = new EquipeFuncionarioBean();
			equipeFuncionario.setIdEquipe(equipe);
			equipeFuncionario.setIdFuncionario(funcionario.getId());
			equipeFuncionarioDAO.adicionar(equipeFuncionario);
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.handleException(e);
		}
	}

}
