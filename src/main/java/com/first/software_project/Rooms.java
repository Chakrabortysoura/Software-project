package com.first.software_project;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Rooms {
    @Id
    private int room_no;
    public Rooms(int no){
        this.room_no=no;
    }
    public void setroom_no(int n){
        this.room_no=n;
    }
    public int getroom_no(){
        return room_no;
    }
}
