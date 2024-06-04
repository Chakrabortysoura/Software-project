package com.first.software_project;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class Login {
    @RequestMapping("/login")
    public String loginpage() {
        //for the main login page request to choose which type of user it is
        return "login";
    }
    @RequestMapping("/login/faculty")
    public ModelAndView facultylogin(@RequestParam("ID") String userid,@RequestParam("Password") String password){
        ModelAndView result=new ModelAndView("login");
        //to verfy the user's login credentials
        
        Configuration config =new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Login_details.class);
        SessionFactory sessionbuilder=config.buildSessionFactory();
        Session s1= sessionbuilder.openSession();
        s1.beginTransaction();
        try{
            Login_details fetched_object=s1.get(Login_details.class, userid);
            System.out.println("Faculty ID:"+fetched_object.getfaculty_id()+" Password check result: "+fetched_object.checkpassword(password));
            if(fetched_object.checkpassword(password)){
                result=new ModelAndView("redirect:/faculty_home_page?id="+fetched_object.getfaculty_id()+"&day=WED");
            }
        }
        catch(HibernateException e){
            System.out.println(e.getMessage());
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
}
