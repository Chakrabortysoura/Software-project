package com.first.software_project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Subjects {
    @Id
    private int subject_code;
    private String subject_name;
}
