package com.software.room_allocation.controller;

import com.software.room_allocation.model.Administration;
import com.software.room_allocation.model.Faculty;
import com.software.room_allocation.model.User;
import com.software.room_allocation.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping(path = "/create/adminuser")
    public Administration createAdmin(@RequestParam String name,@RequestParam String designation, @RequestParam String password) {
        Administration administration = new Administration(name, designation, new User("Admin", password));
        return adminService.createAdmin(administration);
    }

    @PostMapping(path = "/create/facultyuser")
    public Faculty createFaculty(@RequestParam String name, @RequestParam String password, @RequestParam String department, @RequestParam boolean hod) {
        Faculty faculty= new Faculty(name, department, hod, new User("Faculty", password));
        return adminService.createFaculty(faculty);
    }
    @PostMapping(path = "/update/adminuser")
    public Administration updateAdmin(@RequestParam String name,@RequestParam String designation, @RequestParam String password) {
        Administration administration = new Administration(name, designation, new User("Admin", password));
        return adminService.updateAdminDetails(administration);
    }

    @PostMapping(path = "/update/facultyuser")
    public Faculty updateFaculty(@RequestParam String name, @RequestParam String password, @RequestParam String department, @RequestParam boolean hod) {
        Faculty faculty= new Faculty(name, department, hod, new User("Faculty", password));
        return adminService.updateFacultyDetails(faculty);
    }
    @GetMapping(path="/get/hod/{dept}")
    public Faculty getHod(@PathVariable String dept){
        return adminService.getPreviousHod(dept);
    }
    @PostMapping(path="/update/hod")
    public Faculty updateHod(@RequestParam int id, @RequestParam String dept){
        if(adminService.removeHod(dept)){
            return adminService.updateHod(id, dept);
        }
        return null;
    }
}
