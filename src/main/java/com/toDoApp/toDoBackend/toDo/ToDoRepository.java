package com.toDoApp.toDoBackend.toDo;

import com.toDoApp.toDoBackend.run.Location;
import com.toDoApp.toDoBackend.run.Run;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ToDoRepository {

    private List<ToDo> toDos = new ArrayList<>();

    List<ToDo> findAll(){
        return toDos;
    }

    List<ToDo> getToDos(
            Integer page,
            Integer pageSize,
            String sortBy,
            String doneFilter,
            String nameFilter,
            String priorityFilter
    ) {
            List<ToDo> doneFilterList = doneFilter(toDos, doneFilter)
                    .stream()
                    .toList();

            List<ToDo> textFilterList = textFilter(doneFilterList, nameFilter)
                    .stream()
                    .toList();

            List<ToDo> priorityFilterList = priorityFilter(textFilterList, priorityFilter)
                    .stream()
                    .toList();

        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, priorityFilterList.size());
        List<ToDo> paginatedToDos = priorityFilterList.subList(start, end);

        return paginatedToDos;
    }

    // Filters methods
    // Done Filter
     List<ToDo> doneFilter(List<ToDo> toDoList, String param){
        if(param.equals("DONE"))
            return toDoList
                    .stream()
                    .filter(ToDo::done)
                    .toList();
        else if(param.equals("UNDONE"))
            return toDoList
                    .stream()
                    .filter(toDo -> !toDo.done())
                    .toList();
        else
            return toDoList
                    .stream()
                    .toList();
    }

    // Text Filter
    List<ToDo> textFilter(List<ToDo> toDoList, String param){
        if(param.isEmpty())
            return toDoList
                    .stream()
                    .toList();
        else
            return toDoList
                    .stream()
                    .filter(toDo -> toDo.text().toLowerCase().contains(param.toLowerCase()))
                    .toList();
    }

    // Priority Filter
    List<ToDo> priorityFilter(List<ToDo> toDoList, String param){
        return switch (param) {
            case "HIGH" -> toDoList
                    .stream()
                    .filter(toDo -> toDo.priority() == ToDoPriority.HIGHT)
                    .toList();
            case "MEDIUM" -> toDoList
                    .stream()
                    .filter(toDo -> toDo.priority() == ToDoPriority.MEDIUM)
                    .toList();
            case "LOW" -> toDoList
                    .stream()
                    .filter(toDo -> toDo.priority() == ToDoPriority.LOW)
                    .toList();
            default -> toDoList
                    .stream()
                    .toList();
        };
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
