package com.first.software_project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Faculty {
    private String name;
    @Id
    private int faculty_id;
}
