package org.dev.model.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@EqualsAndHashCode
@Table //JPA
@Entity //JPA
public class Task {
    @Id //JPA
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    // Mapeamento da relação com a categoria
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Task(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task: " +
                "Id: " + id +
                ", Name: " + name +
                ", Description: " + description + ", Category: " + category;
    }
}
