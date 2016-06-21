package br.com.resource.catalogoconhecimento.logica.funcionario;

import java.util.ArrayList;
import java.util.Date;
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

public class AdicionarFuncionarioLogica implements Logica{

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String nome = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
		String nomeUsuario = request.getParameter("nomeUser");
		String email = request.getParameter("email");
		String[] tecnologias = request.getParameterValues("tecnologiasArray");
		String cargo = request.getParameter("cargo");
		String cpf = request.getParameter("cpf");
		String rg = request.getParameter("rg");
		String data = request.getParameter("dataNascimento");
		String[] negocios = request.getParameterValues("negociosArray");
		
		Date dataFormatada = new FuncionarioBusiness().formatarData(data);
		
		CargoBean cargoBean = new CargoBean();
		CargoBusiness cargoBusiness = new CargoBusiness();
		
		cargoBean = cargoBusiness.obterPorNome(cargo);
				
		List<NegocioBean> listaNegocios = new ArrayList<>();
		NegocioBusiness negocioBusiness = new NegocioBusiness();
		NegocioBean negocio;
		for(int i =0; i < negocios.length; i++){
			negocio = negocioBusiness.obterPorNome(negocios[i]);
			listaNegocios.add(negocio);
		}


		List<TecnologiaBean> listaTecnologia = new ArrayList<>();
		TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness();
		TecnologiaBean tecnologia;
		for(int i =0 ; i < tecnologias.length ; i ++){
			tecnologia =  tecnologiaBusiness.obterPorNome(tecnologias[i]);
			listaTecnologia.add(tecnologia);
		}
		
		
		FuncionarioBean funcionarioBean = new FuncionarioBean();
		funcionarioBean.setNome(nome);
		funcionarioBean.setEmail(email);
		funcionarioBean.setNomeUser(nomeUsuario);
		funcionarioBean.setTelefone(telefone);
		funcionarioBean.setCargo(cargoBean);
		funcionarioBean.setCpf(cpf);
		funcionarioBean.setRg(rg);
		funcionarioBean.setDataNascimento(dataFormatada);
		
		funcionarioBean.setListaTecnologia(listaTecnologia);
		funcionarioBean.setListaNegocio(listaNegocios);
		
		FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
		int id = funcionarioBusiness.adicionar(funcionarioBean);
		funcionarioBean.setId(id);
		
		TecnologiaFuncionarioBusiness funcionariotecnologia = new TecnologiaFuncionarioBusiness();
		funcionariotecnologia.adicionar(funcionarioBean, listaTecnologia);
		
		FuncionarioNegocioBusiness funcionarioNegocio = new FuncionarioNegocioBusiness();
		funcionarioNegocio.inserir(funcionarioBean, listaNegocios);
		
		
			
		return "mvc?logica=funcionario.ListarFuncionarioLogica";
	}

}
