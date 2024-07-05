package com.toDoApp.toDoBackend.toDo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    //@RequestMapping asks the path and the request method, it'll be a GET

    private final ToDoRepository toDoRepository;

    public ToDoController(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    /*// GET Request to get the ToDo list
    @GetMapping("")
    List<ToDo> findAll(){
        return toDoRepository.findAll();
    }*/

    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping("")
    List<ToDo> getToDos(
            @RequestParam Integer page,
            @RequestParam Integer pageSize,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String doneFilter,
            @RequestParam(required = false) String nameFilter,
            @RequestParam(required = false) String priorityFilter
    ){
        return toDoRepository.getToDos(page, pageSize, sortBy, doneFilter, nameFilter, priorityFilter);
    }

    // GET Request to get a ToDo by ID
    @GetMapping("/{id}")
    ToDo findById(@PathVariable Integer id){
        Optional<ToDo> toDo = toDoRepository.findById(id);
        if(toDo.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return  toDo.get();
    }

    // POST request to add a new ToDo to the list
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@RequestBody ToDo toDo){
        toDoRepository.create(toDo);
    }

    // POST Request to mark as DONE
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/{id}/done")
    void markDone(@PathVariable Integer id){
        toDoRepository.markDone(id);
    }

    // PUT Request to make a ToDO update
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@RequestBody ToDo toDo, @PathVariable Integer id){
        toDoRepository.update(toDo, id);
    }

    // DELETE Request to made a ToDo eliminated
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete (@PathVariable Integer id){
        toDoRepository.delete(id);
    }

}
