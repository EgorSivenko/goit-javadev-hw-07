package org.example.db.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Project {
    private final Integer clientId;
    private final LocalDate startDate;
    private final LocalDate finishDate;
}
