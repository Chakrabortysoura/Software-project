package com.first.software_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;

@Controller
public class Faculty_page {
    public static Faculty search_faculty(int id){
        // function to search for the faculty member from the list
        Faculty teacher=new Faculty();
        teacher.setfaculty_id(id);

        Configuration config=new Configuration().configure().addAnnotatedClass(Faculty.class);
        config.addAnnotatedClass(Scheduled_class.class);
        config.addAnnotatedClass(Subjects.class);
        SessionFactory sessionbuilder=config.buildSessionFactory();
        Session s1=sessionbuilder.openSession();
        s1.beginTransaction();
        try{
            teacher=s1.get(Faculty.class, id);
        }
        catch(Exception e ){
            System.out.println(e.getMessage());
        }
        finally{
            s1.getTransaction().commit();
            s1.close();
        }
        return teacher;
    }
    public static List<Scheduled_class> class_on_specific_day(Faculty teacher,String day){
        //to get the list of classes on a specific day of the week
        List<Scheduled_class> class_list=new ArrayList<Scheduled_class>();
        for(Scheduled_class i:teacher.getclass_list()){
            if(i.getday_of_week().matches(day)){
                class_list.add(i);
            }
        }
        return class_list;
    }
    public static synchronized boolean allocate_room(int room_no,Scheduled_class s){
        Room_allocation allocate=new Room_allocation();
        allocate.setassigned_class(s);
        Configuration config=new Configuration().configure().addAnnotatedClass(Scheduled_class.class);
        config.addAnnotatedClass(Room_allocation.class);
        config.addAnnotatedClass(Faculty.class);
        config.addAnnotatedClass(Subjects.class);
        SessionFactory sessionbuilder=config.buildSessionFactory();
        Session session1=sessionbuilder.openSession();
        session1.beginTransaction();
        try{
            session1.persist(allocate);
            session1.getTransaction().commit();
        }
        catch(Exception e ){
            session1.getTransaction().rollback();
            return false;
        }
        finally{
            session1.close();
        }
        return true;
    }
    public static synchronized boolean allocate_room(int room_no,Scheduled_class s,int start,int end){
        
        return true;
    }

    public static void main(String args[]){
        Scanner scn=new Scanner(System.in);
        Faculty result=search_faculty(1);
        System.out.println("The result will be: ");
        System.out.println("Faculty id: "+result.getfaculty_id()+"Faculty name: "+result.getname()+"\nThe scheduled classes for the faculty: ");
        System.out.println("On which day: ");
        String day=scn.nextLine().toUpperCase().substring(0, 3);  
        System.out.println("Selected list of class: ");
        if(class_on_specific_day(result, day).size()==0){
            System.out.println("You don't have any classes on this day.\n");
        }
        else{
            for(Scheduled_class i:class_on_specific_day(result, day)){
                System.out.println("Batch : "+i.getbatch()+"Day of the week: "+i.getday_of_week());
            }
        }
        // Room_allocation allocation1=new Room_allocation();
        // allocation1.setassigned_class(result.getclass_list().get(0));
        if(allocate_room(201,result.getclass_list().get(0))){
            System.out.println("The allocation was done successfull");
        }
        else{
            System.out.println("The allocation was no successful");
        }
        scn.close();
    }
}
