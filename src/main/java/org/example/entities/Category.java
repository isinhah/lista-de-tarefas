package org.example.entities;

import jdk.jfr.Description;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
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
    @Column(name = "taskQuantity")
    private Integer quantity;
    @OneToMany(mappedBy = "category")
    private List<Task> tasksList;

    public int quantityOfTasks() {
        if (tasksList != null) {
            return tasksList.size();
        } else {
            return 0; // Se não houver tarefas, retorna 0
        }
    }
}
