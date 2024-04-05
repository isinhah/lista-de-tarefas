package org.dev.model.dao;

import org.dev.model.dao.impl.CategoryDaoImpl;
import org.dev.model.dao.impl.TaskDaoImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoFactory {
    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("tarefas-jpa");
    }

    public static TaskDAO getTaskDAO() {
        EntityManager em = emf.createEntityManager();
        return new TaskDaoImpl(em);
    }

    public static CategoryDAO getCategoryDAO() {
        EntityManager em = emf.createEntityManager();
        return new CategoryDaoImpl(em);
    }

    public static void close() {
        emf.close();
    }
}


