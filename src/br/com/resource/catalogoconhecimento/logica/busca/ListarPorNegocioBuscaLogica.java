package br.com.resource.catalogoconhecimento.logica.busca;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.business.FuncionarioBusiness;
import br.com.resource.catalogoconhecimento.business.NegocioBusiness;
import br.com.resource.catalogoconhecimento.business.ProjetoBusiness;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class ListarPorNegocioBuscaLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String[] array = request.getParameter("filtro").split(",");

		String nomeNegocio = "";
		NegocioBusiness negocioBusiness = new NegocioBusiness();

		for (int i = 0; i < array.length; i++) {
			if (negocioBusiness.obterPorNome(array[i]) != null) {
				if (nomeNegocio.isEmpty()) {
					nomeNegocio += "'" + array[i] + "'";
				} else {
					nomeNegocio += ",'" + array[i] + "'";
				}
			}
		}

		if (nomeNegocio.isEmpty()) {
			throw new BusinessException("Pesquisa inválida! Este nome de negócio não existe");
		}

		List<ProjetoBean> projetos = new ProjetoBusiness().obterPorNegocio(nomeNegocio);

		List<FuncionarioBean> listaFuncionario = new FuncionarioBusiness().listarPorNegocio(nomeNegocio);

		request.setAttribute("projetos", projetos);
		request.setAttribute("funcionarios", listaFuncionario);

		return "WEB-INF/jsp/busca/listarBuscaNegocio.jsp";

	}
}
