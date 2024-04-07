package org.dev.menu;

import org.dev.model.dao.DaoFactory;
import org.dev.model.dao.TaskDAO;
import org.dev.model.entities.Task;

import java.util.List;
import java.util.Scanner;

public class TaskMenu {
    public static void main(String[] args) {
        TaskDAO taskDAO = DaoFactory.createTaskDAO();
        Scanner sc = new Scanner(System.in);

        System.out.println("==== CRIAR TAREFA ====");
        System.out.println("Nome da tarefa: ");
        String name = sc.nextLine();
        System.out.println("Descrição da tarefa: ");
        String description = sc.nextLine();
        System.out.println("Insira o id da categoria que sua tarefa se encaixa (ou deixe em branco se não houver categoria): ");
        Integer idCategory = null;
        String input = sc.nextLine();
        if (!input.isEmpty()) {
            idCategory = Integer.valueOf(input);
        }
        Task task = new Task(null, name, description);
        taskDAO.insert(task, idCategory);

        System.out.println("==== EXCLUIR TAREFA POR ID ====");
        System.out.println("Id da tarefa que será excluída: ");
        int taskId = sc.nextInt();
        taskDAO.deleteById(taskId);
        System.out.println("Tarefa excluída com sucesso");

        System.out.println("==== ATUALIZAR TAREFA POR ID ====");
        System.out.println("Insira o id da tarefa existente: ");
        int idTask = sc.nextInt();
        sc.nextLine();
        System.out.println("Insira o novo nome da tarefa: ");
        String nameTask = sc.nextLine();
        System.out.println("Insira a nova descrição da tarefa: ");
        String descriptionTask = sc.nextLine();
        Task updatedTask = taskDAO.findById(idTask);
        if (updatedTask != null) {
            updatedTask.setName(nameTask);
            updatedTask.setDescription(descriptionTask);
            taskDAO.update(updatedTask);
            System.out.println("Tarefa atualizada com sucesso!");
        }

        System.out.println("==== LISTAR TAREFAS POR CATEGORIA ====");
        System.out.println("Insira o id da categoria: ");
        int id = sc.nextInt();
        List<Task> tasksByCategory = taskDAO.findByCategory(id);
        if (tasksByCategory.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada para a categoria com o ID fornecido.");
        } else {
            for (Task task1 : tasksByCategory) {
                System.out.println(task1);
            }
        }

        System.out.println("==== LISTAR TAREFAS ====");
        List<Task> allTasks = taskDAO.findAll();
        for (Task task2 : allTasks) {
            System.out.println(task2);
        }

        sc.close();
    }
}
