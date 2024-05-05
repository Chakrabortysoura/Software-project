package com.first.software_project;

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
public class room_allocation_page {
    public static List<Rooms> check_room(int start,int end){
        Configuration config=new Configuration().configure();
        SessionFactory sessionbuilder=config.buildSessionFactory();
        Session s1=sessionbuilder.openSession();
        s1.beginTransaction();

        NativeQuery<Rooms> search=s1.createNativeQuery("select * from `Rooms` WHERE room_no not in (select room_no from `Room_allocation` where (starting_time>=:start and starting_time<:end ) OR (ending_time>=:start and ending_time<:end))",Rooms.class);
        List<Rooms> list_of_rooms=null;
        try{
            search.setParameter("start", start).setParameter("end",end);
            list_of_rooms=search.getResultList();
            return list_of_rooms;    
        }
        catch(Exception e){
            System.out.println("There was an error in fetching room details."+e.getMessage());
        }
        return list_of_rooms;
    }
    @RequestMapping("/room_selection")
    public String allocation_page(@RequestParam("starting_time") int starting,@RequestParam("ending_time") int ending,HttpSession s1){

        return "select_room";
    }
    @RequestMapping("/allocation_details")
    public String allocation_page(HttpSession s1){
        //get the class to be allocated and its expected starting and ending time
        Scheduled_class target_class=(Scheduled_class)s1.getAttribute("class_to_allocate");
        int start=target_class.getstart();
        int end=target_class.getend();
        //search for available room
        List<Rooms> available_rooms=check_room(start, end);
        s1.setAttribute("rooms_list", available_rooms);
        return "allocation_details";    
    }
}
