package com.software.room_allocation.service;

import com.software.room_allocation.model.Administration;
import com.software.room_allocation.model.User;
import com.software.room_allocation.repository.AdminRepository;
import com.software.room_allocation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepo;
    @Autowired
    UserRepository userRepo;

    public Administration findAdmin(int id) {
        try{
            return adminRepo.findById(id).orElse(null);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
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
    public Administration updateAdminDetails(int adminid, String newname, String newadmindesignation, String newpassword) {
        try{
            Administration originaladminuser= adminRepo.findById(adminid).orElse(null);
            if(originaladminuser==null) {
                return null;
            }
            originaladminuser.setEmployee_name(newname);
            originaladminuser.setDesignation(newadmindesignation);
            originaladminuser.getUserdetails().setPassword(newpassword); // Update all the parameters of the original admin user to save
            return adminRepo.save(originaladminuser);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public boolean deleteAdmin(int id) {
        try{
            adminRepo.deleteById(id);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
