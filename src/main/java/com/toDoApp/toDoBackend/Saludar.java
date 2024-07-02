package com.toDoApp.toDoBackend;

import org.springframework.stereotype.Component;

@Component
public class Saludar {

    public String getSaludo(){
        return "Welcome to To Do Backend :)";
    }
}
