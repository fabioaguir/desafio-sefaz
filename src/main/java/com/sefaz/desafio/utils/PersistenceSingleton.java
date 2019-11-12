package com.sefaz.desafio.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceSingleton {

	private static EntityManagerFactory emf = null;
	
	static {
		if(emf == null)
			emf = Persistence.createEntityManagerFactory("desafio");
	}
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
