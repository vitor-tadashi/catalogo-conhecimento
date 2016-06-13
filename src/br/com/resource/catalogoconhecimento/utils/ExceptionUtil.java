package br.com.resource.catalogoconhecimento.utils;

import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;

public abstract class ExceptionUtil {

	public static BusinessException handleException(Exception e){
		String error = "Sistema indisponivel no momento!";
		
		if(e instanceof NomeRepetidoException){
			error = "Este nome já existe na base de dados";
		}else if(e instanceof AtributoNuloException){
			error = "Por favor, digite todos os campos";
		}else if(e instanceof TamanhoCampoException){
			error = "Você excedeu o numero de caracteres";
		}
		
		return new BusinessException(error);
	}
}
