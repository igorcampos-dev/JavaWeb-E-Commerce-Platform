package br.com.marketupdate.configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Market");
    private PersistenceManager() {
    }public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
