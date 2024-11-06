package com.first.software_project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Scheduled_class {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int class_id;

    private String batch;
    private String day;
    private int start,end;
    @ManyToOne
    private Subjects topic;
    @ManyToOne
    private Faculty teacher;
    
}
