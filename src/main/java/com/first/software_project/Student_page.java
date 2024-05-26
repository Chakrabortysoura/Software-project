package com.first.software_project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Student_page {
    
    //method to get the list of classes for a specified batch on that day of week
    public List<Scheduled_class> class_for_batches(String batch_no,String day){
        List<Scheduled_class> result=new ArrayList<Scheduled_class>();

        //hibernate  configuration
        Configuration config=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Scheduled_class.class).addAnnotatedClass(Faculty.class).addAnnotatedClass(Subjects.class);
        SessionFactory sessionconfig=config.buildSessionFactory();
        Session s1=sessionconfig.openSession();
        s1.beginTransaction();
        NativeQuery<Scheduled_class> searquery=s1.createNativeQuery("select * from Scheduled_class where batch=:batch and day_of_week=:day",Scheduled_class.class);
        
        try{
            searquery.setParameter("batch", batch_no).setParameter("day", day);
            result=searquery.getResultList();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            s1.getTransaction().commit();
            s1.close();
        }
        return result;
    }

    @RequestMapping("/student_page")
    public String student_home_page(@RequestParam("batch") String  batch_no,@RequestParam("day")String day,Model m){
        List<Scheduled_class> class_list=class_for_batches(batch_no, day);
        System.out.println("The class list for today:\n");
        if(class_list.size()==0){
            System.out.println("There is no class today.\n");
        }
        else{
            boolean[] checklist=new boolean[class_list.size()];
            // System.out.println((class_list.size()));
            for(int i=0;i<class_list.size();i++){
                checklist[i]=Faculty_page.check_allocated_or_not(class_list.get(i), LocalDate.now().toString());
            }
            m.addAttribute("allocation_checklist", checklist);
            m.addAttribute("class_list", class_list);
        }
        return "student_page";
    }
}