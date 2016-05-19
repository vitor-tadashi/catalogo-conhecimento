package br.com.resource.catalogoconhecimento.funcionario;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.logica.Logica;

public class FormularioInsereFuncionarioLogica implements Logica{


		public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
			return "/WEB-INF/jsp/funcionarios/formularioCriar.jsp";
		}
	}


