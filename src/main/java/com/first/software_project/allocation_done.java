package com.first.software_project;

public class allocation_done {
    private int class_id;
    private boolean allocation_done=false;
    public void setclass_id(int class_id){
        this.class_id=class_id;
    }
    public void setallocation_done(){
        this.allocation_done=true;
    }
    public boolean getallocation_done(){
        return this.allocation_done;
    }
}