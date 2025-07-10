package com.software.room_allocation.controller;

import com.software.room_allocation.model.Administration;
import com.software.room_allocation.model.Faculty;
import com.software.room_allocation.model.User;
import com.software.room_allocation.service.AdminService;
import com.software.room_allocation.service.FacultyService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
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
        // Endpoint for creating new users with the Admin authority in the database.
        // This endpoint only takes name, designation and password(non-empty strings) as input.
        // No need to create a seperate user account for this admin user. That will be generated automatically and assigned to this user.
        if(name.compareTo("")==0 || admindesignation.compareTo("")==0 || password.compareTo("")==0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Administration administration = new Administration(name, admindesignation, new User("A", password));
        administration = adminService.createAdmin(administration);
        return new ResponseEntity<>(administration, HttpStatus.OK);
    }

    @PostMapping(path = "/create/facultyuser")
    public ResponseEntity<Faculty> createFaculty(@RequestParam String name, @RequestParam String password, @RequestParam String department, @RequestParam boolean hod) {
        // Endpoint for creating new users with the Faculty authority in the database.
        // This endpoint only takes name, designation and password(non-empty strings) as input.
        // No need to create a seperate user account for this faculty user. That will be generated automatically and assigned to this user.
        if(name.compareTo("")==0 || department.compareTo("")==0 || password.compareTo("")==0){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Faculty newfaculty= new Faculty(name, department, hod, new User("F", password));
        newfaculty=facultyService.createFaculty(newfaculty);
        return new ResponseEntity<>(newfaculty, HttpStatus.OK);
    }

    @PostMapping(path = "/update/adminuser")
    public ResponseEntity<Administration> updateAdmin(@RequestParam String name,@RequestParam String admindesignation, @RequestParam String password) {
        // Endpooint for updating user details for any admin user.
        // This endpoint takes all the same parameters as the admin creation endpoint. This endpoint updates the newly sent parameters for the admin user.
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
        // Endpooint for updating user details for any faculty user.
        // This endpoint takes all the same parameters as the faculty creation endpoint. This endpoint updates the newly sent parameters for the faculty user.
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
    public ResponseEntity<Boolean> deleteAdmin(@RequestParam int adminid, HttpSession session, @RequestParam String password) {
        // Delete an adminuser from the database.
        // Along with adminid for the user to be deleted password for the currently logged user in is another parameter for this endpoint
        Administration requestsender= (Administration) session.getAttribute("user");
        if(password.compareTo(requestsender.getUserdetails().getPassword())==0){
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
    public ResponseEntity<Boolean> deleteFaculty(@RequestParam int facultyid, HttpSession session, @RequestParam String password) {
        // Delete a facultyuser from the database.
        // Along with facultyid for the user to be deleted password for the currently logged user in is another parameter for this endpoint
        Administration requestsender= (Administration) session.getAttribute("user");
        if(password.compareTo(requestsender.getUserdetails().getPassword())==0){
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
    public ResponseEntity<Faculty> getHod(@PathParam("dept") String dept){
        // Get Faculty details for a particular department
        Faculty result= facultyService.getPreviousHod(dept);
        if(result==null){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path="/update/hod/{dept}")
    public ResponseEntity<Faculty> updateHod(@RequestParam int facultyidfornew_hod, @PathParam("dept") String dept){
        // Assign a new faculty as a hod for their correspoding department.
        // This method with fail if hod status of the previously assigned hod for the department couldn't be removed.
        Faculty updatedhod= facultyService.updateHod(facultyidfornew_hod, dept);
        if(updatedhod==null){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(updatedhod, HttpStatus.OK);
    }
}
