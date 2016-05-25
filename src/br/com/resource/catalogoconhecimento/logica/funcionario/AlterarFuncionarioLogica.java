package br.com.resource.catalogoconhecimento.logica.funcionario;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaFuncionarioBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AlterarFuncionarioLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String nomeFuncionario = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
		String nomeUsuario = request.getParameter("nomeUser");
		String email = request.getParameter("email");
		int cargo = Integer.parseInt(request.getParameter("cargos"));
		String[] tecnologias = request.getParameterValues("tecnologiasArray");

		
		CargoBean cargoBean = new CargoBean();
		CargoBusiness cargoBusiness = new CargoBusiness();
		
		cargoBean = cargoBusiness.obterPorId(cargo);
				
		

		List<TecnologiaBean> listaTecnologia = new ArrayList<>();
		TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness();
		TecnologiaBean tecnologia;
		for(int i =0 ; i < tecnologias.length ; i ++){
			tecnologia =  tecnologiaBusiness.obterPorNome(tecnologias[i]);
			listaTecnologia.add(tecnologia);
		}
		
		
		FuncionarioBean funcionario = new FuncionarioBean();
		funcionario.setId(id);
		funcionario.setNome(nomeFuncionario);
		funcionario.setTelefone(telefone);
		funcionario.setNomeUser(nomeUsuario);
		funcionario.setEmail(email);
		funcionario.setCargo(cargoBean);
		funcionario.setTecnologias(listaTecnologia);
		
		FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
		funcionarioBusiness.atualizar(funcionario);
		
		TecnologiaFuncionarioBusiness funcionariotecnologia = new TecnologiaFuncionarioBusiness();
		funcionariotecnologia.atualizar(funcionario, listaTecnologia);
		
		
		
			
		return "mvc?logica=funcionario.ListarFuncionarioLogica";
	}

}
