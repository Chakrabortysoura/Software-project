package com.first.software_project;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    @GetMapping("/faculty_home_page//check_allocation{teacher_id}/{day}")
    public List<Allocation_done> check_allocation(@PathVariable int teacher_id,@PathVariable String day){
        Faculty teacher=Faculty_page.search_faculty(teacher_id);
        List<Allocation_done> result_list=new ArrayList<Allocation_done>();
        for(Scheduled_class i:Faculty_page.class_on_specific_day(teacher, day)){
            Allocation_done temp=new Allocation_done();
            temp.setclass_id(i.getclass_id());
            if(Faculty_page.check_allocated_or_not(i, 10)){
               temp.setallocation_done(); 
            }
            result_list.add(temp);
        }
        System.out.println("classes_on_a_specific_day() called");
        return result_list;
    }
}
