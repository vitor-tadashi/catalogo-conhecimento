package br.com.resource.catalogoconhecimento.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.logica.Logica;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjaxServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String parametro = request.getParameter("logica");
		String nomeDaClasse = "br.com.resource.catalogoconhecimento.logica." + parametro;
		String json = "";

		try {
			Class<?> classe = Class.forName(nomeDaClasse);

			Logica logica = (Logica) classe.newInstance();
			json = logica.executar(request, response);
		} catch (BusinessException  e) {
			request.setAttribute("msgErro", e.getMessage());
		} catch(NullPointerException e){
			request.setAttribute("msgErro", "Por favor, preencha todos os campos");
		}catch(Exception e){
			request.setAttribute("msgErro", "Sistema indisponível no momento");
			e.printStackTrace();
		} finally {
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json);
		}
	}
}
