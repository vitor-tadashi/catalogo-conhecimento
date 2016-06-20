package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.resource.catalogoconhecimento.bean.FuncionarioBean;
import br.com.resource.catalogoconhecimento.dao.FuncionarioDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.CpfInvalidoException;
import br.com.resource.catalogoconhecimento.exceptions.DataInvalidaException;
import br.com.resource.catalogoconhecimento.exceptions.EmailInvalidoException;
import br.com.resource.catalogoconhecimento.exceptions.RgInvalidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.exceptions.UserInvalidoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class FuncionarioBusiness {

	private FuncionarioDAO funcionarioDAO;

	public FuncionarioBusiness() {

		funcionarioDAO = new FuncionarioDAO();
	}

	/**
	 * Adiciona um novo funionário na base
	 * 
	 * @param funcionarioBean
	 * @return id, criado no bd, do novo funcionário adicionado
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public int adicionar(FuncionarioBean funcionarioBean)
			throws BusinessException {
		try{
			
			int id = 0;
			
			FuncionarioBean funcionarioCloneCpf = funcionarioDAO.obterPorCpf(funcionarioBean.getCpf());
			FuncionarioBean funcionarioCloneMail = funcionarioDAO.obterPorEmail(funcionarioBean.getEmail());
			FuncionarioBean funcionarioCloneUser = funcionarioDAO.obterPorUser(funcionarioBean.getNomeUser());
			FuncionarioBean funcionarioCloneRg = funcionarioDAO.obterPorRg(funcionarioBean.getRg());
			
			if (funcionarioBean.getNome().trim().equals("")) {
				throw new NullPointerException("Preencha o campo de nome corretamante");
			} else if (!validarNome(funcionarioBean.getNome().trim())) {
				throw new TamanhoCampoException(
						"Número limite de caracteres excedido(máx.150) e/ou caracteres inválidos inseridos");
			} else if (funcionarioBean.getTelefone().trim().equals("")) {
				throw new NullPointerException("Preencha o campo de telefone corretamante");
			} else if (!validarNumero(funcionarioBean.getTelefone().trim())) {
				throw new TamanhoCampoException(
						"Número limite de caracteres excedido(máx.11) e/ou caracteres inválidos inseridos");
			} else if (funcionarioBean.getEmail().trim().equals("")) {
				throw new NullPointerException("Preencha o campo de e-mail corretamante");
			} else if (!validarEmail(funcionarioBean.getEmail())) {
				throw new EmailInvalidoException("Digite um email válido");
			} else if (funcionarioCloneMail != null) {
				throw new EmailInvalidoException("O e-mail: " + funcionarioBean.getEmail() + " já foi cadastrado na base");
			} else if (funcionarioBean.getCpf().trim().equals("")) {
				throw new NullPointerException("Preencha o campo de CPF corretamante");
			} else if (!validarCPF(funcionarioBean.getCpf())) {
				throw new CpfInvalidoException("Digite um CPF válido");
			} else if (funcionarioCloneCpf != null) {
				throw new CpfInvalidoException("O CPF " + funcionarioBean.getCpf() + " já foi cadastrado na base");
			} else if (funcionarioBean.getNomeUser().trim().equals("")) {
				throw new NullPointerException("Preencha o campo de nome de usuário corretamante");
			} else if (!validarUser(funcionarioBean.getNomeUser())) {
				throw new UserInvalidoException("Digite um nome de usuario valido");
			} else if (funcionarioCloneUser != null) {
				throw new UserInvalidoException(
						"O nome de usuário " + funcionarioBean.getNomeUser() + " já foi cadastrado na base");
			} else if (funcionarioBean.getRg().trim().equals("")) {
				throw new NullPointerException("Preencha o campo de RG corretamante");
			} else if (!validarRG(funcionarioBean.getRg())) {
				throw new RgInvalidoException("Digite um RG válido");
			} else if (funcionarioCloneRg != null) {
				throw new RgInvalidoException("O RG " + funcionarioBean.getRg() + " já foi cadastrado na base");
			} else {
				id = funcionarioDAO.adicionar(funcionarioBean);
			}
			
			return id;
		}catch(Exception e){
			throw ExceptionUtil.handleException(e);
		}

		

	}

	/**
	 * Lista todos os funcionários ativos
	 * 
	 * @return Lista de funcionários
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ConsultaNulaException
	 */
	public List<FuncionarioBean> listar()
			throws BusinessException {
		try {

			List<FuncionarioBean> listaFuncionario = funcionarioDAO.listar();

			if (listaFuncionario.isEmpty()) {
				throw new ConsultaNulaException("Não existem funcionários cadastrados");
			} else {
				return listaFuncionario;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	/**
	 * Lista todos os funcionários que possuem as tecnologias especificadas
	 * 
	 * @param nomeTecnologias
	 * @return List<FuncionarioBean>
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ConsultaNulaException
	 */
	public List<FuncionarioBean> listarPorTecnologias(String nomeTecnologias) throws BusinessException {
		try {
			List<FuncionarioBean> listaFuncionario = funcionarioDAO.listarPorTecnologias(nomeTecnologias);

			if (listaFuncionario == null) {
				throw new ConsultaNulaException("Não há funcionários cadastrados");
			} else {
				return listaFuncionario;
			}
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	/**
	 * Obtem todas informações de um funcinário por id
	 * 
	 * @param idFuncionario
	 * @return informações de um funcinário
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public FuncionarioBean obterPorId(int idFuncionario)
			throws BusinessException {
		try{
			return funcionarioDAO.obterPorId(idFuncionario);	
		}catch(Exception e){
			throw ExceptionUtil.handleException(e);
		}
	}

	/**
	 * Atualiza informações de um funcionário
	 * 
	 * @param funcionario
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws TamanhoCampoException
	 * @throws EmailInvalidoException
	 */
	public void atualizar(FuncionarioBean funcionarioBean) throws BusinessException {
		try{
			if (funcionarioBean.getNome().trim().equals("")) {
				throw new NullPointerException("Preencha o campo de nome corretamante");
			} else if (!validarNome(funcionarioBean.getNome().trim())) {
				throw new TamanhoCampoException(
						"Número limite de caracteres excedido(máx.150) e/ou caracteres inválidos inseridos");
			} else if (funcionarioBean.getTelefone().trim().equals("")) {
				throw new NullPointerException("Preencha o campo de telefone corretamante");
			} else if (!validarNumero(funcionarioBean.getTelefone().trim())) {
				throw new TamanhoCampoException(
						"Número limite de caracteres excedido(máx.11) e/ou caracteres inválidos inseridos");
			} else {
				funcionarioDAO.alterar(funcionarioBean);
			}
			
		}catch(Exception e){
			throw ExceptionUtil.handleException(e);
		}
	}

	/**
	 * Remove logicamente um funcionário
	 * 
	 * @param id
	 * @return boolean
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public void deletar(int id) throws BusinessException {
		
		try{
			FuncionarioBean funcionarioExistente = funcionarioDAO.obterPorId(id);
			
			if (funcionarioExistente != null) {
				funcionarioDAO.remover(id);
			} else {
				throw new BusinessException("Esse usuário não pode ser removido");
			}
			
		}catch(Exception e){
		 	throw ExceptionUtil.handleException(e);			
		}

	}

	/**
	 * Obtem todas informações de um funcionário pelo nome
	 * 
	 * @param nome
	 * @return todas informações de um funcionário
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public FuncionarioBean obterPorNome(String nome) throws ClassNotFoundException, SQLException, BusinessException {

		return funcionarioDAO.obterPorNome(nome);
	}

	/**
	 * Obtem informações específicas de um funcionário pelo idEquipe
	 * 
	 * @param id
	 * @return informações específicas de um funcionário
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws ConsultaNulaException
	 */
	public List<FuncionarioBean> listarPorEquipe(int id) throws BusinessException {

		try {
			List<FuncionarioBean> listaFuncionario = funcionarioDAO.listarPorEquipe(id);

			return listaFuncionario;
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	public List<FuncionarioBean> listarPorNegocio(String nomeNegocio) throws BusinessException {
		try {
			return funcionarioDAO.listarPorNegocio(nomeNegocio);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	private boolean validarNome(String nome) {
		return (nome.matches("[A-Za-zÀ-ú? ]+") && nome.length() <= 150);
	}

	public boolean validarNumero(String numero) {
		if (numero.equals("00000000000") || numero.equals("11111111111") || numero.equals("22222222222")
				|| numero.equals("33333333333") || numero.equals("44444444444") || numero.equals("55555555555")
				|| numero.equals("66666666666") || numero.equals("77777777777") || numero.equals("88888888888")
				|| numero.equals("99999999999") || (numero.length() > 11)) {
			return (false);
		} else {
			return (numero.matches("[\\d]+") && numero.length() <= 11);

		}

	}

	public boolean validarRG(String rg) {
		if (rg.equals("00000000000") || rg.equals("11111111111") || rg.equals("22222222222") || rg.equals("33333333333")
				|| rg.equals("44444444444") || rg.equals("55555555555") || rg.equals("66666666666")
				|| rg.equals("77777777777") || rg.equals("88888888888") || rg.equals("99999999999")
				|| (rg.length() > 11)) {
			return (false);
		} else {
			return (rg.matches("[\\d]+") && rg.length() <= 11);

		}

	}

	public boolean validarEmail(String email) {
		return (email.matches("[\\w+@\\w+.\\w+.?\\w]+") && email.length() <= 100);
	}

	public boolean validarUser(String user) {
		return (user.matches("[A-Za-z\\d]+") && user.length() <= 50);
	}

	public static boolean validarCPF(String CPF) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo
		// (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere
											// numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos
			// informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public static String imprimeCPF(String CPF) {
		return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." + CPF.substring(6, 9) + "-"
				+ CPF.substring(9, 11));
	}

	public List<FuncionarioBean> listarPorNome(String nome)
			throws ClassNotFoundException, SQLException, BusinessException {
		return funcionarioDAO.listarPorNome(nome);
	}

	public Date formatarData(String data) throws BusinessException {
		try{
			Date dataAtual = new Date(System.currentTimeMillis());
			
			DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			Date dataFormatada = formatador.parse(data);
			
			if (dataFormatada.after(dataAtual)) {
				throw new DataInvalidaException("Data inserida inválida!");
			}
			
			return dataFormatada;	
		}catch(Exception e){
			throw ExceptionUtil.handleException(e);			
		}
	}

}
