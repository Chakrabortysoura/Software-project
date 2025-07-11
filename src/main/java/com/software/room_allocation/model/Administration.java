package com.software.room_allocation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Administration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employee_id;

    private String employee_name, designation;

    @OneToOne
    private User userdetails;

    public Administration(String name,String designation, User details){
        this.employee_name=name;
        this.designation=designation;
        this.userdetails=details;
    }
}
