package com.toDoApp.toDoBackend.toDo;

import java.util.Arrays;

public enum ToDoPriority {
    HIGH, MEDIUM, LOW;

    static boolean isValid(String priority){
        return Arrays
                .stream(ToDoPriority.values())
                .map(Enum::name)
                .anyMatch(toDopriority -> toDopriority.equals(priority));
    }
}
