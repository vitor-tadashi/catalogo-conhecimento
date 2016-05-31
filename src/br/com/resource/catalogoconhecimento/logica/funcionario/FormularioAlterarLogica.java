package br.com.resource.catalogoconhecimento.logica.funcionario;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.CargoBean;
import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.business.CargoBusiness;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.business.TecnologiaBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioAlterarLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String nome = request.getParameter("nomeFuncionario");
		FuncionarioBusiness funcionarioBusiness = new FuncionarioBusiness();
		TecnologiaBusiness tecnologiaBusiness = new TecnologiaBusiness();
		CargoBusiness cargoBusiness = new CargoBusiness();

		FuncionarioBean funcionarioBean = funcionarioBusiness.obterPorNome(nome);
		List<TecnologiaBean> listaTecnologia = tecnologiaBusiness.listar();
		List<CargoBean> listaCargo = cargoBusiness.listar();

		request.setAttribute("funcionario", funcionarioBean);
		request.setAttribute("tecnologias", listaTecnologia);
		request.setAttribute("cargos", listaCargo);

		return "/WEB-INF/jsp/funcionarios/formularioAlterar.jsp";
	}

}
