package com.first.software_project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Controller;
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

    public static int check_allocated_or_not(Scheduled_class class1,LocalDate date){
        //to check if the room is allocated or not 
        int flag=-1;
        //configuration 
        Configuration config=new Configuration().configure();
        SessionFactory sessionbuilder=config.buildSessionFactory();
        Session s1=sessionbuilder.openSession();
        s1.beginTransaction();

        NativeQuery<Integer> allocated_room=s1.createNativeQuery("select room_no from Room_allocation where assigned_class_class_id= :class_id and today_date=:date",Integer.class);
        NativeQuery<Integer> end_time=s1.createNativeQuery("select ending_time from Room_allocation where assigned_class_class_id= :class_id and today_date=:date",Integer.class);
        NativeQuery<Integer> start_time=s1.createNativeQuery("select starting_time from Room_allocation where assigned_class_class_id= :class_id and today_date=:date",Integer.class);
        
        try{
            allocated_room.setParameter("class_id", class1.getclass_id()).setParameter("date", date);
            start_time.setParameter("class_id", class1.getclass_id()).setParameter("date", date);
            end_time.setParameter("class_id", class1.getclass_id()).setParameter("date", date);
		    List<Integer> result=allocated_room.getResultList();
            flag=result.get(0);
            if(result.get(0)!=-1){
                // to check and update the newly allocated class start times which can differ from the originally allocated time
                result=start_time.getResultList();
                class1.setstart(result.get(0));
                //to check the updated ending time for the class which can differ from the originally allocated time
                result=end_time.getResultList();
                class1.setend(result.get(0));
            }
            return flag;
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
    public String faculty_home(@RequestParam("id") int teacher_id,@RequestParam("day") String day_of_week,HttpSession s1){
        
        // String current_date=LocalDate.now().toString();
        Faculty teacher=search_faculty(teacher_id);
        //search for all the classes for the faculty on that day
        // and get the teachers details from searching by the teacher's id
        
        List<Scheduled_class> today_classes=class_on_specific_day(teacher, day_of_week);
        day_of_week=day_of_week.substring(0,3);

        //set all this data after being fetched in the session object
        s1.setAttribute("day_of_week", day_of_week);
        s1.setAttribute("faculty_details", teacher);
        s1.setAttribute("class_list", today_classes);
        
        // check how many of the classes have already assigned classrooms
        Allocation_done[] checklist=new Allocation_done[today_classes.size()];
        for(int i=0;i<today_classes.size();i++){
            checklist[i]=new Allocation_done();
            // if(check_allocated_or_not(today_classes.get(i), current_date)){
                checklist[i].setallocation_done(check_allocated_or_not(today_classes.get(i), LocalDate.now()));
            // }
        }
        //saving the checklist in the session object
        s1.setAttribute("allocation_checklist", checklist);
        
        return "faculty_home";
    }
}
