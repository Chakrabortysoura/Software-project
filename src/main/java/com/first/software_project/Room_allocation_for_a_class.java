package com.first.software_project;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class Room_allocation_for_a_class {
    public static List<Rooms> check_room(int start,int end,LocalDate date){
        Configuration config=new Configuration().configure();
        SessionFactory sessionbuilder=config.buildSessionFactory();
        Session s1=sessionbuilder.openSession();
        s1.beginTransaction();

        NativeQuery<Rooms> search=s1.createNativeQuery("select * from `Rooms` WHERE room_no not in (select room_no from `Room_allocation` where (starting_time>=:start and starting_time<:end ) OR (ending_time>=:start and ending_time<:end) AND today_date=:date)",Rooms.class);
        List<Rooms> list_of_rooms=null;
        try{
            search.setParameter("start", start).setParameter("end",end).setParameter("date", date);
            list_of_rooms=search.getResultList();
            return list_of_rooms;    
        }
        catch(Exception e){
            System.out.println("There was an error in fetching room details."+e.getMessage());
        }
        return list_of_rooms;
    }

    public synchronized void allocation_entry(Scheduled_class class_to_allocate,int start,int end,int room){
        //sychronized function for actually adding the entry for successfull allocation.
        
        //set all the parameter in the allocation entry
        Room_allocation allocaiton_entry=new Room_allocation();
        allocaiton_entry.setassigned_class(class_to_allocate);
        allocaiton_entry.setdate(LocalDate.now());
        allocaiton_entry.setstarting_time(start);
        allocaiton_entry.setending_time(end);
        allocaiton_entry.setroom_no(room);

        //get the database configuration and session ready
        Configuration config=new Configuration().configure("hibernate.cfg.xml");
        config.addAnnotatedClass(Room_allocation.class).addAnnotatedClass(Faculty.class).addAnnotatedClass(Scheduled_class.class).addAnnotatedClass(Subjects.class);
        SessionFactory sessiondetails= config.buildSessionFactory();
        Session s1=sessiondetails.openSession();
        s1.beginTransaction();
        try{
            s1.persist(allocaiton_entry);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            s1.beginTransaction().rollback();

        }
        finally{
            s1.getTransaction().commit();
        }

    }
    
    protected class add_allocation extends Thread{
        Scheduled_class class_to_allocate;
        private int start,end,room;
        add_allocation(Scheduled_class class_to_allocate,int start,int end,int room){
            this.class_to_allocate=class_to_allocate;
            this.start=start;
            this.end=end;
            this.room=room;
        }
        @Override
        public void run(){
            allocation_entry(class_to_allocate, start, end,room);
        }
    }
    
    
    @RequestMapping("/room_selection")
    public String allocation_page(@RequestParam("starting_time") int starting,@RequestParam("ending_time") int ending,HttpSession s1,HttpServletRequest r1){
        //get the class to be allocated and its actual starting and ending time
        Scheduled_class target_class=(Scheduled_class)s1.getAttribute("class_to_allocate");
        int start=target_class.getstart();
        int end=target_class.getend();

        //search for available room
        List<Rooms> available_rooms=check_room(start, end,LocalDate.now());
        //set the list of allocated room in the session object
        s1.setAttribute("rooms_list", available_rooms);
    
        return "select_room";
    }
    
    
    @RequestMapping("/allocation_details")
    public String allocation_details_page(){
        return "allocation_details";    
    }


    @RequestMapping("/allocate_room")
    public String allocate_room(@RequestParam("starting")int start,@RequestParam("ending")int end,@RequestParam("room_no") int room_no,HttpSession s1){
        //fetch the class data from the session object 
        Scheduled_class target_class=(Scheduled_class)s1.getAttribute("class_to_allocate");
        System.out.println("The room no is:"+room_no);
        //creating the thread which will call the sychronized function for allocating the room
        try{
            add_allocation thread1=new add_allocation(target_class, start, end,room_no);
            thread1.start();
            Allocation_done[] checklist=(Allocation_done[]) s1.getAttribute("allocation_checklist");
            for(Allocation_done i:checklist){
                if(i.getclass_id()==target_class.getclass_id()){
                    i.setclass_id(target_class.getclass_id());
                    i.setallocation_done(room_no);
                    break;
                }
            }
            System.out.println("The room allocation was successful.");
        }
        catch(Exception e){
            System.out.println(e.getMessage()+"\n the allocation failed");
            Faculty teacher=(Faculty)s1.getAttribute("faculty_details");
            int teacher_id=teacher.getfaculty_id();
            return new String("redirect:/faculty_home_page?id="+teacher_id+"&day=FRIDAY");
        }
        finally{
            System.out.println("The allocation process finished");
        }
        return "success_allocation";
        
    }


    @RequestMapping("/go_back_to_home")
    public String go_home_page(HttpSession  s1){
        int teacher_id=((Faculty)s1.getAttribute("faculty_details")).getfaculty_id();
        return new String("redirect:/faculty_home_page?id="+teacher_id+"&day="+(String)s1.getAttribute("day_of_week"));
    }
}
