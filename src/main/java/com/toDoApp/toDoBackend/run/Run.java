package com.toDoApp.toDoBackend.run;

import java.time.LocalDateTime;

public record Run(
        Integer Id,
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        Integer miles,
        Location location
) {
}
