package br.com.resource.catalogoconhecimento.dao;

import org.springframework.stereotype.Repository;

import br.com.resource.catalogoconhecimento.bean.EquipeFuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;


@Repository
public class EquipeFuncionarioDAO extends GenericDAOImpl<EquipeFuncionarioBean, Integer> {

	public void inserirPorEquipe(int idEquipe, FuncionarioBean funcionario) {
		try {
			EquipeFuncionarioBean equipeFuncionario = new EquipeFuncionarioBean();
			equipeFuncionario.setIdEquipe(idEquipe);
			equipeFuncionario.setIdFuncionario(funcionario.getId());
			
			
			
			} catch (Exception e) {
				
			}
		}

}
