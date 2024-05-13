package com.first.software_project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Classes_for_batches {
    @Id
    private int class_id;

    @Column(name = "day_of_week")
    private String day_of_week;
    @Column(name = "name")
    private String name;
    @Column(name = "subject_name")
    private String  subject_name;
    @Column(name = "batch")
    private String batch;
    @Column(name = "expected_start")
    private int expected_start;
    @Column(name = "expected_end")
    private int expected_end;
    
    public void setclass_id(int id){
        this.class_id=id;
    }
    public int getclass_id(){
        return class_id;
    }
    public  void setday_of_week(String day){
        this.day_of_week=day;
    }
    public String getday_of_week(){
        return day_of_week;
    }
    public  void setname(String name){
        this.name=name;
    }
    public String getname(){
        return name;
    }
    public  void setsubject_name(String subject_name){
        this.subject_name=subject_name;
    }
    public String getsubject_name(){
        return subject_name;
    }
    public  void setbatch(String batch){
        this.batch=batch;
    }
    public String getbatch(){
        return batch;
    }
    public void setexpected_start(int start){
        this.expected_start=start;
    }
    public int getexpected_start(){
        return this.expected_start;
    }
    public void setexpected_end(int end){
        this.expected_end=end;
    }
    public int getexpected_end(){
        return expected_end;
    }
}
