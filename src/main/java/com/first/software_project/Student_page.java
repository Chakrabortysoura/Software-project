package com.first.software_project;

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
    //function to check if the class is already allocated  or not 
    public boolean check_allocated_or_not(Classes_for_batches target,int date){
        //to check if the room is allocated or not 
        boolean flag=true;
        //configuration 
        Configuration config=new Configuration().configure();
        SessionFactory sessionbuilder=config.buildSessionFactory();
        Session s1=sessionbuilder.openSession();
        s1.beginTransaction();

        NativeQuery<Room_allocation> search=s1.createNativeQuery("select * from Room_allocation where assigned_class_class_id= :class_id and date=:date",Room_allocation.class);
        try{
            search.setParameter("class_id", target.getclass_id()).setParameter("date", date);
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

    //method to get the list of classes for a specified batch on that day of week
    public List<Classes_for_batches> class_for_batches(String batch_no,String day){
        List<Classes_for_batches> result=new ArrayList<Classes_for_batches>();

        //hibernate  configuration
        Configuration config=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Scheduled_class.class).addAnnotatedClass(Faculty.class).addAnnotatedClass(Subjects.class).addAnnotatedClass(Classes_for_batches.class);
        SessionFactory sessionconfig=config.buildSessionFactory();
        Session s1=sessionconfig.openSession();
        s1.beginTransaction();
        try{
            NativeQuery<Classes_for_batches> searquery=s1.createNativeQuery("select class_id,day_of_week,end as 'expected_end',start as 'expected_start',batch,name,subject_name from(SELECT * from (select * from `Scheduled_class` join `Faculty` WHERE `Scheduled_class`.teacher_faculty_id=`Faculty`.faculty_id ) as intermid join `Subjects` where `intermid`.topic_subject_code=`Subjects`.subject_code) as finalresult where batch=:batch",Classes_for_batches.class);
            
            searquery.setParameter("batch", batch_no);
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
    public String student_home_page(@RequestParam("batch") String  batch_no,Model m){
        List<Classes_for_batches> class_list=class_for_batches(batch_no, "WED");
        System.out.println("The class list for today:\n");
        if(class_list.size()==0){
            System.out.println("There is no class today.\n");
        }
        else{
            boolean[] checklist=new boolean[class_list.size()];
            // System.out.println((class_list.size()));
            for(int i=0;i<class_list.size();i++){
                checklist[i]=check_allocated_or_not(class_list.get(i), 10);
            }
            m.addAttribute("allocation_checklist", checklist);
            m.addAttribute("class_list", class_list);
        }
        return "student_page";
    }
}