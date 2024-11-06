package com.first.software_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first.software_project.model.Subjects;
import com.first.software_project.service.Subjectreposervice;

@RestController
@RequestMapping(path = "/subject")
public class Subjectscontroller {
    @Autowired
    Subjectreposervice repo;

    @PostMapping("/add")
    public Subjects addSubjects(@RequestBody Subjects s){
        try{
            repo.addSubjects(s);
            return s;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/all_subjects")
    public List<Subjects> getallSubjects(){
        List<Subjects> result=repo.allSubjects();
        return result;
    }
}
