package org.dev.model.dao.impl;

import org.dev.model.dao.CategoryDAO;
import org.dev.model.entities.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class CategoryDaoImpl implements CategoryDAO {
    private EntityManager em;

    public CategoryDaoImpl(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public void insert(Category obj) {
        EntityTransaction transaction = null;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(obj);
            transaction.commit();
            System.out.println("Categoria adicionada! Id: " + obj.getId());
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void update(Category obj) {
        EntityTransaction transaction = null;

        try {
            transaction = em.getTransaction();
            transaction.begin();

            Category category = em.getReference(Category.class, obj.getId());

            if (category != null) {
                category.setTitle(obj.getTitle());
                transaction.commit();
            } else {
                throw new RuntimeException("Categoria com ID " + obj.getId() + " não encontrada");
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
        Category category;
        try {
            transaction = em.getTransaction();
            transaction.begin();
            category = em.find(Category.class, id);
            em.remove(category);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }

        if (category == null) {
            throw new RuntimeException("Categoria com ID " + id + " não encontrada");
        }
    }

    @Override
    public Category findById(Integer id) {
        EntityTransaction transaction = null;
        Category category;

        try {
            transaction = em.getTransaction();
            transaction.begin();
            category = em.find(Category.class, id);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }

        if (category == null) {
            throw new RuntimeException("Categoria com ID " + id + " não encontrada");
        }
        return category;
    }

    public List<Category> findAll() {
        try {
            List<Category> categories = em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
            return categories;
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao listar categorias: " + e.getMessage());
        }
    }
}
