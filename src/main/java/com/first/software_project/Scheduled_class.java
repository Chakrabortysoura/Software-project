package com.first.software_project;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Scheduled_class {
    @Id
    private int class_id;

    private String batch;
    private String day_of_week;
    private int start,end;
    @ManyToOne(fetch = FetchType.EAGER)
    private Subjects topic;
    @ManyToOne(fetch = FetchType.EAGER)
    private Faculty teacher;
    public void setclass_id(int id){
        this.class_id=id;
    }
    public int getclass_id(){
        return this.class_id;
    }
    public void setteacher(Faculty t){
        this.teacher=t;
    }
    public void settopic(Subjects s){
        this.topic=s;
    }
    public void setstart(int start){
        this.start=start;
    }
    public void setend(int end){
        this.end=end;
    }
    public void setbatch(String batch){
        this.batch=batch;
    }
    public void setday_of_week(String day){
        this.day_of_week=day;
    }
    @JsonBackReference
    public Faculty getteacher(){
        return this.teacher;
    }
    public Subjects gettopic(){
        return this.topic;
    }
    public int getstart(){
        return this.start;
    }
    public int getend(){
        return this.end;
    }
    public String getbatch(){
        return this.batch;
    }
    public String getday_of_week(){
        return this.day_of_week;
    }
}
