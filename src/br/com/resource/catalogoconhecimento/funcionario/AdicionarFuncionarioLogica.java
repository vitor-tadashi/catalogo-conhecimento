package br.com.resource.catalogoconhecimento.funcionario;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AdicionarFuncionarioLogica implements Logica{

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String nomeFuncionario = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
		String nomeUsuario = request.getParameter("usuario");
		String email = request.getParameter("email");
		String[] tecnologias = request.getParameterValues("tecnologiasArray[]");
		
		CargoBean cargoBean = new CargoBean();
		
		int idCargo = 0;
		cargoBean.setIdCargo(idCargo);
		String nomeCargo = null;
		cargoBean.setNomeCargo(nomeCargo);
		
		
		//cargoBean.setNome(cargo);
		List<TecnologiaBean> tecnologiasLista = new ArrayList<>();
		TecnologiaBean tecnologia;
		for(int i =0 ; i <= tecnologias.length ; i ++){
			tecnologia =  new TecnologiaBean();
			tecnologia.setNomeTecnologia(tecnologias[i]);
			tecnologiasLista.add(tecnologia);
		}
		FuncionarioBean funcionario = new FuncionarioBean();
		funcionario.setNomeFuncionario(nomeFuncionario);
		funcionario.setEmail(email);
		funcionario.setNomeUser(nomeUsuario);
		funcionario.setTelefone(telefone);
		
		FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
		funcionarioBusiness.inserir(funcionario);
			
		return "mvc?logica=funcionario.ListarFuncionarioLogica";
	}

}
