package com.first.software_project;

import java.time.LocalDate;
import java.util.List;

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
public class Reports {
    //function to search for all the room allocation for the day and also check for the allocations done or not
    public static List<Scheduled_class> list_of_classes_by_the_day(String day){
        List<Scheduled_class> class_list=null;
        
        //hibernate configuration
        Configuration config=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Scheduled_class.class).addAnnotatedClass(Faculty.class).addAnnotatedClass(Subjects.class);
        SessionFactory sessionbuilder=config.buildSessionFactory();
        Session s1=sessionbuilder.openSession();
        s1.beginTransaction();
        
        NativeQuery<Scheduled_class> queryresult=s1.createNativeQuery("select * from `Scheduled_class` WHERE day_of_week=:day_of_week", Scheduled_class.class);
        try{
            queryresult.setParameter("day_of_week", day);
            class_list=queryresult.getResultList();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            s1.getTransaction().commit();
        }
        return class_list;
    }
    
    @RequestMapping("/admin_home")
    public String daily_report(HttpSession m){
        List<Scheduled_class> result_list=list_of_classes_by_the_day("WED");
        int[] checklist=new int[result_list.size()];
        for(int i=0;i<result_list.size();i++){
            checklist[i]=Faculty_page.check_allocated_or_not(result_list.get(i),LocalDate.now());
        }
        m.setAttribute("checklist", checklist);
        m.setAttribute("class_list", result_list);
        System.out.println("class list: ");
        for(Scheduled_class item:result_list){
            System.out.println(item.getclass_id());
        }
        return ("admin_home");
    }

    @RequestMapping("/weekly_report")
    public String weekly_report(@RequestParam("starting_date") LocalDate date, Model m){
        String weeklydata="select * from Room_allocation where today_date>=:start_date and today_date<:end_date";
        LocalDate starting_date=date;
        String day_of_week=date.getDayOfWeek().toString();
        System.out.println("The current date: "+starting_date+" Day of the Week: "+day_of_week);
        LocalDate ending_date=starting_date.plusDays(8);
        System.out.println("The ending date for the query:"+ending_date);

        //creating query configs
        Configuration config=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Scheduled_class.class).addAnnotatedClass(Subjects.class).addAnnotatedClass(Room_allocation.class).addAnnotatedClass(Faculty.class);
        SessionFactory sessionbuiler=config.buildSessionFactory();
        Session session1=sessionbuiler.openSession();
        session1.beginTransaction();
        NativeQuery<Room_allocation> weekly_report= session1.createNativeQuery(weeklydata, Room_allocation.class);
        try{
            weekly_report.setParameter("start_date", starting_date.toString());
            weekly_report.setParameter("end_date", ending_date.toString());
            List<Room_allocation> weekly_report_data=weekly_report.getResultList();
            m.addAttribute("class_report",weekly_report_data);
            for(Room_allocation i:weekly_report_data){
                System.out.println("Class ID: "+i.getassigned_class().getclass_id()+" Date: "+i.getdate().toString());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            session1.getTransaction().commit();
            session1.close();
        }
        return "Week_report";
    }
    
}
