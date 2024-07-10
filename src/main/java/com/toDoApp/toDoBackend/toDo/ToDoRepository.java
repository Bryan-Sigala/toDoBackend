package com.toDoApp.toDoBackend.toDo;

import com.toDoApp.toDoBackend.run.Location;
import com.toDoApp.toDoBackend.run.Run;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
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
            List<ToDo> doneFilterList = doneFilter(toDos, doneFilter);

            List<ToDo> textFilterList = textFilter(doneFilterList, nameFilter);

            List<ToDo> priorityFilterList = priorityFilter(textFilterList, priorityFilter);

        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, priorityFilterList.size());
        List<ToDo> paginatedToDos = priorityFilterList.subList(start, end);

        sortBy(paginatedToDos, sortBy);

        return paginatedToDos;
    }

    // Sort Method
    void sortBy(List<ToDo> toDoList, String param) {
        Comparator<ToDo> comparator = null;

        switch (param) {
            case "priorityDes":
                comparator = Comparator.comparing(ToDo::getPriority);
                break;
            case "priorityAsc":
                comparator = Comparator.comparing(ToDo::getPriority, Comparator.reverseOrder());
                System.out.println(comparator);
                break;
            case "dueDateAsc":
                comparator = Comparator.comparing(ToDo::getDueDate);
                break;
            case "dueDateDes":
                comparator = Comparator.comparing(ToDo::getDueDate, Comparator.reverseOrder());
                break;
            default:
                // No need for a comparison
                break;
        }
        if (comparator != null) {
            // Sort list
            Collections.sort(toDoList, comparator);
        }
    }

    // Filters methods
    // Done Filter
     List<ToDo> doneFilter(List<ToDo> toDoList, String param){
        param = param.toUpperCase();
        if(param.equals("DONE"))
            return toDoList
                    .stream()
                    .filter(ToDo::getDone)
                    .collect(Collectors.toList());
        else if(param.equals("UNDONE"))
            return toDoList
                    .stream()
                    .filter(toDo -> !toDo.getDone())
                    .collect(Collectors.toList());
        else
            return toDoList
                    .stream()
                    .collect(Collectors.toList());
    }

    // Text Filter
    List<ToDo> textFilter(List<ToDo> toDoList, String param){
        if(param == null || param.isEmpty()){
            return toDoList;
        }
        return toDoList
                .stream()
                .filter(toDo -> toDo.getText().toLowerCase().contains(param.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Priority Filter
    List<ToDo> priorityFilter(List<ToDo> toDoList, String param){
        param = param.toUpperCase();
        if(ToDoPriority.isValid(param)){
            ToDoPriority priority = ToDoPriority.valueOf(param);
            return toDoList
                    .stream()
                    .filter(toDo -> toDo.getPriority() == priority)
                    .collect(Collectors.toList());
        }
        return toDoList;
    }

    Optional<ToDo> findById(Integer id) {
        return toDos.stream()
                .filter(run -> run.getId() == id)
                .findFirst();
    }

    // POST mark as done
    void markDone(Integer id){
        for(ToDo toDo : toDos)
            if(toDo.getId().equals(id) && !toDo.getDone()){
                toDo.setDone(true);
                break;
            }
    }

    // PUT mark as undone
    void markUndone(Integer id){
        for(ToDo toDo : toDos)
            if(toDo.getId().equals(id) && toDo.getDone()){
                toDo.setDone(false);
                break;
            }
    }

    // PUT request to update priority, due date or text
    void update(Integer id, String priority, String text, String dueDate){
        for(ToDo toDo : toDos)
            if(toDo.getId().equals(id)){
                if(ToDoPriority.isValid(priority))
                    toDo.setPriority(ToDoPriority.valueOf(priority));
                if(text != null && !text.isEmpty())
                    toDo.setText(text);
                if(dueDate != null && !dueDate.isEmpty()){
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dd = LocalDate.parse(dueDate, formatter);
                    if (!dd.isBefore(LocalDate.now()))
                        toDo.setDueDate(dd);
                }
            }
    }

    // DELETE request to eliminate a ToDo
    void delete(Integer id){
        toDos.removeIf(run -> run.getId().equals(id));
    }

    // POST request to create new ToDo
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
