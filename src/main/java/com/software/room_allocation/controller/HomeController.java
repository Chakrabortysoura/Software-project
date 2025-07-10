package com.software.room_allocation.controller;

import com.software.room_allocation.model.Administration;
import com.software.room_allocation.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    AdminService adminService;
    @GetMapping(path = "/login")
    public void home(HttpSession session, @RequestParam int userid){
        Administration user= (Administration) adminService.findAdmin(userid);
        session.setAttribute("user",user);
    }

    @GetMapping(path = "/login/admin")
    public void getAdmin(HttpSession session, @RequestParam int adminid){
        Administration user= (Administration) adminService.findAdmin(adminid);
        session.setAttribute("user",user);
    }
}
