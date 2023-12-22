package org.example.db.entity;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
public class Worker {
    private final String name;
    private final LocalDate birthday;
    private final WorkerLevel level;
    private final Integer salary;

    @RequiredArgsConstructor
    @Getter
    public enum WorkerLevel {
        TRAINEE("Trainee"),
        JUNIOR("Junior"),
        MIDDLE("Middle"),
        SENIOR("Senior");

        private final String label;
    }
}
