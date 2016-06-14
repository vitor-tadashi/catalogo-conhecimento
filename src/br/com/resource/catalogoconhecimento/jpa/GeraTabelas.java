package br.com.resource.catalogoconhecimento.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.resource.catalogoconhecimento.bean.CargoBean;

public class GeraTabelas {

	public static void main(String[] args) {
		CargoBean cargoBean = new CargoBean();
		cargoBean.setNome("Teste");
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Cargo");
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();    
		manager.persist(cargoBean);
		manager.getTransaction().commit();  

		System.out.println("ID do cargo: " + cargoBean.getId());

		manager.close();
		factory.close();
	}

}
