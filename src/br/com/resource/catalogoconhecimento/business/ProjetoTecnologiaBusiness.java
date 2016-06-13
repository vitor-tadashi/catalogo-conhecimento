package br.com.resource.catalogoconhecimento.business;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.resource.catalogoconhecimento.bean.ProjetoBean;
import br.com.resource.catalogoconhecimento.bean.TecnologiaBean;
import br.com.resource.catalogoconhecimento.dao.ProjetoTecnologiaDAO;

@Component
public class ProjetoTecnologiaBusiness {

		ProjetoTecnologiaDAO projetoTecnologiaDAO;

		public ProjetoTecnologiaBusiness() throws ClassNotFoundException, SQLException {


			this.projetoTecnologiaDAO = new ProjetoTecnologiaDAO();

		}

		public int inserir(ProjetoBean projeto, List<TecnologiaBean>listaTecnologias) throws SQLException{
			int linhasAfetadas = 0;

			linhasAfetadas = projetoTecnologiaDAO.inserir(projeto, listaTecnologias);
			
			return linhasAfetadas;
		}

		public List<TecnologiaBean> listar(ProjetoBean projeto) throws ClassNotFoundException, SQLException{

			return projetoTecnologiaDAO.listar(projeto);

		}

		public void atualizar(ProjetoBean projeto, List<TecnologiaBean> listaTecnologia) throws SQLException{

			projetoTecnologiaDAO.atualizar(projeto, listaTecnologia);

		}
		
		public void deletar(ProjetoBean projeto) throws SQLException{
			projetoTecnologiaDAO.deletar(projeto);
		}

	}



