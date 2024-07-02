package com.toDoApp.toDoBackend.toDo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ToDo(
        Integer id,
       String text,
       LocalDate dueDate,
       Boolean done,
       LocalDateTime doneDate,
       ToDoPriority priority,
       LocalDateTime creationDate
) {}
