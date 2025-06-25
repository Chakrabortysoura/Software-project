package com.software.room_allocation.service;

import com.software.room_allocation.model.Administration;
import com.software.room_allocation.model.Faculty;
import com.software.room_allocation.model.User;
import com.software.room_allocation.repository.AdminRepository;
import com.software.room_allocation.repository.FacultyRepository;
import com.software.room_allocation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepo;
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
    public User updateUserDetails(User newuser) {
        return userRepo.save(newuser);
    }
    public Administration createAdmin(Administration newadmin) {
       try{
           User newuser= createUser(newadmin.getUserdetails());
           if(newuser!=null) {
               newadmin.setUserdetails(newuser);
               return adminRepo.save(newadmin);
           }
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
       return null;
    }
    public Administration updateAdminDetails(Administration newadmin) {
        try{
           return adminRepo.save(newadmin);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
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
}
