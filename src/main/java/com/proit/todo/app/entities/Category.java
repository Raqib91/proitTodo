package com.proit.todo.app.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.proit.todo.app.utils.Constants;
import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author raqib91
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title = Constants.DEFAULT_CATEGORY_TITLE;

    @OneToMany(
            mappedBy = "category",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<Todo> todoList;
}
