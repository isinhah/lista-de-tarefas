package org.dev.model.dao.impl;


import javax.persistence.EntityManager;
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
    public void insert(Task obj) {

    }

    @Override
    public void update(Task obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Task findById(Integer id) {
        return null;
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public List<Task> findByCategory(Category category) {
        return null;
    }
}
