package com.software.room_allocation.controller;

import com.software.room_allocation.model.Administration;
import com.software.room_allocation.model.Faculty;
import com.software.room_allocation.model.User;
import com.software.room_allocation.service.AdminService;
import com.software.room_allocation.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    FacultyService facultyService;

    @PostMapping(path = "/create/adminuser")
    public ResponseEntity<Administration> createAdmin(@RequestParam String name, @RequestParam String admindesignation, @RequestParam String password) {
        if(name.compareTo("")==0 || admindesignation.compareTo("")==0 || password.compareTo("")==0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Administration administration = new Administration(name, admindesignation, new User("A", password));
        administration = adminService.createAdmin(administration);
        return new ResponseEntity<>(administration, HttpStatus.OK);
    }

    @PostMapping(path = "/create/facultyuser")
    public ResponseEntity<Faculty> createFaculty(@RequestParam String name, @RequestParam String password, @RequestParam String department, @RequestParam boolean hod) {
        if(name.compareTo("")==0 || department.compareTo("")==0 || password.compareTo("")==0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Faculty newfaculty= new Faculty(name, department, hod, new User("F", password));
        newfaculty=facultyService.createFaculty(newfaculty);
        return new ResponseEntity<>(newfaculty, HttpStatus.OK);
    }

    @PostMapping(path = "/update/adminuser")
    public ResponseEntity<Administration> updateAdmin(@RequestParam String name,@RequestParam String admindesignation, @RequestParam String password) {
        if(name.compareTo("")==0||password.compareTo("")==0||admindesignation.compareTo("")==0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Administration updatedadministration = new Administration(name, admindesignation, new User("A", password));
        updatedadministration= adminService.updateAdminDetails(updatedadministration);
        if(updatedadministration==null){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(updatedadministration, HttpStatus.OK);
    }

    @PostMapping(path = "/update/facultyuser")
    public ResponseEntity<Faculty> updateFaculty(@RequestParam String name, @RequestParam String password, @RequestParam String department, @RequestParam boolean hod) {
        if(name.compareTo("")==0||password.compareTo("")==0||department.compareTo("")==0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Faculty updatedfaculty= new Faculty(name, department, hod, new User("F", password));
        updatedfaculty= facultyService.updateFacultyDetails(updatedfaculty);
        if(updatedfaculty==null){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(updatedfaculty, HttpStatus.OK);
    }

    @PostMapping(path="/delete/adminuser")
    public ResponseEntity<Boolean> deleteAdmin(@RequestParam int adminid, @RequestParam int requestsenderid, @RequestParam String password) {
        if(password.compareTo(adminService.findAdmin(requestsenderid).getUserdetails().getPassword())==0) {
            if(adminService.deleteAdmin(adminid)){
                return new ResponseEntity<>(true,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(path="/delete/facultyuser")
    public ResponseEntity<Boolean> deleteFaculty(@RequestParam int facultyid, @RequestParam int requestsenderid, @RequestParam String password) {
        if(password.compareTo(adminService.findAdmin(requestsenderid).getUserdetails().getPassword())==0) {
            if(facultyService.deleteFaculty(facultyid)){
                return new ResponseEntity<>(true,HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(path="/get/hod/{dept}")
    public ResponseEntity<Faculty> getHod(@PathVariable String dept){
        Faculty result= facultyService.getPreviousHod(dept);
        if(result==null){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/update/hod")
    public ResponseEntity<Faculty> updateHod(@RequestParam int id, @RequestParam String dept){
        Faculty updatedhod= facultyService.updateHod(id, dept);
        if(updatedhod==null){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(updatedhod, HttpStatus.OK);
    }
}
