package com.toDoApp.toDoBackend.toDo;

import jakarta.annotation.Priority;

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
) {

    /*ToDoPriority getPriority(){
        return priority;
    }*/

}
