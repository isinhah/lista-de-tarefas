package org.example;

import org.example.entities.Category;
import org.example.entities.Task;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tarefas-jpa");
        EntityManager em = emf.createEntityManager();

        System.out.println("==== CRIAÇÃO DE CATEGORIAS ====");
        em.getTransaction().begin();
        Category category1 = new Category(null, "Romance");
        Category category2 = new Category(null, "Science Fiction");
        em.persist(category1);
        em.persist(category2);
        em.getTransaction().commit();
        System.out.println("Categoria salva: " + category1);
        System.out.println("Categoria salva: " + category2);

        System.out.println("==== CRIAÇÃO DE TAREFAS ====");
        Category categoryToTask = em.find(Category.class, 2); //Buscar categoria
        em.getTransaction().begin();
        Task task1 = new Task(null, "Estudar ORM", "Arquitetura", categoryToTask);
        em.persist(task1);
        em.getTransaction().commit();
        System.out.println("Tarefa salva: " + task1);

        System.out.println("==== ATUALIZANDO CATEGORIAS ====");
        em.getTransaction().begin();
        Category updatedCategory = em.find(Category.class, 3);
        updatedCategory.setTitle("Programming");
        em.merge(updatedCategory);
        em.getTransaction().commit();
        System.out.println("Categoria atualizada: " + updatedCategory);

        System.out.println("==== ATUALIZANDO TAREFAS ====");
        em.getTransaction().begin();
        Task updatedTask = em.find(Task.class, 3);
        updatedTask.setName("Estudar JDBC");
        updatedTask.setDescription("Banco de dados");
        em.merge(updatedTask);
        em.getTransaction().commit();
        System.out.println("Tarefa atualizada: " + updatedTask);

        System.out.println("==== EXCLUIR CATEGORIA / TAREFA ====");
        em.getTransaction().begin();
        Category removeCategory = em.find(Category.class, 3);
        em.remove(removeCategory);
        em.getTransaction().commit();
        System.out.println("Excluido: " + removeCategory);

        System.out.println("==== LISTAR TODAS AS TAREFAS E CATEGORIAS ====");
        // Consulta para buscar todas as tarefas
        List<Task> tasks = em.createQuery("SELECT t FROM Task t", Task.class).getResultList();

        // Consulta para buscar todas as categorias
        List<Category> categories = em.createQuery("SELECT c FROM Category c", Category.class).getResultList();

        System.out.println("\nTarefas:");
        for (Task task : tasks) {
            System.out.println(task);
        }

        System.out.println("\nCategorias:");
        for (Category category : categories) {
            System.out.println(category);
        }
    }
}