package com.first.software_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.software_project.model.Faculty;
import com.first.software_project.repository.Facultyrepo;

@Service
public class Facultyreposervice {
    @Autowired
    Facultyrepo facultyrepo;
    
    public Faculty findfacultydetails(int id){
        try {
            return facultyrepo.findById(id).orElse(null);
        } catch (Exception e) {
            System.out.println("Erro fetching the data for the Faculty");
            e.printStackTrace();
        }
        return null;
    }

    public Faculty findFaculty(int id){
        try {
            return facultyrepo.findById(id).orElse(null);
        } catch (Exception e) {
            System.out.println("Error with fetching the faculty details.");
        }
        return null;
    }

    public boolean addFaculty(Faculty f){
        try {
            facultyrepo.save(f);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
