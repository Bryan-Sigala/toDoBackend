package com.toDoApp.toDoBackend.toDo;

import jakarta.annotation.Priority;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ToDo {
    private Integer id;
    private String text;
    private LocalDate dueDate;
    private Boolean done;
    private LocalDateTime doneDate;
    private ToDoPriority priority;
    private LocalDateTime creationDate;

    public ToDo(Integer id, String text, LocalDate dueDate, Boolean done, LocalDateTime doneDate, ToDoPriority priority, LocalDateTime creationDate) {
        this.id = id;
        this.text = text;
        this.dueDate = dueDate;
        this.done = done;
        this.doneDate = doneDate;
        this.priority = priority;
        this.creationDate = creationDate;
    }
}
