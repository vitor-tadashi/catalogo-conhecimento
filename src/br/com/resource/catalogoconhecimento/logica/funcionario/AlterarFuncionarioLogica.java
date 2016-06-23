package br.com.resource.catalogoconhecimento.logica.funcionario;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.business.FuncionarioNegocioBusiness;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaFuncionarioBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class AlterarFuncionarioLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
		String nomeUsuario = request.getParameter("nomeUser");
		String email = request.getParameter("email");
		int cargo = Integer.parseInt(request.getParameter("cargo"));
		String[] tecnologias = request.getParameterValues("tecnologiasArray");
		String[] negocios = request.getParameterValues("negociosArray");

		
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
		
		List<NegocioBean> listaNegocio = new ArrayList<>();
		NegocioBusiness negocioBusiness = new NegocioBusiness();
		NegocioBean negocio;
		for(int i =0; i < negocios.length; i++){
			negocio = 	negocioBusiness.obterPorNome(negocios[i]);
			listaNegocio.add(negocio);
		}
		
		FuncionarioBean funcionarioBean = new FuncionarioBean();
		funcionarioBean.setId(id);
		funcionarioBean.setNome(nome);
		funcionarioBean.setTelefone(telefone);
		funcionarioBean.setNomeUser(nomeUsuario);
		funcionarioBean.setEmail(email);
		funcionarioBean.setCargo(cargoBean);
		funcionarioBean.setListaTecnologia(listaTecnologia);
		funcionarioBean.setListaNegocio(listaNegocio);
		
		FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
		funcionarioBusiness.alterar(funcionarioBean);
		
		TecnologiaFuncionarioBusiness funcionariotecnologia = new TecnologiaFuncionarioBusiness();
		funcionariotecnologia.atualizar(funcionarioBean, listaTecnologia);
		
		FuncionarioNegocioBusiness funcionarioNegocioBusiness = new FuncionarioNegocioBusiness();
		funcionarioNegocioBusiness.atualizar(funcionarioBean, listaNegocio);
		
		return "mvc?logica=funcionario.ListarFuncionarioLogica";
	}

}
