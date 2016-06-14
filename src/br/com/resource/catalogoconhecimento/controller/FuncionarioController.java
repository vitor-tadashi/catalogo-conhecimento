package br.com.resource.catalogoconhecimento.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;

@Controller
public class FuncionarioController {

	@Autowired
	FuncionarioBusiness funcionarioBusiness;

	@Autowired
	TecnologiaBusiness tecnologiaBusiness;

	@Autowired
	CargoBusiness cargoBusiness;

	@Autowired
	NegocioBusiness negocioBusiness;
	
	@Autowired
	TecnologiaFuncionarioBusiness funcionariotecnologia;
	
	@Autowired
	FuncionarioNegocioBusiness funcionarioNegocio;

	@RequestMapping(value = "formularioAdicionarFuncionario", method = RequestMethod.GET)
	public String formularioAdicionar(Model model) throws BusinessException {
		List<TecnologiaBean> listaTecnologia = tecnologiaBusiness.listar();
		List<CargoBean> listaCargo = cargoBusiness.listar();
		List<NegocioBean> listaNegocio = negocioBusiness.listar();

		model.addAttribute("tecnologias", listaTecnologia);
		model.addAttribute("cargos", listaCargo);
		model.addAttribute("negocios", listaNegocio);
		return "funcionarios/formularioAdicionar";
	}

	@RequestMapping(value = "listarFuncionario", method = RequestMethod.GET)
	public String listar(Model model) throws BusinessException {

		model.addAttribute("funcionarios", funcionarioBusiness.listar());

		return "funcionarios/listarFuncionarios";
	}
	
	

	@RequestMapping(value = "adicionarFuncionario", method = RequestMethod.POST)
	public String adicionarFuncionario(FuncionarioBean funcionarioBean, @RequestParam( value = "tecnologiasArray[]") String[] tecnologias,
			@RequestParam(value = "negociosArray[]") String[] negocios, @RequestParam(value = "dataNascimento") String data, @RequestParam(value = "cargo") String cargo ) throws BusinessException {
		
		Date dataFormatada = funcionarioBusiness.formatarData(data);
		CargoBean cargoBean = cargoBusiness.obterPorNome(cargo);
		
		TecnologiaBean tecnologiaBean;
		List<TecnologiaBean> listaTecnologias = new ArrayList<>();
		for(int i = 0; i < tecnologias.length; i++){
			tecnologiaBean = tecnologiaBusiness.obterPorNome(tecnologias[i]);
			listaTecnologias.add(tecnologiaBean);
		}
		
		List<NegocioBean> listaNegocios = new ArrayList<>();
		NegocioBean negocioBean;
		for(int i =0; i < negocios.length; i++){
			negocioBean = negocioBusiness.obterPorNome(negocios[i]);
			listaNegocios.add(negocioBean);
		}
		funcionarioBean.setCargo(cargoBean);
		funcionarioBean.setDataNascimento(dataFormatada);
		funcionarioBean.setListaNegocio(listaNegocios);
		funcionarioBean.setListaTecnologia(listaTecnologias);
		
		funcionarioNegocio.adicionar(funcionarioBean, listaNegocios);
		funcionariotecnologia.inserir(funcionarioBean, listaTecnologias);
		funcionarioBusiness.adicionar(funcionarioBean);
		
		return "redirect:listarFuncionarios";

	}

}
