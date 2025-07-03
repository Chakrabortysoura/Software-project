package com.software.room_allocation.service;

import com.software.room_allocation.model.Faculty;
import com.software.room_allocation.model.User;
import com.software.room_allocation.repository.FacultyRepository;
import com.software.room_allocation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacultyService {
    @Autowired
    UserRepository userRepo;
    @Autowired
    FacultyRepository facultyRepo;

    public User createUser(User user) {
        try{
            return userRepo.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Faculty createFaculty(Faculty newfaculty) {
        try{
            User newuser= createUser(newfaculty.getUserdetails());
            if(newuser!=null) {
                newfaculty.setUserdetails(newuser);
                return facultyRepo.save(newfaculty);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Faculty updateFacultyDetails(Faculty newfaculty) {
        try{
            return facultyRepo.save(newfaculty);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Faculty getPreviousHod(String dept){
        return facultyRepo.findFacultyByDepartmentAndHod(dept, true);
    }
    public boolean removeHod(String dept){
        try{
            facultyRepo.removeHod(dept);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public Faculty updateHod(int id, String department){
        try{
            if(removeHod(department)) {
                facultyRepo.assignHod(id);
                return facultyRepo.findById(id).orElse(null);
            }else{
                return null;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Faculty getFaculty(int id){
        try{
            return facultyRepo.findById(id).orElse(null);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public boolean deleteFaculty(int id){
        try{
            facultyRepo.deleteById(id);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
