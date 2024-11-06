package com.first.software_project.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Room_allocation{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int serial_no;
    private LocalDate allocationDate;
    private int ending_time,starting_time,room_no;
    @ManyToOne
    private Scheduled_class assigned_class;
}
