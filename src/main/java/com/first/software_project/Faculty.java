package com.first.software_project;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Faculty {
    private String name;
    @Id
    private int faculty_id;
    @OneToMany(mappedBy = "teacher",fetch = FetchType.EAGER)
    private List<Scheduled_class> class_list=new ArrayList<Scheduled_class>();
    // getter and setter methods for the member variables
    public void setfaculty_id(int a){
        this.faculty_id=a;
    }
    public void setname(String name){
        this.name=name;
    }
    public void setclass_list(Scheduled_class c){
        class_list.add(c);
    }
    public void setclass_list(List<Scheduled_class> c){
        for(Scheduled_class i:c){
            class_list.add(i);
        }
    }
    public int getfaculty_id(){
        return this.faculty_id;
    }
    public String getname(){
        return this.name;
    }
    public List<Scheduled_class> getclass_list(){
        return class_list;
    }
}
