package org.dev.model.dao;

import org.dev.model.entities.Category;
import org.dev.model.entities.Task;

import java.util.List;

public interface TaskDAO {
    void insert(Task obj);
    void update(Task obj);
    void deleteById(Integer id);
    Task findById(Integer id);
    List<Task> findAll();
    List<Task> findByCategory(Category category);
}