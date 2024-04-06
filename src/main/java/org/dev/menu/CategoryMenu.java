package org.dev.menu;

import org.dev.model.dao.CategoryDAO;
import org.dev.model.dao.DaoFactory;
import org.dev.model.entities.Category;

import java.util.List;
import java.util.Scanner;

public class CategoryMenu {
    public static void main(String[] args) {
        CategoryDAO categoryDAO = DaoFactory.createCategoryDAO();
        Scanner sc = new Scanner(System.in);

        System.out.println("==== CRIAR CATEGORIA ====");
        System.out.println("Titulo da categoria: ");
        String categoryTitle = sc.nextLine();
        categoryDAO.insert(new Category(null, categoryTitle));

        System.out.println("==== ATUALIZAR CATEGORIA ====");
        System.out.println("Insira o id da categoria existente: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Novo titulo da categoria: ");
        String updatedTitle = sc.nextLine();
        Category updatedCategory = categoryDAO.findById(id);
        if (updatedCategory != null) {
            updatedCategory.setTitle(updatedTitle);
            categoryDAO.update(updatedCategory);
            System.out.println("Categoria atualizada com sucesso!");
        }

        System.out.println("==== EXCLUIR CATEGORIA ====");
        System.out.println("Id da categoria que será excluida: ");
        int categoryId = sc.nextInt();
        Category removedCategory = categoryDAO.findById(categoryId);
        if (removedCategory != null) {
            categoryDAO.deleteById(removedCategory.getId());
            System.out.println("Categoria exluida com sucesso!");
        }

        System.out.println("==== LISTAR CATEGORIAS ====");
        List<Category> allCategories = categoryDAO.findAll();
        for (Category category : allCategories) {
            System.out.println(category);
        }

        sc.close();
    }
}