package org.dev.model.dao.impl;

import org.dev.model.dao.CategoryDAO;
import org.dev.model.entities.Category;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoryDaoImpl implements CategoryDAO {
    private EntityManager entityManager;

    public CategoryDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void insert(Category obj) {

    }

    @Override
    public void update(Category obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Category findById(Integer id) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return null;
    }
}
