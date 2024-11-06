package com.first.software_project.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.first.software_project.model.Faculty;
import com.first.software_project.model.Scheduled_class;
import com.first.software_project.model.Subjects;
import com.first.software_project.service.Classreposervice;
import com.first.software_project.service.Facultyreposervice;
import com.first.software_project.service.Subjectreposervice;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/class")
public class Scheduled_classcontroller {
    @Autowired
    Classreposervice classservice;
    @Autowired
    Facultyreposervice facultyreposervice;
    @Autowired
    Subjectreposervice subjectreposervice;

    @GetMapping("/get/today")
    public List<Scheduled_class> getTodaysClass(@RequestParam int id,HttpServletResponse response){

        String day=LocalDate.now().getDayOfWeek().toString().substring(0, 3);
        response.setStatus(200);

        Faculty teacher=facultyreposervice.findFaculty(id);
        List<Scheduled_class> result=classservice.todaysClasses(day, teacher);
        if(result==null){
            response.setStatus(404);
        }
        return result;
    }


    @PostMapping("/add")
    public Scheduled_class addClass(@RequestParam String batch,@RequestParam String day,@RequestParam int start,@RequestParam int end,@RequestParam int teacher_id,@RequestParam int subject_id,HttpServletResponse response){
        Scheduled_class newclass=new Scheduled_class();
        newclass.setBatch(batch);
        newclass.setDay(day);
        newclass.setEnd(end);
        newclass.setStart(start);
        
        Subjects topic=subjectreposervice.findSubject(subject_id);
        Faculty teacher=facultyreposervice.findFaculty(subject_id);
        newclass.setTopic(topic);
        newclass.setTeacher(teacher);

        try {
            classservice.addClass(newclass);
            return newclass;
        } catch (Exception e) {
            System.out.println("Erro adding the classs details");
            e.printStackTrace();
        }
        return null;
    }
}
