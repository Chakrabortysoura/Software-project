package com.first.software_project;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/today_report")
    public String daily_report(Model m){
        List<Scheduled_class> result_list=list_of_classes_by_the_day("WED");
        int[] checklist=new int[result_list.size()];
        for(int i=0;i<result_list.size();i++){
            checklist[i]=Faculty_page.check_allocated_or_not(result_list.get(i),LocalDate.now().toString());
        }
        m.addAttribute("checklist", checklist);
        m.addAttribute("class_list", result_list);
        return ("Today's_report");
    }

    @RequestMapping("/weekly_report")
    public String weekly_report(Model m){
        int currentdate=LocalDate.now().getDayOfMonth();
        System.out.println("The current date: "+currentdate);
        return "Week_report";
    }
    
}
