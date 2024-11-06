package com.first.software_project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.first.software_project.model.Faculty;
import com.first.software_project.service.Facultyreposervice;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@RestController
@RequestMapping(path = "/faculty")
public class Facultycotroller {
    @Autowired
    Facultyreposervice facultyservice;

    @GetMapping("/details/{id}")
    public Faculty getFacultydetails(@PathVariable int id,HttpServletResponse response) {
        response.setStatus(200);
        Faculty result=facultyservice.findfacultydetails(id);
        if(result==null){
            response.setStatus(404);
        }
        return result;
    }

    @PostMapping("/add")
    public Faculty addFaculty(@RequestParam String name,@RequestParam int id,HttpServletResponse response){
        Faculty result=new Faculty();
        result.setName(name);
        result.setFaculty_id(id);
        try{
            facultyservice.addFaculty(result);
            response.setStatus(200);
            return result;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        response.setStatus(400);
        return null;
    }
    
}
