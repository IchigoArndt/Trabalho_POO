package com.trabalho.projetofaculdade.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "action_logs")
public class ActionLog {

    @Id
    private String id;

    private String entity;
    private String action;
    private String description;
    private LocalDateTime timestamp;
}
