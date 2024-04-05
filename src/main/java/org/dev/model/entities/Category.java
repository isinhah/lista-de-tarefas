package org.dev.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table //JPA
@Entity //JPA
public class Category {
    @Id //JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @OneToMany(mappedBy = "category") // Mapeamento para a lista de tarefas
    private List<Task> tasks;

    public Category(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public int quantityOfTasks(Task task) {
        if (tasks != null) {
            return tasks.size();
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Id: " + id +
                ", Title: " + title;
    }
}
