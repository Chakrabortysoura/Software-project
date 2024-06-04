package com.first.software_project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Login_details {
    @Id
    int faculty_id;
    @Column(name = "password")
    String password;
    public void setfaculty_id(int id){
        this.faculty_id=id;
    }
    public void setpassword(String password){
        this.password=password;
    }
    public int getfaculty_id(){
        return this.faculty_id;
    }
    public String getpassword(){
        return this.password;
    }
    public boolean checkpassword(String password){
        if(this.password.compareTo(password)==0){
            return true;
        }
        return false;
    }
}
