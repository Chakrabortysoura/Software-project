package com.first.software_project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.software_project.model.Faculty;
import com.first.software_project.model.Scheduled_class;
import com.first.software_project.repository.Scheduled_classrepo;

@Service
public class Classreposervice {
    @Autowired
    Scheduled_classrepo classrepo;

    public List<Scheduled_class> todaysClasses(String day,Faculty teacher){
        List<Scheduled_class> result=new ArrayList<>();
        try{
            for(Scheduled_class i:classrepo.findByTeacher(teacher)){
                if(i.getDay().compareToIgnoreCase(day)==0){
                    result.add(i);
                }   
            }
        }catch(Exception e){
            System.out.println("Error fetching the data.");
            e.printStackTrace();
        }
        return result;
    }

    public boolean addClass(Scheduled_class newclass){
        try{
            classrepo.save(newclass);
            return true;
        }catch(Exception e){
            System.out.println("There was an erro in adding the class");
            System.out.println(e.getMessage());
        }
        return false;
    }
}
