package com.software.room_allocation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Schedule_class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int class_id;

    private String day_of_week,batch;
    private Time start_time,end_time;

    @ManyToOne
    private Subject class_subject;

    @ManyToOne
    private Faculty assigned_faculty;
}
