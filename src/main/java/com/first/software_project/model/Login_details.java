package com.first.software_project.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Login_details {
    @Id
    int faculty_id;
    @Column(name = "password")
    String password;
    
    public boolean checkpassword(String password){
        if(this.password.compareTo(password)==0){
            return true;
        }
        return false;
    }   
}
