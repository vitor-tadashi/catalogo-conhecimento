package br.com.resource.catalogoconhecimento.logica.projeto;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.resource.catalogoconhecimento.business.ProjetoBusiness;
import br.com.resource.catalogoconhecimento.logica.Logica;

public class DeletarProjetoLogica implements Logica {

	@Override
	public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int idProjeto = Integer.parseInt(request.getParameter("idProjeto"));


		ProjetoBusiness projetoBusiness = new ProjetoBusiness();

		
		projetoBusiness.deletar(idProjeto);
		

		return "mvc?logica=projeto.ListarProjetoLogica";
	}

}
