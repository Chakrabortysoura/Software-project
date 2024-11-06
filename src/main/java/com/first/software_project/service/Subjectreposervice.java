package com.first.software_project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.first.software_project.model.Subjects;
import com.first.software_project.repository.Subjectrepo;

@Service
public class Subjectreposervice {
    @Autowired
    Subjectrepo repo;

    public boolean addSubjects(Subjects s){
        try{
            repo.save(s);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Subjects> allSubjects(){
        try {
            return repo.findAll();
        } catch (Exception e) {
            System.out.println("Error with fetching all the subject values.");
        }
        return null;
    }

    public Subjects findSubject(int id){
        try {
            return repo.findById(id).orElse(null);
        } catch (Exception e) {
            System.out.println("Error with fetching the subject value.");
        }
        return null;
    }
}
