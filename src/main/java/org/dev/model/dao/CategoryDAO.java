package org.dev.model.dao;

import org.dev.model.entities.Category;

import java.util.List;

public interface CategoryDAO {
    void insert(Category obj);
    void update(Category obj);
    void deleteById(Integer id);
    Category findById(Integer id);
    List<Category> findAll();
}
