package com.summa.codefest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO {
	private static final String PERSISTENCE_UNIT_NAME = "TRWIB";
	EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

	public EntityManager getEntityManager() {
	      return factory.createEntityManager();
	    } 
}
