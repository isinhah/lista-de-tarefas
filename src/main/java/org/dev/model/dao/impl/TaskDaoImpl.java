package org.dev.model.dao.impl;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.dev.model.dao.TaskDAO;
import org.dev.model.entities.Category;
import org.dev.model.entities.Task;

import java.util.List;

public class TaskDaoImpl implements TaskDAO {
    private EntityManager entityManager;

    public TaskDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void insert(Task obj, Integer id) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            if (id != null) {
                Category category = entityManager.find(Category.class, id);
                if (category != null) {
                    obj.setCategory(category);
                } else {
                    System.out.println("Categoria não encontrada. A tarefa será criada sem categoria.");
                }
            }

            entityManager.persist(obj);
            transaction.commit();
            System.out.println("Tarefa adicionada! Id: " + obj.getId());
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }


    @Override
    public void update(Task obj) {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Task task = entityManager.getReference(Task.class, obj.getId());

            if (task != null) {
                task.setName(obj.getName());
                task.setDescription(obj.getDescription());
                transaction.commit();
            } else {
                throw new RuntimeException("Tarefa com id " + obj.getId() + " não foi encontrada");
            }
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void deleteById(Integer id) {
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            Task task = entityManager.find(Task.class, id);
            if (task == null) {
                throw new RuntimeException("Tarefa com id " + id + " não encontrada");
            }
            entityManager.remove(task);
            entityManager.flush(); // Sincroniza as alterações com o banco de dados
            transaction.commit(); // Realiza o commit da transação
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Task findById(Integer id) {
        EntityTransaction transaction = null;
        Task task;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            task = entityManager.find(Task.class, id);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }

        if (task == null) {
            throw new RuntimeException("Tarefa de id: " + id + " não foi encontrada.");
        }
        return task;
    }

    @Override
    public List<Task> findAll() {
        try {
            List<Task> tasks = entityManager.createQuery("SELECT t FROM Task t", Task.class).getResultList();
            return tasks;
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao listar tarefas.");
        }
    }

    @Override
    public List<Task> findByCategory(Integer categoryId) {
        Category category = entityManager.find(Category.class, categoryId);
        if (category == null) {
            throw new IllegalArgumentException("Categoria com id " + categoryId + " não encontrada");
        }

        TypedQuery<Task> query = entityManager.createQuery(
                "SELECT t FROM Task t WHERE t.category = :category", Task.class);
        query.setParameter("category", category);
        return query.getResultList();
    }
}