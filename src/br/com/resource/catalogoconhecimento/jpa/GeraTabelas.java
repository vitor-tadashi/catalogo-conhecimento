package br.com.resource.catalogoconhecimento.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GeraTabelas {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.
		          createEntityManagerFactory("Cargo");
		    factory.close();

	}

}
