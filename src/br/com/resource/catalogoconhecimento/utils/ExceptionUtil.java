package br.com.resource.catalogoconhecimento.utils;

import javax.security.auth.login.LoginException;

import br.com.resource.catalogoconhecimento.exceptions.AtributoNuloException;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.CaracteresEspeciaisException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.CpfInvalidoException;
import br.com.resource.catalogoconhecimento.exceptions.DataInvalidaException;
import br.com.resource.catalogoconhecimento.exceptions.EmailInvalidoException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.QuantidadeTagException;
import br.com.resource.catalogoconhecimento.exceptions.RegistroVinculadoException;
import br.com.resource.catalogoconhecimento.exceptions.RgInvalidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.exceptions.UserInvalidoException;

public abstract class ExceptionUtil {

	public static BusinessException handleException(Exception e){
		
		String error = "Sistema indisponivel no momento!";
		
		if(e instanceof NomeRepetidoException){
			error = "Este nome já existe na base de dados";
		}else if(e instanceof AtributoNuloException){
			error = "Por favor, digite todos os campos";
		}else if(e instanceof TamanhoCampoException){
			error = "Você excedeu o numero de caracteres";
		}else if(e instanceof ConsultaNulaException){
			error = "Sua busca não retornou nenhum resultado";
		}else if(e instanceof CpfInvalidoException){
			error = "Por favor, digite um cpf válido";
		}else if(e instanceof DataInvalidaException){
			error = "Por favor, digite uma data válido";
		}else if(e instanceof EmailInvalidoException){
			error = "Por favor, digite um email válido";
		}else if(e instanceof QuantidadeTagException){
			error = "Por favor, preencha os campos de busca";
		}else if(e instanceof RegistroVinculadoException){
			error = "Não é possível excluir este registro";
		}else if(e instanceof RgInvalidoException){
			error = "Por favor, digite um rg válido";
		}else if(e instanceof UserInvalidoException){
			error = "Nome de usuário inválido";
		}else if(e instanceof CaracteresEspeciaisException){
			error = "Por favor,não digite caracteres especiais";
		}else if(e instanceof LoginException){
			error = "Usu�rio e/ou senha incorreto(s)";
		}
		
		return new BusinessException(error);
	}
}
