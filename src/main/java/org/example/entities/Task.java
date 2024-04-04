package org.example.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@EqualsAndHashCode
@ToString
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
}