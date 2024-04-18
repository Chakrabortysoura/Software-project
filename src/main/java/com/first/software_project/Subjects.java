package com.first.software_project;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Subjects {
    @Id
    private int subject_code;
    private String subject_name;
    public void setsubject_code(int code){
        this.subject_code=code;
    }
    public void setsubject_name(String name){
        this.subject_name=name;
    }
    public String getsubject_name(){
        return subject_name;
    }
    public int getsubject_code(){
        return subject_code;
    }
}
