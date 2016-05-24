package br.com.resource.catalogoconhecimento.logica.funcionario;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AlterarFuncionarioLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		int id = Integer.parseInt(request.getParameter("id"));
		String nomefuncionario = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
		String email = request.getParameter("email");
		String usuario = request.getParameter("usuario");
		String cargo = request.getParameter("cargo");
		String tecnologia = request.getParameter("tecnologia");

		CargoBean cargoBean = new CargoBean();
		cargoBean.setNome(cargo);
		

		TecnologiaBean tecnologiaBean = new TecnologiaBean();
		tecnologiaBean.setNomeTecnologia(tecnologia);

		FuncionarioBean funcionario = new FuncionarioBean();
		funcionario.setId(id);
		funcionario.setNome(nomefuncionario);
		funcionario.setEmail(email);
		funcionario.setNomeUser(usuario);
		funcionario.setTelefone(telefone);
		
		//VER ISSO COM ALGU�M...N�O ESTOU ENTENDENDO 
		/*List<TecnologiaBean> tec = funcionario.getTecnologias
		tec.add(tecnologiaBean);

		funcionario.setTecnologias(tec);*/

		FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();

		List<FuncionarioBean> listaFuncionarios = funcionarioBusiness.listar();
		listaFuncionarios .add(funcionario);

		request.setAttribute("funcionarios", listaFuncionarios );

		return "mvc?logica=funcionario.ListarFuncionarioLogica";

	}

}
