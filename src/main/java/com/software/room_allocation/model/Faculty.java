package com.software.room_allocation.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int faculty_id;

    private String name, department;
    private boolean hod;

    @OneToOne
    private User userdetails;

    public Faculty(String name, String department, boolean hod, User userdetails) {
        this.name = name;
        this.department = department;
        this.hod = hod;
        this.userdetails = userdetails;
    }
}
