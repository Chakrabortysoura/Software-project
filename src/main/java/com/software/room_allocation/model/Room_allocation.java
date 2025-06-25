package com.software.room_allocation.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Getter
public class Room_allocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date, rescheduled_date;
    private String event_name;
    private Time start_time,end_time;
    private boolean eventy_type, rescheduled;

    @ManyToOne
    private Schedule_class class_details;

    @ManyToOne
    private Rooms assigned_room;
}