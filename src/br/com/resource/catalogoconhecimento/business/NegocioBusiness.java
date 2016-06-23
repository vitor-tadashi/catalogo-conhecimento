package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.resource.catalogoconhecimento.bean.NegocioBean;
import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.dao.NegocioDAO;
import br.com.resource.catalogoconhecimento.exceptions.BusinessException;
import br.com.resource.catalogoconhecimento.exceptions.CaracteresEspeciaisException;
import br.com.resource.catalogoconhecimento.exceptions.ConsultaNulaException;
import br.com.resource.catalogoconhecimento.exceptions.NomeRepetidoException;
import br.com.resource.catalogoconhecimento.exceptions.TamanhoCampoException;
import br.com.resource.catalogoconhecimento.utils.ExceptionUtil;

@Component
public class NegocioBusiness {
	@Autowired
	private NegocioDAO negocioDao;
	
	@Transactional
	public void adicionar(NegocioBean negocioBean) throws BusinessException {
		try { 
			NegocioBean negocioClone = this.obterPorNome(negocioBean.getAreaAtuacao());

			if (!validarAreaAtuacao(negocioBean.getAreaAtuacao())) {
				throw new CaracteresEspeciaisException("Por Favor, digite uma area de ataucao valida!");
			} else if (negocioBean.getAreaAtuacao().length() > 80) {
				throw new TamanhoCampoException("Numero limite de caracteres excedido.");
			}else if (negocioClone != null) {
				throw new NomeRepetidoException("Este nome ja exite na base de dados");	
			}else{
				negocioDao.adicionar(negocioBean);
			}

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public List<NegocioBean> listar() throws BusinessException {
		try { 
			
			List<NegocioBean> listaNegocio = negocioDao.listar();

			if (listaNegocio.isEmpty()) {
				throw new ConsultaNulaException("No ha negocios cadastrados");
			} else {
				return listaNegocio;
			}

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void alterar(NegocioBean negocioBean) throws BusinessException {
		try { 
			
			NegocioBean negocioClone = this.obterPorNome(negocioBean.getAreaAtuacao());

			if (!validarAreaAtuacao(negocioBean.getAreaAtuacao())) {
				throw new CaracteresEspeciaisException("Por favor, digite uma area de atuacao valida!");
			} else if (negocioBean.getAreaAtuacao().length() > 80) {
				throw new TamanhoCampoException("Numero limite de caracteres excedido.");
			} else if (negocioClone != null && negocioClone.getId() != negocioBean.getId()) {
				throw new NomeRepetidoException("Este nome ja exite na base de dados");
			}  else {
				negocioDao.alterar(negocioBean);
			}

		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void remover(NegocioBean negocioBean) throws BusinessException {
		try {

			negocioDao.remover(negocioBean);
			/*if (negocioDao.verificarPorProjeto(negocioBean)) {
				negocioDao.remover(negocioBean);
			} else {
				throw new RegistroVinculadoException("Registro nao pode ser removido pois possui vinculos");
			}
			 */
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public NegocioBean obterPorId(int id) throws BusinessException {
		try {

			return negocioDao.obterPorId(id);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public NegocioBean obterPorNome(String areaAtuacao) throws BusinessException {
		try {

			return negocioDao.obterPorNome(areaAtuacao);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public NegocioBean obterNomeDesativado(NegocioBean negocioBean) throws ClassNotFoundException, SQLException {

		return negocioDao.obterNomeDesativado(negocioBean);
	}

	@Transactional
	public List<NegocioBean> listarPorFuncionario(int id) throws BusinessException {
		try {
			return negocioDao.listarPorFuncionario(id);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}

	@Transactional
	public void reativar(NegocioBean negocioBean) throws ClassNotFoundException, SQLException {

		negocioDao.reativar(negocioBean);
	}

	@Transactional
	public boolean validarAreaAtuacao(String areaAtuacao) {
		return (areaAtuacao.matches("[A-Za-zÁ-ú0-9'\\s]{2,50}"));
	}

	@Transactional
	public List<NegocioBean> obterPorProjeto(ProjetoBean projetoBean) throws BusinessException {
		try {
			return new NegocioDAO().obterPorProjeto(projetoBean);
		} catch (Exception e) {
			throw ExceptionUtil.handleException(e);
		}
	}
}
