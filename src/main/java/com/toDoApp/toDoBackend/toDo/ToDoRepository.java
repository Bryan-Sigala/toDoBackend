package com.toDoApp.toDoBackend.toDo;

import com.toDoApp.toDoBackend.run.Location;
import com.toDoApp.toDoBackend.run.Run;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ToDoRepository {

    private List<ToDo> toDos = new ArrayList<>();

    List<ToDo> findAll(){
        return toDos;
    }

    Optional<ToDo> findById(Integer id) {
        return toDos.stream()
                .filter(run -> run.id() == id)
                .findFirst();
    }

    void update(ToDo toDo, Integer id){
        Optional<ToDo> existingToDo = findById(id);
        if(existingToDo.isPresent()){
            toDos.set(toDos.indexOf(existingToDo.get()), toDo);
        }
    }

    void delete(Integer id){
        toDos.removeIf(run -> run.id().equals(id));
    }

    void create(ToDo toDo){
        toDos.add(toDo);
    }

    @PostConstruct //Do some initialization in this class
    private void init(){
        toDos.add(new ToDo(1,
                "Clean the dishes",
                LocalDate.of(2024, 6, 3),
                false,
                null,
                ToDoPriority.LOW,
                LocalDateTime.now()));

        toDos.add(new ToDo(2,
                "Buy new shoes",
                LocalDate.of(2024, 8, 3),
                true,
                LocalDateTime.now(),
                ToDoPriority.MEDIUM,
                LocalDateTime.now()));
    }


}
