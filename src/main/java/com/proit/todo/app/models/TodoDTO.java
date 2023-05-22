package com.proit.todo.app.models;

import lombok.*;

import java.util.Date;

/**
 * @author raqib91
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class TodoDTO {
    private long id;
    private String title;
    private String description;
    private Date deadline;
}
