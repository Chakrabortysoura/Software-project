package com.first.software_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

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

    public static boolean check_allocated_or_not(Scheduled_class class1,int date){
        //to check if the room is allocated or not 
        boolean flag=true;
        //configuration 
        Configuration config=new Configuration().configure();
        SessionFactory sessionbuilder=config.buildSessionFactory();
        Session s1=sessionbuilder.openSession();
        s1.beginTransaction();

        NativeQuery<Room_allocation> search=s1.createNativeQuery("select * from Room_allocation where assigned_class_class_id= :class_id and date=:date",Room_allocation.class);
        try{
            search.setParameter("class_id", class1.getclass_id()).setParameter("date", date);
		    List<Room_allocation> result=search.getResultList();
            if(result.size()==0){
                flag=false;
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            s1.getTransaction().commit();
            s1.close();
        }
        return flag;
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
    @RequestMapping("/")
    public String login(){
        System.out.println("This is the login page.");
        return "login";
    }
    @RequestMapping("/faculty_home_page")
    public String faculty_home(@RequestParam("id") int teacher_id,@RequestParam("day") String day_of_week,Model m){
        Faculty teacher=search_faculty(teacher_id);
        //search for all the classes for the faculty on that day
        // and get the teachers details from searching by the teacher's id
        List<Scheduled_class> today_classes=class_on_specific_day(teacher, day_of_week);
        day_of_week=day_of_week.substring(0,3);
        m.addAttribute("day_of_week", day_of_week);
        m.addAttribute("faculty_details", teacher);
        m.addAttribute("class_list", today_classes);

        // check how many of the classes have already assigned classrooms
        
        System.out.println(today_classes.size());

        return "faculty_home";
    }
    // public static void main(String args[]){

    //     Scanner scn=new Scanner(System.in);
    //     Faculty result=search_faculty(1);
    //     System.out.println("The result will be: ");
    //     System.out.println("Faculty id: "+result.getfaculty_id()+"Faculty name: "+result.getname()+"\nThe scheduled classes for the faculty: ");
    //     System.out.println("On which day: ");
    //     String day=scn.nextLine().toUpperCase().substring(0, 3);  
    //     System.out.println("Selected list of class: ");
    //     if(class_on_specific_day(result, day).size()==0){
    //         System.out.println("You don't have any classes on this day.\n");
    //     }
    //     else{
    //         for(Scheduled_class i:class_on_specific_day(result, day)){
    //             System.out.println("Batch : "+i.getbatch()+"Day of the week: "+i.getday_of_week());
    //         }
    //     }
    //     // Room_allocation allocation1=new Room_allocation();
    //     // allocation1.setassigned_class(result.getclass_list().get(0));
    //     if(allocate_room(201,result.getclass_list().get(0))){
    //         System.out.println("The allocation was done successfully");
    //     }
    //     else{
    //         System.out.println("The allocation was not successful");
    //     }
    //     scn.close();
        // Scheduled_class class2=new Scheduled_class();
		// class2.setclass_id(2);
		
        // if(check_allocated_or_not(class2, 20)){
        //     System.out.println("The class was assigned.");
        // }
        // else{
        //     System.out.println("The class is still not assigned with any clasroom.");
        // }
    // }
}
