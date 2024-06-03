package com.first.software_project;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class Faculty_details {
    @GetMapping("/faculty_home_page/{teacher_id}")
    public Faculty faculty_home(@PathVariable int teacher_id){
        Faculty teacher=Faculty_page.search_faculty(teacher_id);
        System.out.println("faculty_home() called");
        return teacher;
    }

    @GetMapping("/faculty_home_page/{teacher_id}/{day}")
    public List<Scheduled_class> classes_on_specific_day(@PathVariable int teacher_id,@PathVariable String day){
        Faculty teacher=Faculty_page.search_faculty(teacher_id);
        List<Scheduled_class> result_list=Faculty_page.class_on_specific_day(teacher, day);
        System.out.println("classes_on_a_specific_day() called");
        return result_list;
    }

    //consumes specifies the data type for  the api call be that json or xml with consumes
    //also returns a the response in a specific data format with produces being xml
    @GetMapping(path ="/faculty_home_page/check_allocation/{teacher_id}/{day}",consumes = {"application/json"},produces = {"application/xml"} )
    public List<Allocation_done> check_allocation(@PathVariable int teacher_id,@PathVariable String day){
        Faculty teacher=Faculty_page.search_faculty(teacher_id);
        List<Allocation_done> result_list=new ArrayList<Allocation_done>();
        for(Scheduled_class i:Faculty_page.class_on_specific_day(teacher, day)){
            Allocation_done temp=new Allocation_done();
            temp.setclass_id(i.getclass_id());
            // if(Faculty_page.check_allocated_or_not(i, LocalDate.now().toString())){
            temp.setallocation_done(Faculty_page.check_allocated_or_not(i, LocalDate.now())); 
            // }
            result_list.add(temp);
        }
        System.out.println("classes_on_a_specific_day() called");
        return result_list;
    }
}
