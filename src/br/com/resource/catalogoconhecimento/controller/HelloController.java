package br.com.resource.catalogoconhecimento.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.resource.catalogoconhecimento.business.ProjetoBusiness;

@Controller
public class HelloController {

	@Autowired
	private ProjetoBusiness projetoBusiness;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		
		System.out.println(projetoBusiness.hashCode());
		
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";

	}

}