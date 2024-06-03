package com.first.software_project;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Room_allocation{
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private int serial_no;
    private LocalDate today_date;
    private int ending_time,starting_time,room_no;
    @ManyToOne
    private Scheduled_class assigned_class;
    // setters and getter methods for the member variables
    public void setdate(LocalDate date){
        this.today_date=date;
    }
    public void setending_time(int end){
        this.ending_time=end;
    }
    public void setstarting_time(int start){
        this.starting_time=start;
    }
    public void setassigned_class(Scheduled_class s){
        this.assigned_class=s;
    }
    public LocalDate getdate(){
        return this.today_date;
    }
    public int getending_time(){
        return this.ending_time;
    }
    public int getstarting_time(){
        return this.starting_time;
    }
    public int getroom_no(){
        return this.ending_time;
    }
    public void setroom_no(int room_no){
        this.room_no=room_no;
    }
    public Scheduled_class getassigned_class(){
        return this.assigned_class;
    }
}
