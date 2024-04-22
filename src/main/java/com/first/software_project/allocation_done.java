package com.first.software_project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Room_allocation")
public class allocation_done {
    @Id
    private int serial_no;
    @Column(name = "assigned_class_class_id")
    private int class_id;
    @Column(name="room_no")
    private int room_no;
    @Transient
    private boolean allocation_done;
    
    public void setclass_id(int id){
        this.class_id=id;
    }
    public void seallocation_done(){
        this.allocation_done=true;
    }
    public int getclass_id(){
        return this.class_id;
    }
    public boolean getallocation_done(){
        return this.allocation_done;
    }
    public int getroom_no(){return room_no;}
    public void setroom_no(int room){
        this.room_no=room;
    }
}
