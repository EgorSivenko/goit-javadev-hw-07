package org.example.db.entity;

import lombok.Data;

@Data
public class ProjectWorker {
    private final Integer projectId;
    private final Integer workerId;
}
