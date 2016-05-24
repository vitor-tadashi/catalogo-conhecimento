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
import br.com.resource.catalogoconhecimento.business.FuncionarioTecnologiaBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AdicionarFuncionarioLogica implements Logica{

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String nomeFuncionario = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
		String nomeUsuario = request.getParameter("nomeUser");
		String email = request.getParameter("email");
		String[] tecnologias = request.getParameterValues("tecnologiasArray");
		String cargo = request.getParameter("cargo");
		
		CargoBean cargoBean = new CargoBean();
		CargoBusiness cargoBusiness = new CargoBusiness();
		
		cargoBean = cargoBusiness.obterPorNome(cargo);
				
		

		List<TecnologiaBean> listaTecnologia = new ArrayList<>();
		TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness();
		TecnologiaBean tecnologia;
		for(int i =0 ; i < tecnologias.length ; i ++){
			tecnologia =  tecnologiaBusiness.obterPorNome(tecnologias[i]);
			listaTecnologia.add(tecnologia);
		}
		
		
		FuncionarioBean funcionario = new FuncionarioBean();
		funcionario.setNome(nomeFuncionario);
		funcionario.setEmail(email);
		funcionario.setNomeUser(nomeUsuario);
		funcionario.setTelefone(telefone);
		funcionario.setCargo(cargoBean);
		funcionario.setTecnologia(listaTecnologia);
		
		FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
		int id = funcionarioBusiness.inserir(funcionario);
		funcionario.setId(id);
		
		FuncionarioTecnologiaBusiness funcionariotecnologia = new FuncionarioTecnologiaBusiness();
		funcionariotecnologia.inserir(funcionario, listaTecnologia);
		
		
		
			
		return "mvc?logica=funcionario.ListarFuncionarioLogica";
	}

}
